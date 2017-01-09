package com.actitime.webpageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.actitime.driver.SuperReference;
//import com.actitime.genericlibrary.Constants;
import com.actitime.genericlibrary.FileUtility;
import com.actitime.genericlibrary.Helper;
import com.actitime.genericlibrary.Report;

/*
 * Updated on 1/7/2017
 */

/*
 * ActiTime Login Page Object
 */
public class LoginPO {

	private WebDriver driver;

	@FindBy(css = "input#username")
	private WebElement userName;

	@FindBy(css = "input[type='password']")
	private WebElement passWord;

	@FindBy(css = "input[type='checkbox']")
	private WebElement passWordChkBox;

	@FindBy(css = "input#loginButton")
	private WebElement logInBtn;

	public LoginPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/* Method to Login to actiTIME Application */
	public void login() {
		try {
			driver.get(SuperReference.url);
			Helper.implicitWait(driver);
			userName.sendKeys(FileUtility.testData.get("UserID"));
			passWord.sendKeys(FileUtility.testData.get("Pwd"));
			passWord.sendKeys(Keys.TAB);
			passWord.sendKeys(Keys.TAB);
			passWord.sendKeys(Keys.ENTER);
			Report.captureScreenshot(driver, "SignIntoApplication ");
			Assert.assertEquals("actiTIME - Enter Time-Track",
					driver.getTitle());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

}
