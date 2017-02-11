package com.actitime.testscripts;

import java.io.IOException;

import org.testng.annotations.Test;

import com.actitime.driver.SuperReference;
import com.actitime.genericlibrary.FileUtility;

/*
 * ActiTime Application Test Scripts
 */
public class TestScriptsDevice extends SuperReference {
	@Test
	public void TC01_SignIntoApplication() throws IOException {
		FileUtility.retrieveData("TC01_SignIntoApplication");
		loginPO.login();
	}

	@Test
	public void TC02_NavigateToTasks() {
		dashBoardPO.navigateToTasks();
	}

	@Test
	public void TC03_NavigateToReports() {
		dashBoardPO.navigateToReports();
	}

	@Test
	public void TC04_NavigateToUsers() {
		dashBoardPO.navigateToUsers();
	}

	@Test
	public void TC05_SignOutOfApplication() {
		dashBoardPO.logout();
	}
}
