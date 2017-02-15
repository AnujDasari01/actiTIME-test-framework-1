package com.actitime.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	private static String generalEnvPropFilePath;
	private static String standAloneEnvPropFilePath;
	private static String gridEnvPropFilePath;
	private static String app;
	private static String browserName;
	private static String browserName1;
	private static String browserName2;
	private static String browserName3;
	private static String nodeUrl1;
	private static String nodeUrl2;
	private static String automationName;
	private static String deviceName;
	private static String platformName;
	private static String platformVersion;
	private static String noReset;
	private static String fullReset;
	private static String url;
	private static String type;
	private static String runOn;
	private static String device;

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

	public static String getRunOn() {
		return runOn;
	}

	public static String getGeneralEnvPropFilePath() {
		return generalEnvPropFilePath;
	}

	public static String getStandAloneEnvPropFilePath() {
		return standAloneEnvPropFilePath;
	}

	public static String getGridEnvPropFilePath() {
		return gridEnvPropFilePath;
	}

	public static String getBrowserName1() {
		return browserName1;
	}

	public static String getBrowserName2() {
		return browserName2;
	}

	public static String getBrowserName3() {
		return browserName3;
	}

	public static String getNodeUrl1() {
		return nodeUrl1;
	}

	public static String getNodeUrl2() {
		return nodeUrl2;
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
		retrieveGeneralEnvProperties();
		retrieveStandAloneEnvProperties();
		retrieveGridEnvProperties();
		readValidXmlSheet();
		XMLUtility.autoRunXml();
	}

	/**
	 * This method retrieves all properties from the GeneralEnvProperties file
	 **/
	public static void retrieveGeneralEnvProperties() {
		generalEnvPropFilePath = "./src/test/resources/PropertiesFiles/GeneralEnvProperties.properties";
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(generalEnvPropFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<Object> set = prop.keySet();

		Iterator<Object> it = set.iterator();

		for (int i = 0; i < set.size(); i++) {
			String key = (String) it.next();

			if (key.equalsIgnoreCase("runOn")) {
				runOn = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("platformType")) {
				type = prop.getProperty(key);
			} else
				continue;
		}
	}

	/**
	 * This method retrieves all properties from the StandAloneEnvProperties
	 * file
	 **/
	public static void retrieveStandAloneEnvProperties() {
		standAloneEnvPropFilePath = "./src/test/resources/PropertiesFiles/StandAloneEnvProperties.properties";
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(standAloneEnvPropFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<Object> set = prop.keySet();

		Iterator<Object> it = set.iterator();

		for (int i = 0; i < set.size(); i++) {
			String key = (String) it.next();

			if (key.equalsIgnoreCase("url")) {
				url = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("browserName")) {
				browserName = prop.getProperty(key);
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
				device = prop.getProperty(key);
			} else
				continue;
		}
	}

	/**
	 * This method retrieves all properties from the GridEnvProperties file
	 **/
	public static void retrieveGridEnvProperties() {
		gridEnvPropFilePath = "./src/test/resources/PropertiesFiles/GridEnvProperties.properties";
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(gridEnvPropFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<Object> set = prop.keySet();

		Iterator<Object> it = set.iterator();

		for (int i = 0; i < set.size(); i++) {
			String key = (String) it.next();

			if (key.equalsIgnoreCase("browserName1")) {
				browserName1 = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("browserName2")) {
				browserName2 = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("browserName3")) {
				browserName3 = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("nodeUrl1")) {
				nodeUrl1 = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("nodeUrl2")) {
				nodeUrl2 = prop.getProperty(key);
			} else
				continue;
		}
	}

	/**
	 * This method selects the sheet based on platform type
	 **/
	public static void readValidXmlSheet() throws Exception {
		if (type.equalsIgnoreCase("Desktop")) {
			if (runOn.equalsIgnoreCase("grid")) {
				XMLUtility.createXmlForGridConfig("TestScriptsWeb",
						browserName1, browserName2, browserName3);
			} else if (runOn.equalsIgnoreCase("StandAlone")) {
				XMLUtility.createXmlForStandAloneConfig("TestScriptsWeb",
						browserName);
			}
		}

		if (type.equalsIgnoreCase("Device")) {
			if (runOn.equalsIgnoreCase("grid")) {
				XMLUtility.createXmlForGridConfig("TestScriptsDevice",
						browserName1, browserName2, browserName3);
			} else if (runOn.equalsIgnoreCase("StandAlone")) {
				XMLUtility.createXmlForStandAloneConfig("TestScriptsDevice",
						app);
			}

		}

		if (type.equalsIgnoreCase("App")) {
			if (runOn.equalsIgnoreCase("grid")) {
				XMLUtility.createXmlForGridConfig("TestScriptsApp",
						browserName1, browserName2, browserName3);
			} else if (runOn.equalsIgnoreCase("StandAlone")) {
				XMLUtility.createXmlForStandAloneConfig("TestScriptsDevice",
						app);
			}
		}
	}
}
