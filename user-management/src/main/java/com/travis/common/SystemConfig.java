package com.travis.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SystemConfig {
	protected static final Log logger = LogFactory.getLog(SystemConfig.class);
	public static SystemConfig INSTANCE = new SystemConfig();
	private Properties prop;

	protected SystemConfig() {
		this.prop = new Properties();
		try {
			this.prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("system.properties"),
					"UTF-8"));
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public String getValue(String key) {
		return getValue(key, null);
	}

	public String getValue(String key, String defaultVal) {
		String s = this.prop.getProperty(key);
		if (s == null) {
			return defaultVal;
		}
		return s;
	}

	public boolean getValue(String key, boolean defaultVal) {
		String s = this.prop.getProperty(key);
		if (s == null) {
			return defaultVal;
		}
		return Boolean.parseBoolean(s);
	}

	public int getValue(String key, int defaultVal) {
		String s = this.prop.getProperty(key);
		if (s == null) {
			return defaultVal;
		}
		return Integer.parseInt(s);
	}

	public boolean isDebug() {
		return getValue("debug", false);
	}

	public String format(String key, String[] value) {
		return MessageFormat.format(getValue(key), value);
	}

	public static void main(String[] s) {
		System.out.println(INSTANCE.format("test", new String[] { "aa", "bb", "cc" }));
	}
}