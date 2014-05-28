package com.opennice.selenium.control.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opennice.selenium.basic.HtmlAttributes;
import com.opennice.selenium.control.Control;
import com.opennice.selenium.control.LazyLoadControl;

/**
 * 输入框, input type="text"
 * @author liulxiang
 *
 */
@LazyLoadControl
public class TextBox extends Control {

	protected TextBox() {
		// empty for LazyLoad
	}

	public TextBox(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public TextBox(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}
	
	public TextBox(WebDriver driver, String xpath, String flag) {
		super(driver, xpath, flag);
		// TODO Auto-generated constructor stub
	}
	
	public TextBox(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 设置输入框中的文字，会删除原来的文字
	 * @author liulxiang
	 * @param text
	 */
	public void setText(String text) {
		this.clear();
		this.pause();
		this.sendKeys(text);
	}
	
	/**
	 * 在输入框中追加一段文字
	 * @author liulxiang
	 * @param text
	 */
	public void appendText(String text) {
		this.sendKeys(text);
	}
	
	@Override
	public String getText() {
		return getAttribute(HtmlAttributes.VALUE);
	}

}
