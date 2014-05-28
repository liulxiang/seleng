package com.opennice.common;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.sun.jna.WString;

/**
 * 
 */

public class HttpWatchUtil {
	private static Autoitx lib = Autoitx.INSTANCE;

	public static void clickRecord(WebDriver driver) {
		Selenium2Proxy s2p = new Selenium2Proxy(driver);
		s2p.getKeyboard().sendKeys(Keys.CONTROL, Keys.F2);

	}

	public static void startHttpWatchInIE(WebDriver driver) {
		Selenium2Proxy s2p = new Selenium2Proxy(driver);
		s2p.getKeyboard().sendKeys(Keys.SHIFT, Keys.F2);
	}

	public static void startHttpWatchInBrowser(WebDriver driver,
			int pressArrowDownKeyTimes) {
		Selenium2Proxy s2p = new Selenium2Proxy(driver);
		s2p.getKeyboard().sendKeys(Keys.ALT, "T");
		for (int i = 0; i < pressArrowDownKeyTimes; i++) {
			s2p.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		}
		s2p.getKeyboard().sendKeys(Keys.ENTER);
	}

	public static void clickStop(WebDriver driver) {
		Selenium2Proxy s2p = new Selenium2Proxy(driver);
		s2p.getKeyboard().sendKeys(Keys.CONTROL, Keys.F3);
	}

	public static void clickClear(WebDriver driver) {
		Selenium2Proxy s2p = new Selenium2Proxy(driver);
		s2p.getKeyboard().sendKeys(Keys.CONTROL, Keys.DELETE);
	}

	public static void clickSave(WebDriver driver) {
		Selenium2Proxy s2p = new Selenium2Proxy(driver);
		s2p.getKeyboard().sendKeys(Keys.CONTROL, Keys.SHIFT, "S");
	}

	public static void export2Har(WebDriver driver) {
		lib.AU3_Send(new WString("^+h"), 0);
	}

	public static void saveHttpWatchLog(String logFileName) {
		String saveWindowTitle = "HttpWatch - Save";
		lib.AU3_WinWaitActive(new WString(saveWindowTitle), new WString(""),
				10000);
		lib.AU3_ControlSend(new WString(saveWindowTitle), new WString(""),
				new WString("Edit1"), new WString(logFileName), 1);
		lib.AU3_ControlClick(new WString(saveWindowTitle), new WString(""),
				new WString("[CLASS:Button; TEXT:保存(&S)]"), new WString(
						"left"), 1, Autoitx.AU3_INTDEFAULT,
				Autoitx.AU3_INTDEFAULT);
	}

	public static void exportHttpWatchLog(String logFileName) {
		String exportWindowTitle = "HttpWatch - Export";
		lib.AU3_WinWaitActive(new WString(exportWindowTitle), new WString(""),
				10000);
		lib.AU3_WinActive(new WString(exportWindowTitle), new WString(""));
		lib.AU3_Send(new WString(logFileName + "\n"), 1);
	}
}
