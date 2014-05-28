package com.opennice.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyGetter {
	private static Properties prop = new Properties();

	static {
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		String ret = System.getenv(key);

		if (ret == null || ret.trim().equals("")) {
			ret = prop.getProperty(key);
		}

		return ret;
	}
}
