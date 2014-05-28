package com.opennice.selenium.control.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opennice.selenium.basic.HtmlAttributes;
import com.opennice.selenium.control.Control;
import com.opennice.selenium.control.LazyLoadControl;

/**
 * 按钮, input type="button"
 * @author liulxiang
 *
 */
@LazyLoadControl
public class Button extends Control {

	protected Button() {
		// empty for LazyLoad
	}
	
	public Button(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public Button(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}
	
	public Button(WebDriver driver, String xpath, String flag) {
		super(driver, xpath, flag);
		// TODO Auto-generated constructor stub
	}
	
	public Button(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public String getText(){
		return wrappedElement.getAttribute(HtmlAttributes.VALUE);
	}
}
