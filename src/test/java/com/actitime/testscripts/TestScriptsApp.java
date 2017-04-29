package com.actitime.testscripts;

import org.testng.annotations.Test;

import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

/*
 * ActiTime Application Test Scripts
 */
public class TestScriptsApp extends BaseTest {
	@Test
	public void TC01_CreateNewForm() throws InterruptedException {
		test = extent.createTest("CreateNewForm");
		FileUtilityManager.retrieveData("TC01_CreateNewForm");
		boolean createForm = appCreateNewFormPO.createNewForm();
		ReportNGReport.captureScreenshot(driver, "CreateNewForm ");
		if (createForm) {
			test.pass("TC01_CreateNewForm");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "TC01_CreateNewForm");
			test.log(Status.INFO, "Failed to create a new form");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
		
	}

}
	