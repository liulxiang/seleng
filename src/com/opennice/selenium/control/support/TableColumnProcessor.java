package com.opennice.selenium.control.support;

import java.util.Hashtable;

/**
 * 表格列的处理类
 * @author liulxiang
 *
 * @param <T>
 */
public class TableColumnProcessor<T extends TableCellProcessor> {
	private Class<T> cellProcessorClass;
	private ITableColumn column;
	private Hashtable<Integer, T> cache;
	
	public TableColumnProcessor(ITableColumn column, Class<T> cellProcessorClass) {
		if (null == column) {
			throw new IllegalArgumentException("Arg column can not be null.");
		}

		if (null == cellProcessorClass) {
			throw new IllegalArgumentException("Arg cellProcessorClass can not be null.");
		}

		this.cellProcessorClass = cellProcessorClass;
		this.column = column;
		this.cache = new Hashtable<Integer, T>();
	}
	
	/**
	 * 获取单元格的处理类
	 * 根据rowIndex查找匹配的单元格
	 * @author liulxiang
	 * @param rowIndex
	 * @return
	 */
	public T getRow(int rowIndex) {
		if (!cache.containsKey(rowIndex)) {
			cache.put(rowIndex, TableCellProcessor.createInstance(column.getCell(rowIndex), cellProcessorClass));
		}
		
		return cache.get(rowIndex);
	}
	
	/**
	 * 获取行数
	 * @author liulxiang
	 * @return
	 */
	public int getRowsCount() {
		return column.getRowsCount();
	}
}
