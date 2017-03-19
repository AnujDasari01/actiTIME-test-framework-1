package com.actitime.testscripts;

import org.testng.annotations.Test;

import com.actitime.driver.SuperReference;
import com.actitime.genericlibrary.FileUtility;
import com.actitime.reports.ExtentReport;
import com.actitime.reports.Report;
import com.aventstack.extentreports.Status;

/*
 * ActiTime Application Test Scripts
 */
public class TestScriptsApp extends SuperReference {
	@Test
	public void TC01_CreateNewForm() throws InterruptedException {
		test = extent.createTest("CreateNewForm");
		FileUtility.retrieveData("TC01_CreateNewForm");
		boolean createForm = appCreateNewFormPO.createNewForm();
		Report.captureScreenshot(driver, "CreateNewForm ");
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
