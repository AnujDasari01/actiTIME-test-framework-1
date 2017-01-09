package com.actitime.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

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
	public static String EnvPropFilePath;
	public static String app;
	public static String browserName;
	public static String automationName;
	public static String deviceName;
	public static String platformName;
	public static String platformVersion;
	public static String noReset;
	public static String fullReset;
	public static String url;
	public static String type;
	public static String device;
	
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

	
	/*Read properties from properties file*/
	public void getProperties() throws IOException {

		EnvPropFilePath = "Env.properties";

		Properties prop = new Properties();

		InputStream input = new FileInputStream("Env.properties");

		prop.load(input);

		Set<Object> set = prop.keySet();
		Iterator<Object> it = set.iterator();

		for (int i = 0; i < set.size(); i++) {
			String key = (String) it.next();
			// String value = prop.getProperty(key);

			if (key.equalsIgnoreCase("browserName")) {
				browserName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("platformType")) {
				type = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("url")) {
				url = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("platformName")) {
				platformName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("platformVersion")) {
				platformVersion = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("automationName")) {
				automationName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("deviceName")) {
				deviceName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("noReset")) {
				noReset = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("fullReset")) {
				fullReset = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("app")) {
				app = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("device")) {
				device = prop.getProperty("device");
			} else
				continue;
		}
		
	}

	/* Before Suite */
	@BeforeSuite
	public void beforeSuite() throws Exception {
		// System.out.println("In BeforeSuite ...........");
		getProperties();

		if (SuperReference.type.equalsIgnoreCase("Device")) {
			appiumStart();
			setup();
		} else if (SuperReference.type.equalsIgnoreCase("Desktop")) {
			invokeBrowser();
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
				SuperReference.automationName);
		capabilities.setCapability("deviceName", SuperReference.deviceName);
		capabilities.setCapability("platformVersion",
				SuperReference.platformVersion);
		capabilities.setCapability("platformName", SuperReference.platformName);
		capabilities.setCapability("app", SuperReference.app);
		capabilities.setCapability("device", SuperReference.device);
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
		if (SuperReference.browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"./src/main/resources/GeckoDriver/geckodriver.exe");

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}

		else if (SuperReference.browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"./src/main/resources/ChromeDriver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		else if (SuperReference.browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver",
					"./src/main/resources/IEDriver/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			capabilities.setCapability("ensureCleanSession", true);
			capabilities.setCapability("browserName",
					SuperReference.browserName.equalsIgnoreCase("ie"));
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
		if (SuperReference.type.equalsIgnoreCase("Device")) {
			appiumStop();
		} else if (SuperReference.type.equalsIgnoreCase("Desktop")) {
			closeBrowser();
		}
	}

	/* Method to close a browser */
	public static void closeBrowser() throws IOException {
		driver.quit();
		// Report.createShortcut();
	}

}
