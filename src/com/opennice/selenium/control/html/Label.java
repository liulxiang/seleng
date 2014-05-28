package com.opennice.selenium.control.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opennice.selenium.control.Control;
import com.opennice.selenium.control.LazyLoadControl;

/**
 * label元素
 * @author liulxiang
 *
 */
@LazyLoadControl
public class Label extends Control {

	protected Label() {
		// empty for LazyLoad
	}

	public Label(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public Label(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}
	
	public Label(WebDriver driver, String xpath, String flag) {
		super(driver, xpath, flag);
		// TODO Auto-generated constructor stub
	}

	public Label(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

}
