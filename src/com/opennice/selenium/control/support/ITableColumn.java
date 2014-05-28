package com.opennice.selenium.control.support;

import java.util.List;

/**
 * 表格列的抽象接口
 * @author liulxiang
 *
 */
public interface ITableColumn {
	/**
	 * 获取单元格
	 * @author liulxiang
	 * @param rowIndex 行号，从0开始
	 * @return
	 */
	public ITableCell getCell(int rowIndex);
	
	/**
	 * 获取所有单元格
	 * @author liulxiang
	 * @return
	 */
	public List<ITableCell> getAllCells();
	
	/**
	 * 获取行数
	 * @author liulxiang
	 * @return
	 */
	public int getRowsCount();
	
	/**
	 * 获取当前列号。
	 * @author liulxiang
	 * @return 从0开始的列号
	 */
	public int getColumnIndex();
}
