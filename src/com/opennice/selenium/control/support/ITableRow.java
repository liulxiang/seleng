package com.opennice.selenium.control.support;

import com.opennice.selenium.control.IControl;

/**
 * 表格行的抽象接口
 * @author liulxiang
 *
 */
public interface ITableRow extends IControl {
	/**
	 * 获取单元格
	 * @author liulxiang
	 * @param columnIndex 列号，从0开始
	 * @return
	 */
	public ITableCell getCell(int columnIndex);
	
	/**
	 * 获取列数
	 * @author liulxiang
	 * @return
	 */
	public int getColumnsCount();
	
	/**
	 * 获取当前行号。
	 * @author liulxiang
	 * @return 从0开始的行号
	 */
	public int getRowIndex();
}
