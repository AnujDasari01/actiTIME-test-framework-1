package com.actitime.driver;

import com.actitime.genericlibrary.XMLUtility;

/*
* Updated on 1/7/2017
*/

/*
* Driver class with main method
*/
public class Driver {
	private static String relativePath;
	
	public static String getRelativePath() {
		return relativePath;
	}
	
	
	public static void main(String args[]) throws Exception {
		
		relativePath = System.getProperty("user.dir");
		
		XMLUtility.createXml();
		
		XMLUtility.autoRunXml();

	}
}
