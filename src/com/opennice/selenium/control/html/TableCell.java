package com.opennice.selenium.control.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opennice.selenium.control.Control;
import com.opennice.selenium.control.support.ITableCell;

/**
 * 表格单元格，适用于&lt;td&gt;或&lt;th&gt;元素
 * @author liulxiang
 *
 */
public class TableCell extends Control implements ITableCell {

	public TableCell() {
		// TODO Auto-generated constructor stub
	}

	public TableCell(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public TableCell(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public TableCell(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

}
