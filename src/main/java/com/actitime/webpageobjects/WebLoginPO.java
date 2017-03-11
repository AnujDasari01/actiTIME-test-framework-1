package com.actitime.webpageobjects;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.driver.Driver;
import com.actitime.genericlibrary.FileUtility;
import com.actitime.genericlibrary.Helper;
import com.actitime.genericlibrary.Report;

/**
 * This is ActiTime Login Page Object
 **/
public class WebLoginPO {

	WebDriver driver;

	@FindBy(css = "input#username")
	private WebElement username;

	@FindBy(css = "input[type='password']")
	private WebElement password;

	@FindBy(css = "input[type='checkbox']")
	private WebElement passwordChkBox;

	@FindBy(css = "input#loginButton")
	private WebElement logInBtn;
	
	String type;
	public WebLoginPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is used to login to ActiTime Application
	 * 
	 * @throws IOException
	 **/
	public String login() throws IOException {
		try {
			if (Driver.getType().equalsIgnoreCase("Desktop")) {
				driver.get(Driver.getDesktopUrl());
			} else if (Driver.getType().equalsIgnoreCase("Device")) {
				driver.get(Driver.getDeviceUrl());
			}
			Helper.implicitWait(driver);
			username.sendKeys(FileUtility.getTestData().get("UserID"));
			password.sendKeys(FileUtility.getTestData().get("Pwd"));
			password.sendKeys(Keys.TAB);
			password.sendKeys(Keys.TAB);
			password.sendKeys(Keys.ENTER);
			Report.captureScreenshot(driver, "SignIntoApplication ");

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return driver.getTitle();
	}

}
