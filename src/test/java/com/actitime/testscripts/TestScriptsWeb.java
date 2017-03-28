package com.actitime.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

/*
 * ActiTime Application Test Scripts
 */
public class TestScriptsWeb extends BaseTest {
	@Test
	public void TC01_SignIntoApplication() throws IOException {
		test = extent.createTest("SignIntoApplication",
				"Verify SignIntoApplication");
		FileUtilityManager.retrieveData("TC01_SignIntoApplication");
		String loginTitle = webLoginPO.login();
		ReportNGReport.captureScreenshot(driver, "SignIntoApplication ");
		Assert.assertEquals(loginTitle, "actiTIME - Enter Time-Track");
		if (loginTitle.equals("actiTIME - Enter Time-Track")) {
			test.pass("TC01_SignIntoApplication");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "TC01_SignIntoApplication");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
	}

	@Test
	public void TC02_NavigateToTasks() {
		test = extent.createTest("NavigateToTasks", "Verify NavigateToTasks");
		String dashboardTitle = webDashBoardPO.navigateToTasks();
		ReportNGReport.captureScreenshot(driver, "NavigateToTasks ");
		Assert.assertEquals(dashboardTitle, "actiTIME - Open Tasks");
		if (dashboardTitle.equalsIgnoreCase("actiTIME - Open Tasks")) {
			test.pass("TC02_NavigateToTasks");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "TC02_NavigateToTasks");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
	}

	@Test
	public void TC03_NavigateToReports() {
		test = extent.createTest("NavigateToReports",
				"Verify NavigateToReports");
		String reportTitle = webDashBoardPO.navigateToReports();
		ReportNGReport.captureScreenshot(driver, "NavigateToReports ");
		Assert.assertEquals(reportTitle, "actiTIME - Reports Dashboard");
		if (reportTitle.equalsIgnoreCase("actiTIME - Reports Dashboard")) {
			test.pass("TC03_NavigateToReports");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "TC03_NavigateToReports");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
	}

	@Test
	public void TC04_NavigateToUsers() {
		test = extent.createTest("NavigateToUsers", "Verify NavigateToUsers");
		String usersTitle = webDashBoardPO.navigateToUsers();
		ReportNGReport.captureScreenshot(driver, "NavigateToUsers ");
		Assert.assertEquals(usersTitle, "actiTIME - User List");
		if (usersTitle.equalsIgnoreCase("actiTIME - User List")) {
			test.pass("TC04_NavigateToUsers");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "TC04_NavigateToUsers");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
	}

	@Test
	public void TC05_CreateAUser() {
		test = extent.createTest("CreateAUser", "Verify User is Created");
		FileUtilityManager.retrieveData("TC05_CreateAUser");
		String expectedName = FileUtilityManager.getTestData().get("Last_Name")
				+ ", " + FileUtilityManager.getTestData().get("First_Name");
		String createUser = webUsersPO.createUser();
		if (createUser.contains("already exists")) {
			test.log(Status.FAIL, "TC05_CreateAUser");
			test.log(Status.INFO, createUser);
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			Assert.fail(createUser);
		} else {
			ReportNGReport.captureScreenshot(driver, "UserAddition ");
			Assert.assertEquals(expectedName, createUser);
			test.pass("TC05_CreateAUser");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
	}

	@Test
	public void TC06_VerifyExistingUser() {
		test = extent.createTest("VerifyExistingUser", "Verify if user exists");
		FileUtilityManager.retrieveData("TC06_VerifyExistingUser");
		String checkUser = FileUtilityManager.getTestData().get("Full_Name");
		boolean verifyUserStatus = webUsersPO.checkExistingUser(checkUser);
		if (verifyUserStatus) {
			test.pass("TC06_VerifyExistingUser");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			ReportNGReport.captureScreenshot(driver, "DeleteExistingUser");
		} else {
			test.log(Status.FAIL, "TC06_VerifyExistingUser");
			test.log(Status.INFO, checkUser + ":No Such User Found!");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			ReportNGReport.captureScreenshot(driver, "VerifyExistingUser");
			Assert.fail(checkUser + " : No Such User Found!");
		}

	}

	@Test
	public void TC07_DeleteUser() {
		test = extent.createTest("DeleteUser", "Verify User is Deleted");
		FileUtilityManager.retrieveData("TC07_DeleteUser");
		String deleteUser = FileUtilityManager.getTestData().get("Full_Name");
		boolean deleteUserStatus = webUsersPO.deleteUser(deleteUser);
		if (deleteUserStatus) {
			test.pass("TC07_DeleteUser");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			ReportNGReport.captureScreenshot(driver, "DeleteExistingUser");
		} else {
			test.log(Status.FAIL, "TC07_DeleteUser");
			test.log(Status.INFO, deleteUser + ":No Such User Found!");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			ReportNGReport.captureScreenshot(driver, "DeleteExistingUser");
			Assert.fail(deleteUser + " : No Such User Found!");
		}

	}

	@Test
	public void TC08_SignOutOfApplication() {
		test = extent.createTest("SignOutOfApplication",
				"Verify SignOutOfApplication");
		String logoutConfirmTitle = webDashBoardPO.logout();
		ReportNGReport.captureScreenshot(driver, "SignOutOfApplication");
		Assert.assertEquals(logoutConfirmTitle, "actiTIME - Login");
		if (logoutConfirmTitle.equalsIgnoreCase("actiTIM	E - Login")) {
			test.pass("TC08_SignOutOfApplication");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "TC08_SignOutOfApplication");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
	}

}
