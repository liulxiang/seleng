package com.opennice.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class S2TableUtil {

	public static int getNumberOfRowsInTable(String tableId, WebDriver driver) {
		return driver.findElement(By.id(tableId))
				.findElements(By.tagName("tr")).size();
	}

	public static int getNumberOfRowsInTable(By by, WebDriver driver) {
		return driver.findElement(by).findElements(By.tagName("tr")).size();
	}

	public static int getNumberOfColsInTable(By by, WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(
				By.tagName("tr"));
		WebElement row = rows.get(0);
		return row.findElements(By.tagName("td")).size();
	}

	public static String getCellText(By by, int rowIndex, int colIndex,
			WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(
				By.tagName("tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		WebElement col = cols.get(colIndex);
		return col.getText();
	}

	public static String getCellValue(By by, int rowIndex, int colIndex,
			WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(
				By.tagName("tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		WebElement col = cols.get(colIndex);
		return col.getText();
	}

	public static String getCellInnerHTML(By by, int rowIndex, int colIndex,
			WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(
				By.tagName("tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		WebElement col = cols.get(colIndex);
		System.out.println("HTML: " + col.getAttribute("innerHTML"));
		return col.getAttribute("innerHTML");
	}

	public static int getRowIndexInTable(By by, int colIndex, String query,
			WebDriver driver) {
		int result = -1;
		List<WebElement> rows = driver.findElement(by).findElements(
				By.tagName("tr"));
		int rowIndex = 0;
		for (WebElement row : rows) {
			if (query.equals(row.findElements(By.tagName("td")).get(colIndex)
					.getText())) {
				result = rowIndex;
				break;
			}
			rowIndex++;
		}

		return result;
	}

	public static WebElement getCellSubElement(By tableBy, int rowIndex, int colIndex,
			By cellSubElementBy, WebDriver driver) {
		List<WebElement> rows = driver.findElement(tableBy).findElements(
				By.tagName("tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		return cols.get(colIndex).findElement(cellSubElementBy);
	}

	public static void clickCell(By by, int rowIndex, int colIndex, WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(
				By.tagName("tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		cols.get(colIndex).click();
	}
}
