package com.opennice.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UIWebElementUtil {
	public static boolean isWebElementDisplayed(WebDriver driver, By by,
			int waitTime, int retry) {
		boolean result = false;

		WebElement we = driver.findElement(by);
		for (int i = 0; i < retry; i++) {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			result = we.isDisplayed();
			if (result)
				break;
		}

		return result;
	}

	public static boolean isWebElementDisplayed(WebDriver driver, By by) {
		return UIWebElementUtil.isWebElementDisplayed(driver, by, 100, 3);
	}

	public static boolean isWebElementExist(WebDriver driver, By by,
			int waitTime, int retry) {

		boolean result = false;

		for (int i = 0; i < retry; i++) {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				result = true;
				driver.findElement(by);
			} catch (NoSuchElementException ex) {
				result = false;
			}

			if (result)
				break;
		}

		return result;
	}

	public static boolean isWebElementExist(WebDriver driver, By by) {
		return UIWebElementUtil.isWebElementExist(driver, by, 100, 3);
	}

	public static boolean isWebElementInScreen(WebDriver driver, By by,
			int waitTime, int retry) {
		boolean result = false;

		WebElement we = driver.findElement(by);
		for (int i = 0; i < retry; i++) {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String style = we.getAttribute("style");
			String left = style.substring(style.indexOf("left:"))
					.replace("left:", "").trim();
			result = left.startsWith("-") ? false : true;

			if (result)
				break;
		}

		return result;
	}

	public static boolean isWebElementInScreen(WebDriver driver, By by) {
		return UIWebElementUtil.isWebElementInScreen(driver, by, 100, 3);
	}
}
