package com.opennice.selenium.thirdparty.autoit3;

import java.io.File;

import com.opennice.selenium.common.ConfigurationSettings;
import com.sun.jna.Native;

/**
 * 通过JNA创建AutoItX对象的类
 * 
 * @author liulxiang
 *
 */
public class AutoItXFactory {
	private static AutoItX INSTANCE;
	
	static {
		String path = System.getProperty("user.dir") + File.pathSeparator + ConfigurationSettings.THIRDPARTY_AUTOIT3_DLL;
		File file = new File(path);
		
		if (!file.isFile()) {
			throw new RuntimeException(String.format("Cannot load dll file: %s", path));
		}

		INSTANCE = (AutoItX) Native.loadLibrary(path, AutoItX.class);
	}
	
	/**
	 * 获取AutoItX实例
	 * @author liulxiang
	 * @return
	 */
	public static AutoItX getInstance() {
		return INSTANCE;
	}
}
