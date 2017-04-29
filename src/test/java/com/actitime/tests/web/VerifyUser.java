package com.actitime.tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

public class VerifyUser extends BaseTest {
	@Test(groups = { "Regression" })
	public void verifyExistingUser() {
		test = extent.createTest("VerifyExistingUser", "Verify if user exists");
		FileUtilityManager.retrieveData("VerifyExistingUser");
		String checkUser = FileUtilityManager.getTestData().get("Full_Name");
		webLoginPO.login();
		webDashBoardPO.navigateToUsers();
		boolean verifyUserStatus = webUsersPO.checkExistingUser(checkUser);
		if (verifyUserStatus) {
			test.pass("VerifyExistingUser");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			ReportNGReport.captureScreenshot(driver, "DeleteExistingUser");
		} else {
			test.log(Status.FAIL, "VerifyExistingUser");
			test.log(Status.INFO, checkUser + ":No Such User Found!");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			ReportNGReport.captureScreenshot(driver, "VerifyExistingUser");
			Assert.fail(checkUser + " : No Such User Found!");
		}
		webDashBoardPO.logout();

	}
}