package com.actitime.webpageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.actitime.genericlibrary.FileUtility;
import com.actitime.genericlibrary.Helper;
import com.actitime.genericlibrary.Report;

/*
 * Updated on 1/7/2017
 */

/**
 * This is ActiTime Users Page Object
 **/
public class UsersPO {

	private WebDriver driver;
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

	@FindBy(xpath = "//div[@class='userFieldError usernameSecondLvl']/div[contains(text(),'this')]")
	private WebElement duplicateUserError;

	@FindBy(xpath = "//button[contains(text(),'Delete User')]")
	private WebElement deleteUserBtn;

	@FindBy(css = "img.closeButton")
	private WebElement modalWindowClose;

	@FindBy(xpath = "//a[@class='page' and contains(text(),'1')]")
	private WebElement firstPageUserList;

	@FindBy(css = "a.next")
	private WebElement nextBtn;

	public UsersPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is used to create a user
	 **/
	public void createUser() {
		addUserBtn.click();
		firstNameTextField.sendKeys(FileUtility.testData.get("First_Name"));
		lastNameTextField.sendKeys(FileUtility.testData.get("Last_Name"));
		emailTextField.sendKeys(FileUtility.testData.get("Email_Address"));
		userNameTextField.sendKeys(FileUtility.testData.get("UserName"));
		passwordTextField.sendKeys(FileUtility.testData.get("Password"));
		retypePasswordTextField.sendKeys(FileUtility.testData.get("Password"));

		if (duplicateUserError.isDisplayed()) {
			Report.captureScreenshot(driver, "UserAddition");
			modalWindowClose.click();
			Helper.handleAlert("Y", driver);
			Assert.fail("User having username: "
					+ FileUtility.testData.get("UserName") + " already exists!");
		}

		else {
			Helper.scrollTo(confirmUserAddBtn, driver);
			confirmUserAddBtn.click();
			Helper.normalWait(driver, 1);
			Helper.scrollTo(confirmUserAdd, driver);
			String actualName = confirmUserAdd.getText();
			String expectedName = FileUtility.testData.get("Last_Name") + ", "
					+ FileUtility.testData.get("First_Name");
			Assert.assertEquals(actualName, expectedName);
			Helper.scrollTo(usersTitle, driver);
			Report.captureScreenshot(driver, "UserAddition ");
			// modalWindowClose.click();

		}

	}

	/**
	 * This method is used to check for an existing user
	 **/
	public void checkExistingUser() {
		String checkUser = FileUtility.testData.get("Full_Name");
		System.out.println("Check for user: " + checkUser);
		driver.navigate().refresh();
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
				} catch (Exception e) {
					for (int i = 0; i < addedUsersList.size(); i++) {
						availableUsers = addedUsersList.get(i).getText();
						if (checkUser.equalsIgnoreCase(availableUsers)) {
							break;
						} else if (!(checkUser.equalsIgnoreCase(availableUsers))
								&& count < addedUsersList.size()) {
							continue;
						} else {
							Report.captureScreenshot(driver, "CheckExistingUser");
							Assert.fail(checkUser + " : No Such User Found!");
							break;
						}
					}
					break;

				}

			}
		}
	}

	/**
	 * This method is used to delete an existing user
	 **/
	public void deleteUser() {
		String deleteUser = FileUtility.testData.get("Full_Name");
		System.out.println("Delete user :" + deleteUser);
		driver.navigate().refresh();
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
					Helper.normalWait(driver, 1);
					if (!deleteUserBtn.isEnabled()) {
						modalWindowClose.click();
						Assert.fail("Unable to delete user");
					}

					else {
						deleteUserBtn.click();
						Helper.normalWait(driver, 1);
						Helper.handleAlert("Y", driver);
						Report.captureScreenshot(driver, "DeleteExistingUser");

						break;

					}
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
				} catch (Exception e) {
					for (int i = 0; i < addedUsersList.size(); i++) {
						availableUsers = addedUsersList.get(i).getText();
						if (deleteUser.equalsIgnoreCase(availableUsers)) {
							break;
						} else if (!(deleteUser
								.equalsIgnoreCase(availableUsers))
								&& count < addedUsersList.size()) {
							continue;
						} else {
							Report.captureScreenshot(driver, "DeleteExistingUser");
							Assert.fail(deleteUser + " : No Such User Found!");
							break;
						}
					}
					break;

				}

			}
		}
	}

}
