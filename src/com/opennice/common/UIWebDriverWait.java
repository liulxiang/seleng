package com.opennice.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIWebDriverWait extends WebDriverWait {



	public static final int DEFAULT_TRY_TIMES = 50; //延长等待次数，之前是20

	public static final int DEFAULT_SLEEP_TIME = 500;

	public UIWebDriverWait(WebDriver driver, long timeOutInSeconds) {
		super(driver, timeOutInSeconds);
	}

	public UIWebDriverWait(WebDriver driver, long timeOutInSeconds,
			long sleepInMillis) {
		super(driver, timeOutInSeconds, sleepInMillis);
	}

	public void sleep(long timeInMillisecond) {
		try {
			Thread.sleep(timeInMillisecond);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void waitUntilElementInScreen(WebDriver driver, By by) {
		waitUntilElementInScreen(driver, by, DEFAULT_SLEEP_TIME,
				DEFAULT_TRY_TIMES);
	}

	public void waitUntilElementInScreen(WebDriver driver, By by,
			int timeInMillisecond, int tryTimes) {
		boolean inScreen = false;
		WebElement elem = driver.findElement(by);

		int i = 0;
		while (true) {
			try {
				Thread.sleep(timeInMillisecond);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String style = elem.getAttribute("style");
			String left = style.substring(style.indexOf("left:"))
					.replace("left:", "").trim();
			inScreen = left.startsWith("-") ? false : true;

			if (inScreen) {
				break;
			}

			if (i >= tryTimes) {
				throw new RuntimeException("WaitUntilElementVisible Timeout!");
			}

			i++;
		}
	}

	public void waitUntilElementOccur(WebDriver driver, By by) {
		waitUntilElementOccur(driver, by, DEFAULT_SLEEP_TIME, DEFAULT_TRY_TIMES);
	}

	public void waitUntilElementOccur(WebDriver driver, By by,
			int timeInMillisecond, int tryTimes) {
		int i = 0;
		while (true) {
			try {
				Thread.sleep(timeInMillisecond);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				driver.findElement(by);
				break;
			} catch (NoSuchElementException ex) {
				if (i >= tryTimes) {
					throw new RuntimeException("WaitUntilElementOccur Timeout!");
				}

				i++;
			}
		}
	}

	public void waitUntilElementVisible(WebDriver driver, By by) {
		waitUntilElementVisible(driver, by, DEFAULT_SLEEP_TIME,
				DEFAULT_TRY_TIMES);
	}

	public void waitUntilElementVisible(WebDriver driver, By by,
			int timeInMillisecond, int tryTimes) {
		WebElement elem = driver.findElement(by);

		int i = 0;
		while (true) {
			try {
				Thread.sleep(timeInMillisecond);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (elem.isDisplayed()) {
				break;
			}

			if (i >= tryTimes) {
				throw new RuntimeException("WaitUntilElementVisible Timeout!");
			}

			i++;
		}
	}

	public void waitUntilPageLoaded(WebDriver driver) {
		waitUntilPageLoaded(driver, DEFAULT_SLEEP_TIME, DEFAULT_TRY_TIMES);
	}

//	public void waitUntilPageLoaded(WebDriver driver, int timeInMillisecond,
//			int tryTimes) {
//		try {
//			Thread.sleep(timeInMillisecond); // 注意，必须先延时等待页面发生改动
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		WebElement we = driver.findElement(By.id("Loading"));
//
//		int i = 0;
//		while (true) {
//			if (we.getAttribute("style").equals("display: none;")
//					|| we.getAttribute("style").equals("display:none;")) {
//				break;
//			}
//
//			if (i >= tryTimes)
//				throw new RuntimeException("WaitForPageLoading Timeout!");
//
//			i++;
//
//			try {
//				Thread.sleep(timeInMillisecond);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
	
	public void waitUntilPageLoaded(WebDriver driver, int timeInMillisecond,
			int tryTimes) {
		try {
			Thread.sleep(timeInMillisecond); // 注意，必须先延时等待页面发生改动
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		By by = By.id("Loading");
		
//兼容老页面
		if (!UIWebElementUtil.isWebElementExist(driver, by)) {
			return;
		}

		WebElement we = driver.findElement(by);

		int i = 0;
		while (true) {
			if (we.getAttribute("style").equals("display: none;")
					|| we.getAttribute("style").equals("display:none;")
					|| we.getAttribute("style").equals("display: none; ")
					|| we.getAttribute("style").equals("DISPLAY: none")) {
				break;
			}

			if (i >= tryTimes)
				throw new RuntimeException("WaitForPageLoading Timeout!");

			i++;

			try {
				Thread.sleep(timeInMillisecond);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}



	public void waitUntilMaterUploaded(WebDriver driver) {
		waitUntilMaterUploaded(driver, DEFAULT_SLEEP_TIME, DEFAULT_TRY_TIMES);
	}

	public void waitUntilMaterUploaded(WebDriver driver, int timeInMillisecond,
			int tryTimes) {
		try {
			Thread.sleep(timeInMillisecond); // 注意，必须先延时等待页面发生改动
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int i = 0;
		while (true) {

			By by = By.xpath("//div[contains(@id, \"DialogLoading\")]");
			if (!UIWebElementUtil.isWebElementExist(driver, by)
					|| !UIWebElementUtil.isWebElementInScreen(driver, by)) {
				break;
			}

			if (i >= tryTimes)
				throw new RuntimeException("WaitForMaterUpload Timeout!");

			i++;

			try {
				Thread.sleep(timeInMillisecond);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
