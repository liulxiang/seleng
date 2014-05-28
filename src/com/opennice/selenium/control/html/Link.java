package com.opennice.selenium.control.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opennice.selenium.basic.HtmlAttributes;
import com.opennice.selenium.control.Control;
import com.opennice.selenium.control.LazyLoadControl;

/**
 * a元素，超链接
 * @author liulxiang
 *
 */
@LazyLoadControl
public class Link extends Control {

	protected Link() {
		// empty for LazyLoad
	}

	public Link(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public Link(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public Link(WebDriver driver, String xpath, String flag) {
		super(driver, xpath, flag);
		// TODO Auto-generated constructor stub
	}
	
	public Link(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取链接地址
	 * @author liulxiang
	 * @return
	 */
	public String getHref() {
		return getAttribute(HtmlAttributes.HREF);
	}

	/**
	 * 获取链接的目标窗口
	 * @author liulxiang
	 * @return
	 */
	public String getTarget() {
		return getAttribute(HtmlAttributes.TARGET);
	}

}
