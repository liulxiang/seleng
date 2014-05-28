package com.opennice.selenium.control.er;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import com.opennice.selenium.basic.CssSelector;
import com.opennice.selenium.basic.HtmlAttributes;
import com.opennice.selenium.control.Control;
import com.opennice.selenium.control.html.TableCell;
import com.opennice.selenium.control.support.ITableCell;
import com.opennice.selenium.control.support.ITableColumn;

/**
 * ER框架中TableColumn的封装。
 * @author liulxiang
 *
 */
public class ErTableColumn extends Control implements ITableColumn {
	private int columnIndex;
	private String tableId;
	private String cellIdFormat;
	
	public ErTableColumn(WebElement tableBodyElement, String tableId, int columnIndex) {
		super(tableBodyElement);

		this.tableId = tableId;
		this.columnIndex = columnIndex;
		this.cellIdFormat = this.tableId + ErTable.BODY_CELL_POSTFIX + "%s_" + String.valueOf(this.columnIndex);
	}

	@Override
	public ITableCell getCell(int rowIndex) {
		return new TableCell(this.findElement(By.id(String.format(cellIdFormat, rowIndex))));
	}

	/**
	 * 获取行数。<br />
	 * 该实现通过findElements实现，会一次查找到所有节点，性能不佳，不建议多次调用。
	 * @author liulxiang
	 * @return
	 */
	@Override
	public int getRowsCount() {
		return this.findElements(By.cssSelector(CssSelector.byAttributeStartsWith(HtmlAttributes.ID, this.tableId + ErTable.BODY_ROW_POSTFIX))).size();
	}

	@Override
	public List<ITableCell> getAllCells() {
		List<ITableCell> result = new ArrayList<ITableCell>();
		
		for (int i = 0; ; i++) {
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
