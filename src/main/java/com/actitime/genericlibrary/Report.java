package com.actitime.genericlibrary;

/*
 * Updated on 18/12/2016
 */

/*
 * Class with Report Functionality
 */

import java.io.File;
import java.net.URL;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.actitime.driver.Driver;
/*
* Updated on 1/7/2017
*/

/*
* Report class to generate reports with screenshot
*/
public class Report extends TestListenerAdapter {
	private static String dirPath = null;

	// private ExtentReports extent;

	private static Date dtNow = new Date();

	private static String strStartDate = dtNow.toString().replace(":", "_");

	private static String strDateStamp = strStartDate.replace(" ", "_");

	public static String makDir() {

		String s = Driver.getRelativePath() + "/Resources/Reports/" + strDateStamp;
		File srcDir = new File(s);
		srcDir.mkdirs();
		return dirPath = srcDir.getAbsolutePath();
	}

	public static void captureScreenshot(WebDriver driver, String screenshotName) {
		Date dtnow2 = new Date();
		String strStartDate2 = dtnow2.toString().replace(":", "_");
		String strDateStamp2 = strStartDate2.replace(" ", "_");

		try {
			//Thread.sleep(7000);
			Helper.normalWait(driver, 7);
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(dirPath + "/ " + screenshotName
					+ "_" + strDateStamp2

					+ ".png"));
			String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
			System.setProperty(ESCAPE_PROPERTY, "false");
			URL path = new File(dirPath + "/ " + screenshotName + "_"
					+ strDateStamp2 + ".png").toURI().toURL();
			String test = "<a href=" + path +"> click to open screenshot of "
					+ screenshotName + "</a>";
			Reporter.log(screenshotName + test + "<br>");
		}

		catch (Exception e) {
			System.out.println("Exception while taking screenshot "
					+ e.getMessage());
		}
	}
}
