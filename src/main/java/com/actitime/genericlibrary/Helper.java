package com.actitime.genericlibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/*
 * Updated on 1/7/2017
 */


/**
 * This is Helper class with all generic methods 
 **/
public class Helper {


	/**
	 * This method makes the driver wait for specified seconds 
	 **/
	public static void normalWait(WebDriver driver, long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	/**
	 * This method makes the driver wait implicitly 
	 **/
	public static void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	}


	/**
	 * This method makes the driver wait till the webelement is located
	 **/
	public static void explicitWait(By element, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * This method makes the driver scroll down the specified webelement
	 **/
	public static boolean scrollTo(WebElement wb, WebDriver driver) {
		try {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true);", wb);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb.isDisplayed();
	}


	/**
	 * This method checks for partial text in a webelement 
	 **/
	public static void checkPartialText(WebElement wb, String st) {
		try {
			st = st.trim().toLowerCase();
			if (wb.getText().contains(st)) {
				Reporter.log(st + " is present in" + wb.getText() + "<br>");
			} else {
				Reporter.log(st + " is not present" + "<br>");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	/**
	 * This method checks for text in a webelement 
	 **/
	public static void checkText(WebElement wb, String st) {
		try {
			st = st.trim();
			if (wb.getText().equalsIgnoreCase(st)) {
				Reporter.log(st + " is present" + "<br>");
			} else {
				Reporter.log(st + " is not present" + "<br>");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * This method handles alert windows 
	 **/
	public static void handleAlert(String status, WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		if(status.equalsIgnoreCase("Y")) {
			alert.accept();
		}
		else if (status.equalsIgnoreCase("N")) {
			alert.dismiss();
		}
	}
	
	

}
