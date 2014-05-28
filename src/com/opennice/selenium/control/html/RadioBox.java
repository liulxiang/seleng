package com.opennice.selenium.control.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opennice.selenium.control.Control;
import com.opennice.selenium.control.LazyLoadControl;

/**
 * 单选框, input type="radio"
 * @author liulxiang
 *
 */
@LazyLoadControl
public class RadioBox extends Control {

	protected RadioBox() {
		// empty for LazyLoad
	}

	public RadioBox(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public RadioBox(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}
	
	public RadioBox(WebDriver driver, String xpath, String flag) {
		super(driver, xpath, flag);
		// TODO Auto-generated constructor stub
	}
	
	public RadioBox(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

}
