package com.actitime.webpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.actitime.genericlibrary.Report;

/*
 * Updated on 1/7/2017
 */

/**
 * This is ActiTime Dashboard Page Object
 **/

public class DashboardPO {

	private WebDriver driver;

	@FindBy(css = "a.content.tasks")
	private WebElement tasksWidget;

	@FindBy(css = "a.content.reports")
	private WebElement reportWidget;

	@FindBy(css = "a.content.users")
	private WebElement usersWidget;

	@FindBy(xpath = "//div[@class='overlayTipWrapper']/span")
	private WebElement tasksTitle;

	@FindBy(xpath = "//div[@class='overlayTipWrapper' and contains(text(),'Reports')]")
	private WebElement reportsTitle;

	@FindBy(xpath = "//div[@class='pagetitle']/span")
	private WebElement usersTitle;

	@FindBy(css = "a.logout")
	private WebElement logOutBtn;

	public DashboardPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This methods Navigates driver to Tasks
	 **/
	public void navigateToTasks() {
		tasksWidget.click();
		Report.captureScreenshot(driver, "NavigateToTasks ");
		Assert.assertEquals("actiTIME - Open Tasks", driver.getTitle());
	}

	/**
	 * This methods Navigates driver to Reports
	 **/
	public void navigateToReports() {
		reportWidget.click();
		Report.captureScreenshot(driver, "NavigateToReports ");
		Assert.assertEquals("actiTIME - Reports Dashboard", driver.getTitle());
	}

	/**
	 * This methods Navigates driver to Users
	 **/
	public void navigateToUsers() {
		usersWidget.click();
		Report.captureScreenshot(driver, "NavigateToUsers ");
		Assert.assertEquals("actiTIME - User List", driver.getTitle());
	}

	/**
	 * This methods makes driver logout of application
	 **/
	public void logout() {
		logOutBtn.click();
		Report.captureScreenshot(driver, "SignOutOfApplication ");
	}
}