package com.opennice.selenium.screenshot;

/**
 * 进行截图的接口
 * @author liulxiang
 *
 */
public interface IScreenshotTaker {
	/**
	 * 进行截图
	 * @return 截图的二进制数据
	 */
	public byte[] getScreenshot();
}
