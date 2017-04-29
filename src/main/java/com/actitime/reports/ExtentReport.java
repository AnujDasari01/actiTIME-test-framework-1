package com.actitime.reports;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.actitime.driver.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	private static String dirPath = null;

	private static Date dtNow = new Date();

	private static String strStartDate = dtNow.toString().replace(":", "_");

	private static String strDateStamp = strStartDate.replace(" ", "_");

	private static ExtentReports extent;

	private static ExtentTest test;

	private static ExtentHtmlReporter htmlReporter;

	/**
	 * This method creates the execution report
	 **/
	public static String makDir() {
		String s = Driver.getRelativePath() + "/Resources/ExtentReports/"
				+ strDateStamp;
		File srcDir = new File(s);
		srcDir.mkdirs();
		return dirPath = srcDir.getAbsolutePath() + "./ExtentReport.html";
	}

	public static ExtentReports getExtent() {
		if (extent != null)
			return extent; // avoid creating new instance of html file
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {
		htmlReporter = new ExtentHtmlReporter(dirPath);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("ActiTIME Automation Report");
		htmlReporter.config().setReportName("Automated Test Cycle");
		return htmlReporter;
	}

	public static ExtentTest createTest(String name, String description) {
		test = extent.createTest(name, description);
		return test;
	}

	public static void captureAndDisplayScreenShot(WebDriver ldriver,
			ExtentTest eTest) {
		Date dtnow2 = new Date();
		String strStartDate2 = dtnow2.toString().replace(":", "_");
		String strDateStamp2 = strStartDate2.replace(" ", "_");
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {
			String screenshotPath = dirPath + "_" + strDateStamp2 + ".png";
			FileUtils.copyFile(src, new File(screenshotPath));
			eTest.addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}