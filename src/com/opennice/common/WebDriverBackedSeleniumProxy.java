package com.opennice.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

public class WebDriverBackedSeleniumProxy {
	private static WebDriverBackedSelenium seleniumInstance = null;
	private static WebDriver driverInstance = null;
	
	public static WebDriverBackedSelenium getSelenium(WebDriver driver) {
		if (null == driverInstance || !driver.equals(driverInstance)) {
			seleniumInstance = new WebDriverBackedSelenium(driver, "");
			driverInstance = driver;
		}
		
		return seleniumInstance;
	}
}

