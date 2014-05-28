package com.opennice.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opennice.selenium.support.internal.DefaultDriverFactory;

/**
 * 获得WebDriver的工厂类
 * 
 * 
 */
public class WebDriverFactory extends DefaultDriverFactory {
	@Override
	public WebDriver getDriver() {
		WebDriver driver = super.getDriver();
		// 基于获取到的Driver, 添加EventFiring代理
		EventFiringWebDriver efDriver = new EventFiringWebDriver(driver);
		efDriver.register(new BDWebDriverEventListener());
		return efDriver;
	}

	private class BDWebDriverEventListener implements WebDriverEventListener {

		public void beforeNavigateTo(String url, WebDriver driver) {

		}

		public void afterNavigateTo(String url, WebDriver driver) {

		}

		public void beforeNavigateBack(WebDriver driver) {

		}

		public void afterNavigateBack(WebDriver driver) {

		}

		public void beforeNavigateForward(WebDriver driver) {

		}

		public void afterNavigateForward(WebDriver driver) {

		}

		public void beforeFindBy(By by, WebElement element, WebDriver driver) {

		}

		public void afterFindBy(By by, WebElement element, WebDriver driver) {

		}

		public void beforeClickOn(WebElement element, WebDriver driver) {

		}

		public void afterClickOn(WebElement element, WebDriver driver) {
			UIWebDriverWait wait = new UIWebDriverWait(driver,
					WebDriverWait.DEFAULT_SLEEP_TIMEOUT);

			wait.sleep(200);
			wait.waitUntilPageLoaded(driver);
		}

		public void beforeChangeValueOf(WebElement element, WebDriver driver) {
			UIWebDriverWait wait = new UIWebDriverWait(driver,
					WebDriverWait.DEFAULT_SLEEP_TIMEOUT);

			wait.sleep(200);
		}

		public void afterChangeValueOf(WebElement element, WebDriver driver) {

		}

		public void beforeScript(String script, WebDriver driver) {

		}

		public void afterScript(String script, WebDriver driver) {

		}

		public void onException(Throwable throwable, WebDriver driver) {
			Date date = new Date();
			String suffix = new SimpleDateFormat("yyyyMMddHHmmss").format(date);

			if (driver instanceof TakesScreenshot) {
				TakesScreenshot ts = (TakesScreenshot) driver;

				File screenshotDir = new File("log/screenshots");

				if (!screenshotDir.exists()) {
					screenshotDir.mkdirs();
				}

				ts.getScreenshotAs(OutputType.FILE).renameTo(
						new File(screenshotDir, "screenshot-" + "-" + suffix
								+ ".jpg"));
			}

			File errorLog = new File("log/exceptions.log");
			FileWriter fw = null;
			try {
				fw = new FileWriter(errorLog, true);
				fw.append(suffix + ":\n");
				fw.append(throwable.getMessage());
				fw.append("\n\n");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fw != null) {
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
