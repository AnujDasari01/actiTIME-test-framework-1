package com.actitime.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import com.actitime.genericlibrary.XMLUtility;

/*
 * Updated on 1/7/2017
 */

/**
 * This is Driver class with main method
 **/
public class Driver {
	private static String relativePath;
	private static String envPropFilePath;
	private static String app;
	private static String browserName;
	private static String automationName;
	private static String deviceName;
	private static String platformName;
	private static String platformVersion;
	private static String noReset;
	private static String fullReset;
	private static String url;
	private static String type;
	private static String device;

	public static String getEnvPropFilePath() {
		return envPropFilePath;
	}

	public static String getApp() {
		return app;
	}

	public static String getBrowserName() {
		return browserName;
	}

	public static String getAutomationName() {
		return automationName;
	}

	public static String getDeviceName() {
		return deviceName;
	}

	public static String getPlatformName() {
		return platformName;
	}

	public static String getPlatformVersion() {
		return platformVersion;
	}

	public static String getNoReset() {
		return noReset;
	}

	public static String getFullReset() {
		return fullReset;
	}

	public static String getUrl() {
		return url;
	}

	public static String getType() {
		return type;
	}

	public static String getDevice() {
		return device;
	}

	public static String getRelativePath() {
		return relativePath;
	}

	/**
	 * This is the main method
	 **/
	public static void main(String args[]) throws Exception {

		relativePath = System.getProperty("user.dir");

		getProperties();

		readValidXmlSheet();

		XMLUtility.autoRunXml();

	}

	/**
	 * This method reads properties from properties file
	 **/
	public static void getProperties() throws IOException {

		envPropFilePath = "Env.properties";

		Properties prop = new Properties();

		InputStream input = new FileInputStream(envPropFilePath);

		prop.load(input);

		Set<Object> set = prop.keySet();

		Iterator<Object> it = set.iterator();

		for (int i = 0; i < set.size(); i++) {
			String key = (String) it.next();

			if (key.equalsIgnoreCase("browserName")) {
				browserName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("platformType")) {
				type = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("url")) {
				url = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("platformName")) {
				platformName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("platformVersion")) {
				platformVersion = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("automationName")) {
				automationName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("deviceName")) {
				deviceName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("noReset")) {
				noReset = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("fullReset")) {
				fullReset = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("app")) {
				app = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("device")) {
				device = prop.getProperty("device");
			} else
				continue;
		}

	}

	/**
	 * This method selects the sheet based on platform type
	 **/
	public static void readValidXmlSheet() throws Exception {
		if (type.equalsIgnoreCase("Desktop")) {
			XMLUtility.createXml("TestScriptsWeb");
		}

		if (type.equalsIgnoreCase("Device")) {
			XMLUtility.createXml("TestScriptsDevice");
		}

		if (type.equalsIgnoreCase("App")) {
			XMLUtility.createXml("TestScriptsApp");
		}
	}
}
