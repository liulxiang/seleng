package com.opennice.selenium.control.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opennice.selenium.control.Control;
import com.opennice.selenium.control.LazyLoadControl;

/**
 * 包装普通的行级元素，如span, b等
 * @author liulxiang
 *
 */
@LazyLoadControl
public class LineElement extends Control {

	protected LineElement() {
		// empty for LazyLoad
	}

	public LineElement(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public LineElement(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public LineElement(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

}
