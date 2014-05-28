package com.opennice.selenium.listener;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.collections.Lists;
import org.testng.collections.Maps;
import org.testng.internal.Utils;
import org.testng.internal.annotations.Sets;
import org.testng.reporters.XMLConstants;
import org.testng.reporters.XMLStringBuffer;
import org.testng.xml.XmlSuite;

/**
 * 用于生成一个简易JUnit格式报告的IReporter。<br />
 * 适用于TestNG配置中只包含一个Test的情况。<br />
 * 默认输出到TestNG配置的output目录/junit-results.xml
 * @author liulxiang
 *
 */
public class SingleTestJUnitReporter implements IReporter, ITestListener {

	private class InnerTestResult {
		public ITestResult result;
		public int invocationCount;

		public InnerTestResult(ITestResult testResult, int invocationCount) {
			this.result = testResult;
			this.invocationCount = invocationCount;
		}

		public String toString() {
			return String.format("Class: %s, Method: %s, InvocationCount: %s", this.result.getInstance().getClass().getName(), this.result
							.getMethod().getMethodName(), this.invocationCount);
		}
	}

	private class InnerTestResultComparator implements Comparator<InnerTestResult> {
		@Override
		public int compare(InnerTestResult o1, InnerTestResult o2) {
			return o1.invocationCount - o2.invocationCount;
		}
	}

	private HashMap<Class<?>, HashMap<Method, List<InnerTestResult>>> resultMap = new HashMap<Class<?>, HashMap<Method, List<InnerTestResult>>>();
	private InnerTestResultComparator comparator = new InnerTestResultComparator();
	
    /**
     * 默认的输出文件名
     */
    private static final String DEFAULT_OUTPUT_FILE = "junit-results.xml";

    private class TestTag {
        public Properties properties;
        public String message;
        public String type;
        public String stackTrace;
        public String errorTag;
    }

	@SuppressWarnings("deprecation")
	@Override
	public void onTestStart(ITestResult testResult) {
		InnerTestResult innerResult = new InnerTestResult(testResult, testResult.getMethod().getCurrentInvocationCount());

		Class<?> testClass = testResult.getInstance().getClass();
		Method testMethod = testResult.getMethod().getMethod();

		if (!resultMap.containsKey(testClass)) {
			resultMap.put(testClass, new HashMap<Method, List<InnerTestResult>>());
		}

		HashMap<Method, List<InnerTestResult>> methodMap = resultMap.get(testClass);
		if (!methodMap.containsKey(testMethod)) {
			methodMap.put(testMethod, new ArrayList<InnerTestResult>());
		}

		List<InnerTestResult> testResultList = methodMap.get(testMethod);
		testResultList.add(innerResult);
	}

    @SuppressWarnings("deprecation")
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String defaultOutputDirectory) {
        Map<Class<?>, Set<ITestResult>> failedConfigurations = Maps.newHashMap();
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                addResults(tc.getFailedConfigurations().getAllResults(), failedConfigurations);
            }
        }

        XMLStringBuffer xsb = new XMLStringBuffer();
        xsb.addComment("Generated by " + getClass().getName());
        xsb.push(XMLConstants.TESTSUITES);

        for (Map.Entry<Class<?>, HashMap<Method, List<InnerTestResult>>> testClassEntry : this.resultMap.entrySet()) {
            Class<?> cls = testClassEntry.getKey();
            Properties p1 = new Properties();
            Date timeStamp = Calendar.getInstance().getTime();
            p1.setProperty(XMLConstants.ATTR_TIMESTAMP, timeStamp.toGMTString());

            List<TestTag> testCases = Lists.newArrayList();
            int failures = 0;
            int errors = 0;
            int testCount = 0;
            float totalTime = 0;

            HashMap<Method, List<InnerTestResult>> testMethodResultMap = testClassEntry.getValue();
            for (Map.Entry<Method, List<InnerTestResult>> testMethodEntry : testMethodResultMap.entrySet()) {
            	List<InnerTestResult> resultList = testMethodEntry.getValue();
            	Collections.sort(resultList, this.comparator);
            	
                for (InnerTestResult innerTestResult : resultList) {
                	ITestResult tr = innerTestResult.result;
                    TestTag testTag = new TestTag();

                    boolean isSuccess = tr.getStatus() == ITestResult.SUCCESS;
                    if (!isSuccess) {
                        if (tr.getThrowable() instanceof AssertionError) {
                            errors++;
                        } else {
                            failures++;
                        }
                    }

                    Properties p2 = new Properties();
                    p2.setProperty(XMLConstants.ATTR_CLASSNAME, cls.getName());
                    p2.setProperty(XMLConstants.ATTR_NAME, getTestName(tr));
                    long time = tr.getEndMillis() - tr.getStartMillis();
                    p2.setProperty(XMLConstants.ATTR_TIME, "" + formatTime(time));
                    Throwable t = getThrowable(tr, failedConfigurations);
                    if (!isSuccess && t != null) {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        t.printStackTrace(pw);
                        testTag.message = t.getMessage();
                        testTag.type = t.getClass().getName();
                        testTag.stackTrace = sw.toString();
                        testTag.errorTag = tr.getThrowable() instanceof AssertionError ? "error" : "failure";
                    }
                    totalTime += time;
                    testCount++;
                    testTag.properties = p2;
                    testCases.add(testTag);
                }
            }

            p1.setProperty(XMLConstants.ATTR_FAILURES, "" + failures);
            p1.setProperty(XMLConstants.ATTR_ERRORS, "" + errors);
            p1.setProperty(XMLConstants.ATTR_NAME, cls.getSimpleName());
            p1.setProperty(XMLConstants.ATTR_PACKAGE, cls.getPackage().getName());
            p1.setProperty(XMLConstants.ATTR_TESTS, "" + testCount);
            p1.setProperty(XMLConstants.ATTR_TIME, "" + formatTime(totalTime));
            try {
                p1.setProperty(XMLConstants.ATTR_HOSTNAME, InetAddress.getLocalHost().getHostName());
            } catch (UnknownHostException e) {
                // ignore
            }

            //
            // Now that we have all the information we need, generate the file
            //

            xsb.push(XMLConstants.TESTSUITE, p1);
            for (TestTag testTag : testCases) {
                if (testTag.stackTrace == null) {
                    xsb.addEmptyElement(XMLConstants.TESTCASE, testTag.properties);
                } else {
                    xsb.push(XMLConstants.TESTCASE, testTag.properties);

                    Properties p = new Properties();
                    if (testTag.message != null) {
                        p.setProperty(XMLConstants.ATTR_MESSAGE, testTag.message);
                    }
                    p.setProperty(XMLConstants.ATTR_TYPE, testTag.type);
                    xsb.push(testTag.errorTag, p);
                    xsb.addCDATA(testTag.stackTrace);
                    xsb.pop(testTag.errorTag);

                    xsb.pop(XMLConstants.TESTCASE);
                }
            }
            xsb.pop(XMLConstants.TESTSUITE);
        }
        
        xsb.pop(XMLConstants.TESTSUITES);
        Utils.writeUtf8File(defaultOutputDirectory, getOutputFileName(), xsb.toXML());
    }

    protected String getOutputFileName() {
        return DEFAULT_OUTPUT_FILE;
    }

    protected String getTestName(ITestResult testResult) {
        return testResult.getMethod().getMethodName(); 
    }

    private String formatTime(float time) {
        DecimalFormat format = new DecimalFormat("#.###");
        format.setMinimumFractionDigits(3);
        return format.format(time / 1000.0f);
    }

    private Throwable getThrowable(ITestResult tr, Map<Class<?>, Set<ITestResult>> failedConfigurations) {
        Throwable result = tr.getThrowable();
        if (result == null && tr.getStatus() == ITestResult.SKIP) {
            // Attempt to grab the stack trace from the configuration failure
            for (Set<ITestResult> failures : failedConfigurations.values()) {
                for (ITestResult failure : failures) {
                    // Naive implementation for now, eventually, we need to try to find
                    // out if it's this failure that caused the skip since (maybe by
                    // seeing if the class of the configuration method is assignable to
                    // the class of the test method, although that's not 100% fool proof
                    if (failure.getThrowable() != null) {
                        return failure.getThrowable();
                    }
                }
            }
        }

        return result;
    }

    private void addResults(Set<ITestResult> allResults, Map<Class<?>, Set<ITestResult>> out) {
        for (ITestResult tr : allResults) {
            Class<?> cls = tr.getMethod().getTestClass().getRealClass();
            Set<ITestResult> l = out.get(cls);
            if (l == null) {
                l = Sets.newHashSet();
                out.put(cls, l);
            }
            l.add(tr);
        }
    }
    
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
}
