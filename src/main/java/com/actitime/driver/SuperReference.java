 package com.actitime.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.actitime.webpageobjects.DashboardPO;
import com.actitime.webpageobjects.LoginPO;
import com.actitime.webpageobjects.UsersPO;

/**
 * This is SuperReference class which runs scripts in desktop and device
 * browsers
 **/
public class SuperReference {

	protected WebDriver driver;
	protected LoginPO loginPO;
	protected DashboardPO dashBoardPO;
	protected UsersPO usersPO;

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
	 * This method invokes a standalone browser
	 * 
	 **/
	public WebDriver invokeBrowser(String browser) throws MalformedURLException {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"./src/main/resources/GeckoDriver/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			return driver;
		}

		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"./src/main/resources/ChromeDriver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			return driver;
		}

		else if (browser.equalsIgnoreCase("ie")) {
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
			driver.manage().window().maximize();
			return driver;
		}
		return driver;
	}

	/**
	 * This method invokes a grid browser
	 * 
	 **/
	public static WebDriver invokeBrowserInGrid(String browser)
			throws MalformedURLException {
		String nodeURL = null;
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("firefox")) {
			nodeURL = Driver.getNodeUrl1();
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
			driver.manage().window().maximize();
			return driver;
		}

		else if (browser.equalsIgnoreCase("chrome")) {
			nodeURL = Driver.getNodeUrl2();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
			driver.manage().window().maximize();
			return driver;
		}

		else if (browser.equalsIgnoreCase("internet explorer")) {
			nodeURL = Driver.getNodeUrl3();
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
			capabilities.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
			driver.manage().window().maximize();
			return driver;
		}

		else {
			nodeURL = Driver.getNodeUrl2();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
			driver.manage().window().maximize();
			return driver;
		}
	}

	/**
	 * This method closes the browser
	 **/
	public void closeBrowser() throws IOException {
		driver.quit();
	}

	/**
	 * This method runs scripts in a device browser
	 **/
	@SuppressWarnings("rawtypes")
	public WebDriver setup(String browser) throws MalformedURLException,
			InterruptedException {
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
		return driver;
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

	/* Before Class */
	@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
	public void beforeClass(String browser) throws Exception {
		if (Driver.getRunOn().equalsIgnoreCase("grid")) {
			if (Driver.getType().equalsIgnoreCase("Desktop")) {
				driver = invokeBrowserInGrid(browser);
				loginPO = PageFactory.initElements(driver, LoginPO.class);
				dashBoardPO = PageFactory.initElements(driver,
						DashboardPO.class);
				usersPO = PageFactory.initElements(driver, UsersPO.class);
			} else if (Driver.getType().equalsIgnoreCase("Device")) {
				appiumStart();
				driver = setup(browser);
				loginPO = PageFactory.initElements(driver, LoginPO.class);
				dashBoardPO = PageFactory.initElements(driver,
						DashboardPO.class);
				usersPO = PageFactory.initElements(driver, UsersPO.class);
			} else if (Driver.getType().equalsIgnoreCase("App")) {
				appiumStart();
				setupApp();
			}
		}

		else if (Driver.getRunOn().equalsIgnoreCase("StandAlone")) {
			if (Driver.getType().equalsIgnoreCase("Desktop")) {
				driver = invokeBrowser(browser);
				loginPO = PageFactory.initElements(driver, LoginPO.class);
				dashBoardPO = PageFactory.initElements(driver,
						DashboardPO.class);
				usersPO = PageFactory.initElements(driver, UsersPO.class);
			} else if (Driver.getType().equalsIgnoreCase("Device")) {
				appiumStart();
				driver = setup(browser);
				loginPO = PageFactory.initElements(driver, LoginPO.class);
				dashBoardPO = PageFactory.initElements(driver,
						DashboardPO.class);
				usersPO = PageFactory.initElements(driver, UsersPO.class);
			} else if (Driver.getType().equalsIgnoreCase("App")) {
				appiumStart();
				setupApp();
			}
		}
	}

	/* After Class */
	@AfterClass
	public void afterClass() throws IOException, InterruptedException {
		if (Driver.getType().equalsIgnoreCase("Device")) {
			appiumStop();
		} else if (Driver.getType().equalsIgnoreCase("Desktop")) {
			closeBrowser();
		}
	}
}
