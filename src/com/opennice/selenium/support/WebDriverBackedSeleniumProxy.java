package com.opennice.selenium.support;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

/**
 * WebDriverBackedSelenium对象的一个管理器，采用和WebDriver对象一一匹配的方式来管理。
 * WebDriverBackedSelenium对象比较占内存，不适宜大量创建并缓存。
 * @author liulxiang
 *
 */
public class WebDriverBackedSeleniumProxy {
	protected static HashMap<Integer, WebDriverBackedSelenium> seleniumInstanceMap
									= new HashMap<Integer, WebDriverBackedSelenium>();
	
	/**
	 * 获取一个WebDriverBackedSelenium对象。
	 * @author liulxiang
	 * @param driver
	 * @return NULL if driver is null, or a cached WebDriverBackedSelenium object.
	 */
	public static WebDriverBackedSelenium getSelenium(WebDriver driver) {
		if (null == driver) {
			return null;
		}
		
		Integer key = driver.hashCode();
		if (!seleniumInstanceMap.containsKey(key)) {
			seleniumInstanceMap.put(key, new WebDriverBackedSelenium(driver, ""));
		}
		
		return seleniumInstanceMap.get(key);
	}
	
	/**
	 * 从缓存的Selenium对象池中移除
	 * @author liulxiang
	 * @param driver
	 */
	public static void unregisterSelenium(WebDriver driver) {
		if (null == driver) {
			return;
		}
		Integer key = driver.hashCode();
		if (seleniumInstanceMap.containsKey(key)) {
			seleniumInstanceMap.remove(key);
		}
	}
}

