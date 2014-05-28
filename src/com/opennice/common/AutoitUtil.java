package com.opennice.common;

import com.sun.jna.WString;

public class AutoitUtil {
	private static Autoitx lib = Autoitx.INSTANCE;

	public static void uploadImg(String path, int x, int y) {
		lib.AU3_MouseClick(new WString("left"), x, y, 2, 1000);
		lib.AU3_WinActivate(new WString(
				"选择要上载的文件，通过: host"),
				new WString(""));
		lib.AU3_WinWaitActive(new WString(
				"选择要上载的文件，通过: host"),
				new WString(""), 3000);
		lib.AU3_Sleep(1000);
		lib.AU3_Send(new WString(path), 1);
		lib.AU3_Sleep(1000);
		lib.AU3_Send(new WString("\n"), 1);
	}

	public static void clickFilterButton(int x, int y) {
		lib.AU3_MouseClick(new WString("left"), x, y, 2, 0);
	}
}
