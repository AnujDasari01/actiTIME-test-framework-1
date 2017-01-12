package com.actitime.genericlibrary;

import java.util.concurrent.TimeUnit;

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

/*
 * Helper class with all generic methods
 */
public class Helper {

	/* Method to make the driver sleep for specific seconds */
	public static void normalWait(WebDriver driver, long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* Method to make the driver wait implicitly */
	public static void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	}

	/*
	 * Method to make the driver wait explicitly till WebElement ele is visible
	 */
	public static void explicitWait(By element, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method to make the driver scroll down to the WebElement ele
	 */
	public static boolean scrollTo(WebElement wb, WebDriver driver) {
		try {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true);", wb);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb.isDisplayed();
	}

	/*
	 * Method to check for Partial text
	 */
	public static void checkPartialText(WebElement el, String st) {
		try {
			st = st.trim().toLowerCase();
			if (el.getText().contains(st)) {
				Reporter.log(st + " is present in" + el.getText() + "<br>");
			} else {
				Reporter.log(st + " is not present" + "<br>");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * Method to check for a text
	 */
	public static void checkText(WebElement el, String st) {
		try {
			st = st.trim();
			if (el.getText().equalsIgnoreCase(st)) {
				Reporter.log(st + " is present" + "<br>");
			} else {
				Reporter.log(st + " is not present" + "<br>");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
