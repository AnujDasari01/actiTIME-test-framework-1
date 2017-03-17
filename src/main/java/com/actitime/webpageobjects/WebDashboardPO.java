package com.actitime.webpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This is ActiTime Dashboard Page Object
 **/

public class WebDashboardPO {

	WebDriver driver;

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

	public WebDashboardPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This methods Navigates driver to Tasks
	 **/
	public String navigateToTasks() {
		tasksWidget.click();
		return driver.getTitle();
	}

	/**
	 * This methods Navigates driver to Reports
	 **/
	public String navigateToReports() {
		reportWidget.click();
		return driver.getTitle();
	}

	/**
	 * This methods Navigates driver to Users
	 **/
	public String navigateToUsers() {
		usersWidget.click();
		return driver.getTitle();
	}

	/**
	 * This methods makes driver logout of application
	 **/
	public String logout() {
		logOutBtn.click();
		return driver.getTitle();
	}
}