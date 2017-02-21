package com.actitime.testscripts;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.actitime.driver.SuperReference;
import com.actitime.genericlibrary.FileUtility;

/*
 * Updated on 1/7/2017
 */

/*
 * ActiTime Application Test Scripts
 */
public class TestScriptsWeb extends SuperReference {
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
	public void TC05_CreateAUser() {
		FileUtility.retrieveData("TC05_CreateAUser");
		String expectedName = FileUtility.getTestData().get("Last_Name") + ", "
				+ FileUtility.getTestData().get("First_Name");
		String createUser = usersPO.createUser();
		if (createUser.contains("already exists")) {
			Assert.fail(createUser);
		} else {
			Assert.assertEquals(expectedName, createUser);
		}
	}

	@Test
	public void TC06_VerifyExistingUser() {
		FileUtility.retrieveData("TC06_VerifyExistingUser");
		usersPO.checkExistingUser(FileUtility.getTestData().get("Full_Name"));
	}

	@Test
	public void TC07_DeleteUser() {
		FileUtility.retrieveData("TC07_DeleteUser");
		usersPO.deleteUser();
	}

	@Test
	public void TC08_SignOutOfApplication() {
		dashBoardPO.logout();
	}

}
