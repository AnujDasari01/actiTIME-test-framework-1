package com.actitime.driver;

import com.actitime.utils.XMLUtilityManager;

/**
 * This is Driver class with main method
 **/
public class Driver extends DriverManager {
	
	/**
	 * This is the main method
	 **/
	public static void main(String args[]) throws Exception {
		relativePath = System.getProperty("user.dir");
		runOn = System.getProperty("runOn");
		type = System.getProperty("platform");
		if (runOn.equalsIgnoreCase("Grid")) {
			retrieveGridEnvProperties();
		} else if (runOn.equalsIgnoreCase("Standalone")) {
			retrieveStandAloneEnvProperties();
		}
		XMLUtilityManager.autoRunXml();
	}
}