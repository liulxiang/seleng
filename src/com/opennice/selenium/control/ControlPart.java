package com.opennice.selenium.control;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;

/**
 * 将控件拆分成Part的概念，便于组合
 * @author liulxiang
 *
 * @param <ParentType>
 */
public abstract class ControlPart<ParentType extends Control> implements WrapsDriver {
	protected WebDriver driver;
	protected ParentType parentControl;
	
	public ControlPart(ParentType parent) {
		if (null == parent) {
			throw new IllegalArgumentException("Arg parent can not be null.");
		}
		parentControl = parent;
		driver = parent.getWrappedDriver();
	}
	
	/**
	 * 获取封装的driver对象
	 * @author liulxiang
	 * @return
	 */
	@Override
	public WebDriver getWrappedDriver() {
		return driver;
	}

	/**
	 * 获取封装的父控件
	 * @author liulxiang
	 * @return
	 */
	public ParentType getParentControl() {
		return parentControl;
	}
}
