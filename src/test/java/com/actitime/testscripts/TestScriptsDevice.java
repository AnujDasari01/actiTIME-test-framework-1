package com.actitime.testscripts;

import java.io.IOException;
import org.testng.Assert;
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
		String loginTitle = loginPO.login();
		Assert.assertEquals(loginTitle, "actiTIME - Enter Time-Track");
	}

	@Test
	public void TC02_NavigateToTasks() {
		String dashboardTitle = dashBoardPO.navigateToTasks();
		Assert.assertEquals(dashboardTitle, "actiTIME - Open Tasks");
	}

	@Test
	public void TC03_NavigateToReports() {
		String reportTitle = dashBoardPO.navigateToReports();
		Assert.assertEquals(reportTitle, "actiTIME - Reports Dashboard");
	}

	@Test
	public void TC04_NavigateToUsers() {
		String usersTitle = dashBoardPO.navigateToUsers();
		Assert.assertEquals(usersTitle, "actiTIME - User List");
	}

	@Test
	public void TC05_SignOutOfApplication() {
		dashBoardPO.logout();
	}
}
