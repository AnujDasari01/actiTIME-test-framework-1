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

/**
 * This is SuperReference class which runs scripts in desktop and device
 * browsers
 **/
public class SuperReference {

	private static WebDriver driver;
	//private String nodeURL;
	protected static LoginPO loginPO;
	protected static DashboardPO dashBoardPO;
	protected static UsersPO usersPO;

	/* Starting Appium from Console */
	AppiumDriverLocalService service = AppiumDriverLocalService
			.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(
							new File("C:/Program Files (x86)/Appium/node.exe"))
					.withAppiumJS(
							new File(
									"C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js")));

	/* For Device Only - START APPIUM SERVER */
	public void appiumStart() {
		if (service.isRunning() == true) {
			service.stop();
			service.start();
		} else {
			service.start();
		}
	}

	/* For Device Only - STOP APPIUM SERVER */
	public void appiumStop() throws IOException {
		service.stop();
	}

	/**
	 * This method invokes a browser
	 * 
	 **/
	public void invokeBrowser() throws MalformedURLException {
		if (Driver.getBrowserName().equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"./src/main/resources/GeckoDriver/geckodriver.exe");

			driver = new FirefoxDriver();
			// nodeURL = "http://192.168.0.29:5555/wd/hub";
			// DesiredCapabilities caps = DesiredCapabilities.firefox();
			// caps.setBrowserName("firefox");
			// caps.setPlatform(Platform.WINDOWS);
			// driver = new RemoteWebDriver(new URL(nodeURL), caps);
			driver.manage().window().maximize();
		}

		else if (Driver.getBrowserName().equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"./src/main/resources/ChromeDriver/chromedriver.exe");
			driver = new ChromeDriver();
			//nodeURL = "http://192.168.0.13:5555/wd/hub";
			// DesiredCapabilities caps = DesiredCapabilities.chrome();
			// caps.setBrowserName("chrome");
			// caps.setPlatform(Platform.WINDOWS);
			// driver = new RemoteWebDriver(new URL(nodeURL), caps);
			driver.manage().window().maximize();
		}

		else if (Driver.getBrowserName().equalsIgnoreCase("ie")) {
			//nodeURL = "http://10.182.64.151:5555/wd/hub";
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,
					false);
			capabilities.setCapability(
					InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			capabilities.setCapability(
					InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			capabilities.setCapability(
					InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(
					InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			System.setProperty("webdriver.ie.driver",
					"./src/main/resources/IEDriver/IEDriverServer.exe");

			driver = new InternetExplorerDriver(capabilities);
			// capabilities.setBrowserName("ie");
			// capabilities.setPlatform(Platform.WINDOWS);
			// driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
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

	/**
	 * This method closes the browser
	 **/
	public static void closeBrowser() throws IOException {
		driver.quit();
	}

	/**
	 * This method runs scripts in a device browser
	 **/
	@SuppressWarnings("rawtypes")
	public void setup() throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities
				.setCapability("automationName", Driver.getAutomationName());
		capabilities.setCapability("deviceName", Driver.getDeviceName());
		capabilities.setCapability("platformName", Driver.getPlatformName());
		capabilities.setCapability("platformVersion",
				Driver.getPlatformVersion());
		capabilities.setCapability("app", Driver.getApp());
		capabilities.setCapability("device", Driver.getDevice());
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		loginPO = new LoginPO(driver);
		dashBoardPO = new DashboardPO(driver);
		usersPO = new UsersPO(driver);
	}

	/**
	 * This method runs scripts in a mobile application
	 **/
	@SuppressWarnings("rawtypes")
	public void setupApp() throws MalformedURLException, InterruptedException {
		File app = new File("./src/test/resources/Apk/FormApp.apk");
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("appium-version", "1.4.16.1");
		capabilities
				.setCapability("automationName", Driver.getAutomationName());
		capabilities.setCapability("deviceName", Driver.getDeviceName());
		capabilities.setCapability("platformName", Driver.getPlatformName());
		capabilities.setCapability("platformVersion",
				Driver.getPlatformVersion());
		capabilities.setCapability("device", Driver.getDevice());
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("noReset", Driver.getNoReset());
		capabilities.setCapability("fullReset", Driver.getFullReset());
		capabilities.setCapability("appActivity", "com.anuj.task1.FormLogin");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	/* Before Suite */
	@BeforeSuite
	public void beforeSuite() throws Exception {

		System.out.println("In BeforeSuite ...........");
		Driver.getProperties();
		if (Driver.getType().equalsIgnoreCase("Device")) {
			appiumStart();
			setup();
		} else if (Driver.getType().equalsIgnoreCase("Desktop")) {
			invokeBrowser();
		} else if (Driver.getType().equalsIgnoreCase("App")) {
			appiumStart();
			setupApp();
		}
	}

	/* After Suite */
	@AfterSuite
	public void afterSuite() throws IOException, InterruptedException {
		if (Driver.getType().equalsIgnoreCase("Device")) {
			appiumStop();
		} else if (Driver.getType().equalsIgnoreCase("Desktop")) {
			closeBrowser();
		}
	}
}
