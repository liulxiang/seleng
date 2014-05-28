package com.opennice.selenium.control.html;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import com.opennice.selenium.basic.HtmlTags;
import com.opennice.selenium.control.Control;
import com.opennice.selenium.control.support.ITableCell;
import com.opennice.selenium.control.support.ITableColumn;

/**
 * 表格列，并没有真正对应的元素，列是一个虚拟概念。
 * @author liulxiang
 *
 */
public class TableColumn extends Control implements ITableColumn {
	
	private int columnIndex;
	
	public TableColumn(WebElement tableBodyElement, int columnIndex) {
		super(tableBodyElement);

		this.columnIndex = columnIndex;
	}
	
	/**
	 * 获取单元格
	 * @author liulxiang
	 * @param rowIndex 行号，从0开始
	 * @return 以TableCell封装好的&lt;td&gt;或&lt;th&gt;元素
	 */
	@Override
	public ITableCell getCell(int rowIndex) {
		// xpath中数组下标从1开始
		int row = rowIndex + 1;
		int column = this.columnIndex + 1;
		String xpath = String.format(Table.CELL_XPATH, row, column);
		return new TableCell(this.findElement(By.xpath(xpath)));
	}

	/**
	 * 获取行数。
	 * @author liulxiang
	 * @return
	 */
	@Override
	public int getRowsCount() {
		return this.getChildNodesCountByTagName(this, HtmlTags.TR);
	}

	/**
	 * 获取所有单元格
	 * @author liulxiang
	 * @return
	 */
	@Override
	public List<ITableCell> getAllCells() {
		List<ITableCell> result = new ArrayList<ITableCell>();
		
		for (int i = 0; ;i++) {
			try {
				result.add(this.getCell(i));
			}
			catch (NotFoundException ex) {
				break;
			}
		}
		
		return result;
	}

	@Override
	public int getColumnIndex() {
		return this.columnIndex;
	}
}
