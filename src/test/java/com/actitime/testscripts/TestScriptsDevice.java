package com.actitime.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.driver.SuperReference;
import com.actitime.genericlibrary.ExtentReport;
import com.actitime.genericlibrary.FileUtility;
import com.actitime.genericlibrary.Report;
import com.aventstack.extentreports.Status;

/*
 * ActiTime Application Test Scripts
 */
public class TestScriptsDevice extends SuperReference {
	@Test
	public void TC01_SignIntoApplication() throws IOException {
		test = extent.createTest("SignIntoApplication", "Verify SignIntoApplication");
		FileUtility.retrieveData("TC01_SignIntoApplication");
		String loginTitle = deviceLoginPO.login();
		Report.captureScreenshot(driver, "SignIntoApplication ");
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
		String dashboardTitle = deviceDashBoardPO.navigateToTasks();
		Report.captureScreenshot(driver, "NavigateToTasks ");
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
		test = extent.createTest("NavigateToReports", "Verify NavigateToReports");
		String reportTitle = deviceDashBoardPO.navigateToReports();
		Report.captureScreenshot(driver, "NavigateToReports ");
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
		String usersTitle = deviceDashBoardPO.navigateToUsers();
		Report.captureScreenshot(driver, "NavigateToUsers ");
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
	public void TC05_SignOutOfApplication() {
		test = extent.createTest("SignOutOfApplication", "Verify SignOutOfApplication");
		String logoutConfirmTitle = deviceDashBoardPO.logout();
		Report.captureScreenshot(driver, "SignOutOfApplication");
		Assert.assertEquals(logoutConfirmTitle, "actiTIME - Login");
		if (logoutConfirmTitle.equalsIgnoreCase("actiTIME - Login")) {
			test.pass("TC05_SignOutOfApplication");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "TC08_SignOutOfApplication");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
	}
}
