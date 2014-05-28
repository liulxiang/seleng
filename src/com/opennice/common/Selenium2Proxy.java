package com.opennice.common;

import java.io.File;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Keyboard;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 *
 */
public class Selenium2Proxy {
	private WebDriver driver;

	public Selenium2Proxy(WebDriver driver) {
		this.driver = driver;
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void back() {
		driver.navigate().back();
	}

	public void getCurrentUrl() {
		driver.getCurrentUrl();
	}

	public void getTitle() {
		driver.getTitle();
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void captureScreenshot(String fileName) {
		TakesScreenshot tsDriver = (TakesScreenshot) driver;
		File image = new File(fileName);
		tsDriver.getScreenshotAs(OutputType.FILE).renameTo(image);
	}

	public void switchToWindow(String windowTitle) {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handler : windowHandles) {
			driver.switchTo().window(handler);
			String title = driver.getTitle();
			if (windowTitle.equals(title)) {
				break;
			}
		}
	}

	public void switchToFrame(String frameId) {
		driver.switchTo().frame(frameId);
	}
	
	private Alert switchToAlert() {
		return driver.switchTo().alert();
	}
	
	public void acceptAlert(){
		switchToAlert().accept();
	}

	public void dismissAlert(){
		switchToAlert().dismiss();
	}
	
	public Mouse getMouse() {
		return ((HasInputDevices) driver).getMouse();
		// mouse.mouseDown(((Locatable)weSecNav).getCoordinates());
	}
	
	public Keyboard getKeyboard(){
		return ((HasInputDevices) driver).getKeyboard();
		//getKeyboard().pressKey(Keys.CONTROL);
	}
	
	public void refreshWithCtrlF5(){
		getKeyboard().sendKeys(Keys.CONTROL, Keys.F5);
	}
}
