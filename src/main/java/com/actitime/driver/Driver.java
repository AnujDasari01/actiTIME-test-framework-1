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

/*
 * Driver class with main method
 */
public class Driver {
	private static String relativePath;
	public static String EnvPropFilePath;
	public static String app;
	public static String browserName;
	public static String automationName;
	public static String deviceName;
	public static String platformName;
	public static String platformVersion;
	public static String noReset;
	public static String fullReset;
	public static String url;
	public static String type;
	public static String device;

	public static String getRelativePath() {
		return relativePath;
	}

	public static void main(String args[]) throws Exception {

		relativePath = System.getProperty("user.dir");

		getProperties();

		XMLUtility.createXml();

		XMLUtility.autoRunXml();

	}

	/* Read properties from properties file */
	public static void getProperties() throws IOException {

		EnvPropFilePath = "Env.properties";

		// String[] args = null;
		// EnvPropFilePath = new FileInputStream(args[0]);

		Properties prop = new Properties();

		InputStream input = new FileInputStream(EnvPropFilePath);

		prop.load(input);

		Set<Object> set = prop.keySet();
		Iterator<Object> it = set.iterator();

		for (int i = 0; i < set.size(); i++) {
			String key = (String) it.next();
			// String value = prop.getProperty(key);

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
}
