package com.opennice.selenium.control.support;

/**
 * 在表格中定位一行时使用的接口
 * @author liulxiang
 *
 */
public interface ITableRowLocator {
	/**
	 * 判断是否是所要找的行
	 * @param row 表格行
	 * @return true代表是要查找的行，否则false
	 */
	boolean isTarget(ITableRow row);
}
