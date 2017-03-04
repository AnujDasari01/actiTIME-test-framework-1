package com.actitime.testscripts;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.actitime.driver.SuperReference;
import com.actitime.genericlibrary.FileUtility;

/*
 * ActiTime Application Test Scripts
 */
public class TestScriptsWeb extends SuperReference {
	@Test
	public void TC01_SignIntoApplication() throws IOException {
		FileUtility.retrieveData("TC01_SignIntoApplication");
		String loginTitle = webLoginPO.login();
		Assert.assertEquals(loginTitle, "actiTIME - Enter Time-Track");
	}

	@Test
	public void TC02_NavigateToTasks() {
		String dashboardTitle = webDashBoardPO.navigateToTasks();
		Assert.assertEquals(dashboardTitle, "actiTIME - Open Tasks");
	}

	@Test
	public void TC03_NavigateToReports() {
		String reportTitle = webDashBoardPO.navigateToReports();
		Assert.assertEquals(reportTitle, "actiTIME - Reports Dashboard");
	}

	@Test
	public void TC04_NavigateToUsers() {
		String usersTitle = webDashBoardPO.navigateToUsers();
		Assert.assertEquals(usersTitle, "actiTIME - User List");
	}

	@Test
	public void TC05_CreateAUser() {
		FileUtility.retrieveData("TC05_CreateAUser");
		String expectedName = FileUtility.getTestData().get("Last_Name") + ", "
				+ FileUtility.getTestData().get("First_Name");
		String createUser = webUsersPO.createUser();
		if (createUser.contains("already exists")) {
			Assert.fail(createUser);
		} else {
			Assert.assertEquals(expectedName, createUser);
		}
	}

	@Test
	public void TC06_VerifyExistingUser() {
		FileUtility.retrieveData("TC06_VerifyExistingUser");
		webUsersPO.checkExistingUser(FileUtility.getTestData().get("Full_Name"));
	}

	@Test
	public void TC07_DeleteUser() {
		FileUtility.retrieveData("TC07_DeleteUser");
		webUsersPO.deleteUser();
	}

	@Test
	public void TC08_SignOutOfApplication() {
		webDashBoardPO.logout();
	}

}
