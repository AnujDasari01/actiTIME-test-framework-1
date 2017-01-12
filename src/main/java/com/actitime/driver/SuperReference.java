package com.actitime.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.actitime.webpageobjects.DashboardPO;
import com.actitime.webpageobjects.LoginPO;
import com.actitime.webpageobjects.UsersPO;

/*
 * Updated on 1/7/2017
 */

/*
 * Class to invoke and close a browser in Desktop and Device
 */
public class SuperReference {

	private static WebDriver driver;
	public static LoginPO loginPO;
	public static DashboardPO dashBoardPO;
	public static UsersPO usersPO;

	/* Starting Appium from Console */
	AppiumDriverLocalService service = AppiumDriverLocalService
			.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(
							new File("C:/Program Files (x86)/Appium/node.exe"))
					.withAppiumJS(
							new File(
									"C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js")));

	/* Before Suite */
	@BeforeSuite
	public void beforeSuite() throws Exception {
		// System.out.println("In BeforeSuite ...........");
		Driver.getProperties();
		if (Driver.type.equalsIgnoreCase("Device")) {
			appiumStart();
			setup();
		} else if (Driver.type.equalsIgnoreCase("Desktop")) {
			invokeBrowser();
		}
		else if (Driver.type.equalsIgnoreCase("App")) {
			appiumStart();
			setupApp();
		}
	}

	/* For Device Only - START APPIUM SERVER */
	public void appiumStart() {
		if (service.isRunning() == true) {
			service.stop();
			service.start();
		} else {
			service.start();
		}
	}

	/* Method to run test scripts in device browser */
	@SuppressWarnings("rawtypes")
	public void setup() throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("automationName",
				Driver.automationName);
		capabilities.setCapability("deviceName", Driver.deviceName);
		capabilities.setCapability("platformVersion",
				Driver.platformVersion);
		capabilities.setCapability("platformName", Driver.platformName);
		capabilities.setCapability("app", Driver.app);
		capabilities.setCapability("device", Driver.device);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		loginPO = new LoginPO(driver);
		dashBoardPO = new DashboardPO(driver);
		usersPO = new UsersPO(driver);
	}
	
	/* Method to run test scripts on app in device  */
	@SuppressWarnings("rawtypes")
	public void setupApp() throws MalformedURLException, InterruptedException {
		File app = new File("./src/test/resources/Apk/whatsapp.apk");
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("automationName",
				Driver.automationName);
		capabilities.setCapability("deviceName", Driver.deviceName);
		capabilities.setCapability("platformVersion",
				Driver.platformVersion);
		capabilities.setCapability("platformName", Driver.platformName);
		capabilities.setCapability("device", Driver.device);
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appActivity", "com.whatsapp.Main");
		capabilities.setCapability("noReset", false);
		capabilities.setCapability("fullReset", false);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		loginPO = new LoginPO(driver);
		dashBoardPO = new DashboardPO(driver);
		usersPO = new UsersPO(driver);
	}

	/* For Device Only - STOP APPIUM SERVER */
	public void appiumStop() throws IOException {
		service.stop();
		// Report.createShortcut();
	}

	/* Method to invoke a browser */
	public void invokeBrowser() {
		if (Driver.browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"./src/main/resources/GeckoDriver/geckodriver.exe");

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}

		else if (Driver.browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"./src/main/resources/ChromeDriver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		else if (Driver.browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					"./src/main/resources/IEDriver/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			capabilities.setCapability("ensureCleanSession", true);
			capabilities.setCapability("browserName",
					Driver.browserName.equalsIgnoreCase("ie"));
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}

		else {
			System.setProperty("webdriver.gecko.driver",
					"./src/main/resources/GeckoDriver/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		loginPO = new LoginPO(driver);
		dashBoardPO = new DashboardPO(driver);
		usersPO = new UsersPO(driver);

	}

	/* After Suite */
	@AfterSuite
	public void afterSuite() throws IOException, InterruptedException {
		if (Driver.type.equalsIgnoreCase("Device")) {
			appiumStop();
		} else if (Driver.type.equalsIgnoreCase("Desktop")) {
			closeBrowser();
		}
	}

	/* Method to close a browser */
	public static void closeBrowser() throws IOException {
		driver.quit();
		// Report.createShortcut();
	}

}
