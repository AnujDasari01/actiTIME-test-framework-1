package com.actitime.pageobjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.actitime.genericlibrary.FileUtility;
import com.actitime.genericlibrary.Helper;
import com.actitime.genericlibrary.Report;

/*
 * Updated on 23/12/2016
 */

/*
 * ActiTime Dashboard Page Object
 */

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

	@FindBy(css = "div.buttonText")
	private WebElement addUserBtn;

	@FindBy(css = "input#userDataLightBox_firstNameField")
	private WebElement firstNameTextField;

	@FindBy(css = "input#userDataLightBox_lastNameField")
	private WebElement lastNameTextField;

	@FindBy(css = "input#userDataLightBox_emailField")
	private WebElement emailTextField;

	@FindBy(css = "input#userDataLightBox_usernameField")
	private WebElement userNameTextField;

	@FindBy(css = "input#userDataLightBox_passwordField")
	private WebElement passwordTextField;

	@FindBy(css = "input#userDataLightBox_passwordCopyField")
	private WebElement retypePasswordTextField;

	@FindBy(css = "div#userDataLightBox_commitBtn")
	private WebElement confirmUserAddBtn;

	@FindBy(css = "span.userNameSpan")
	private WebElement confirmUserAdd;

	@FindBy(xpath = "//div[@class='name']/span[@class='userNameSpan']")
	private List<WebElement> addedUsersList;

	@FindBy(xpath = "//button[contains(text(),'Delete User')]")
	private WebElement deleteUserBtn;

	@FindBy(css = "img.closeButton")
	private WebElement modalWindowClose;

	@FindBy(css = "a.logout")
	private WebElement logOutBtn;

	@FindBy(css = "a.next")
	private WebElement nextBtn;

	public DashboardPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/* Method to Navigate to Tasks */
	public void navigateToTasks() {
		tasksWidget.click();
		Report.captureScreenshot(driver, "NavigateToTasks ");
		Assert.assertEquals("actiTIME - Open Tasks", driver.getTitle());
	}

	/* Method to Navigate to Reports */
	public void navigateToReports() {
		reportWidget.click();
		Report.captureScreenshot(driver, "NavigateToReports ");
		Assert.assertEquals("actiTIME - Reports Dashboard", driver.getTitle());
	}

	/* Method to Navigate to Users */
	public void navigateToUsers() {
		usersWidget.click();
		Report.captureScreenshot(driver, "NavigateToUsers ");
		Assert.assertEquals("actiTIME - User List", driver.getTitle());

	}

	/* Method to create a user */
	public void createUser() {
		addUserBtn.click();
		firstNameTextField.sendKeys(FileUtility.testData.get("First_Name"));
		lastNameTextField.sendKeys(FileUtility.testData.get("Last_Name"));
		emailTextField.sendKeys(FileUtility.testData.get("Email_Address"));
		userNameTextField.sendKeys(FileUtility.testData.get("UserName"));
		passwordTextField.sendKeys(FileUtility.testData.get("Password"));
		retypePasswordTextField.sendKeys(FileUtility.testData.get("Password"));
		Helper.scrollTo(confirmUserAddBtn, driver);
		confirmUserAddBtn.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Helper.scrollTo(confirmUserAdd, driver);
		String actualName = confirmUserAdd.getText();
		String expectedName = FileUtility.testData.get("Last_Name") + ", "
				+ FileUtility.testData.get("First_Name");
		Assert.assertEquals(actualName, expectedName);
		Helper.scrollTo(usersTitle, driver);
		Report.captureScreenshot(driver, "UserAddition ");
		// modalWindowClose.click();
	}

	public void checkExistingUser() {
		String checkUser = FileUtility.testData.get("Full_Name");
		System.out.println("Check for user: " + checkUser);
		int count = 0;
		String availableUsers;
		while (count < 10) {
			Helper.scrollTo(usersTitle, driver);
			for (int i = 0; i < addedUsersList.size(); i++) {
				availableUsers = addedUsersList.get(i).getText();
				if (checkUser.equalsIgnoreCase(availableUsers)) {
					WebElement we = addedUsersList.get(i);
					Helper.scrollTo(we, driver);
					Report.captureScreenshot(driver, "CheckExistingUser");
					break;
				} else if (!(checkUser.equalsIgnoreCase(availableUsers))
						&& count < 10) {
					count++;
					continue;
				}
			}

			/* If all items in one page are checked, click on next page */
			if (count == 10) {
				count = 0;
				Helper.scrollTo(nextBtn, driver);
				nextBtn.click();
				continue;
			}

			/* Verify for an user on all pages */
			else if (count < 10) {
				try {
					if (nextBtn.isEnabled()) {
						break;
					}
				} catch (NoSuchElementException e) {
					for (int i = 0; i < addedUsersList.size(); i++) {
						availableUsers = addedUsersList.get(i).getText();
						if (checkUser.equalsIgnoreCase(availableUsers)) {
							break;
						} else if (!(checkUser.equalsIgnoreCase(availableUsers))
								&& count < addedUsersList.size()) {
							continue;
						} else {
							Assert.fail("No Such User Found!");
							break;
						}
					}
					break;

				}

			}
		}
	}

	public void deleteUser() {
		String deleteUser = FileUtility.testData.get("Full_Name");
		int count = 0;
		String availableUsers;
		while (count < 10) {
			Helper.scrollTo(usersTitle, driver);
			for (int i = 0; i < addedUsersList.size(); i++) {
				availableUsers = addedUsersList.get(i).getText();
				if (deleteUser.equalsIgnoreCase(availableUsers)) {
					WebElement we = addedUsersList.get(i);
					Helper.scrollTo(we, driver);
					we.click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					deleteUserBtn.click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Alert alert = driver.switchTo().alert();
					alert.accept();
					Report.captureScreenshot(driver, "DeleteExistingUser");

					break;
				} else if (!(deleteUser.equalsIgnoreCase(availableUsers))
						&& count < 10) {
					count++;
					continue;
				}
			}

			/* If all items in one page are checked, click on next page */
			if (count == 10) {
				count = 0;
				Helper.scrollTo(nextBtn, driver);
				nextBtn.click();
				continue;
			}

			/* Verify for an user on all pages */
			else if (count < 10) {
				try {
					if (nextBtn.isEnabled()) {
						break;
					}
				} catch (NoSuchElementException e) {
					for (int i = 0; i < addedUsersList.size(); i++) {
						availableUsers = addedUsersList.get(i).getText();
						if (deleteUser.equalsIgnoreCase(availableUsers)) {
							break;
						} else if (!(deleteUser
								.equalsIgnoreCase(availableUsers))
								&& count < addedUsersList.size()) {
							continue;
						} else {
							Assert.fail("No Such User Found!");
							break;
						}
					}
					break;

				}

			}
		}
	}

	/* Method to Log out of Actitime Application */
	public void logout() {
		logOutBtn.click();
		Report.captureScreenshot(driver, "SignOutOfApplication ");
	}
}