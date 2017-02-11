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

	private String relativePath;
	private String envPropFilePath;
	private String app;
	private String browserName;
	private String automationName;
	private String deviceName;
	private String platformName;
	private String platformVersion;
	private String noReset;
	private String fullReset;
	private String url;
	private String type;
	private String runOn;
	private String device;

	public String getEnvPropFilePath() {
		return envPropFilePath;
	}

	public String getApp() {
		return app;
	}

	public String getBrowserName() {
		return browserName;
	}

	public String getAutomationName() {
		return automationName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public String getPlatformVersion() {
		return platformVersion;
	}

	public String getNoReset() {
		return noReset;
	}

	public String getFullReset() {
		return fullReset;
	}

	public String getUrl() {
		return url;
	}

	public String getType() {
		return type;
	}

	public String getRunOn() {
		return runOn;
	}
	public String getDevice() {
		return device;
	}

	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * This is the main method
	 **/
	public static void main(String args[]) throws Exception {

		new Driver().readValidXmlSheet();

		XMLUtility.autoRunXml();

	}

	/**
	 * Driver constructor to initialize all instance variables 
	 **/
	public Driver()  {
		relativePath = System.getProperty("user.dir");
		
		envPropFilePath = "Env.properties";
		
		Properties prop = new Properties();

		InputStream input = null;
		try {
			input = new FileInputStream(envPropFilePath);
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
				device = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("runOn")) {
				runOn = prop.getProperty(key);
			}else
				continue;
		}
	}


	/**
	 * This method selects the sheet based on platform type
	 **/
	public void readValidXmlSheet() throws Exception {
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
