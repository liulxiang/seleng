package com.opennice.common;

import java.io.IOException;

import com.sun.jna.WString;

/**
 * 利用CamStudio来录像
 * 
 * 
 */
public class CamStudioClient {
	private static Autoitx lib = Autoitx.INSTANCE;
	private String camStudioFullPath;
	private String camStudioTitle = "CamStudio";
	private String saveAVIFileTitle = "Save AVI File";

	public CamStudioClient(String camStudioFullPath) {
		this.setCamStudioFullPath(camStudioFullPath);
	}

	public void startCamStudio() {
		try {
			Runtime.getRuntime().exec(this.camStudioFullPath);
			lib.AU3_WinWaitActive(new WString(camStudioTitle), new WString(""),
					3000);
			lib.AU3_WinActive(new WString(camStudioTitle), new WString(""));

			int width = lib.AU3_WinGetPosWidth(new WString(
					camStudioTitle), new WString(""));
			int height = lib.AU3_WinGetPosHeight(new WString(
					camStudioTitle), new WString(""));
			lib.AU3_WinMove(new WString(camStudioTitle), new WString(""), 0, 0,
					width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeCamStudio() {
		lib.AU3_WinWaitActive(new WString(camStudioTitle), new WString(""),
				3000);
		lib.AU3_WinActive(new WString(camStudioTitle), new WString(""));
		lib.AU3_WinClose(new WString(camStudioTitle), new WString(""));
	}

	public void minimizeWindow() {
		lib.AU3_WinWaitActive(new WString(camStudioTitle), new WString(""),
				3000);
		lib.AU3_WinActive(new WString(camStudioTitle), new WString(""));
		lib.AU3_WinMinimizeAll();
	}

	public void clickRecord() {
		lib.AU3_WinWaitActive(new WString(camStudioTitle), new WString(""),
				3000);
		lib.AU3_WinActive(new WString(camStudioTitle), new WString(""));
		lib.AU3_Send(new WString("{F8}"), 0);
	}

	public void clickStop() {
		lib.AU3_WinWaitActive(new WString(camStudioTitle), new WString(""),
				3000);
		lib.AU3_WinActive(new WString(camStudioTitle), new WString(""));
		lib.AU3_Send(new WString("{F9}"), 0);
	}

	public void saveAVIFile(String fileName) {
		lib.AU3_WinWaitActive(new WString(saveAVIFileTitle), new WString(""),
				3000);
		lib.AU3_WinActive(new WString(saveAVIFileTitle), new WString(""));
		lib.AU3_ControlSend(new WString(saveAVIFileTitle), new WString(""),
				new WString("Edit1"), new WString(fileName), 1);
		lib.AU3_ControlClick(new WString(saveAVIFileTitle), new WString(""),
				new WString("[CLASS:Button; TEXT:保存(&S)]"),
				new WString("left"), 1, Autoitx.AU3_INTDEFAULT,
				Autoitx.AU3_INTDEFAULT);
		lib.AU3_Sleep(3000);
	}

	/**
	 * @param camStudioFullPath
	 *            the camStudioFullPath to set
	 */
	public void setCamStudioFullPath(String camStudioFullPath) {
		this.camStudioFullPath = camStudioFullPath;
	}

	/**
	 * @return the camStudioFullPath
	 */
	public String getCamStudioFullPath() {
		return camStudioFullPath;
	}

}
