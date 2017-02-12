package com.actitime.testscripts;

import java.io.IOException;
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
	public void TC05_CreateAUser() {
		FileUtility.retrieveData("TC05_CreateAUser");
		usersPO.createUser();
	}

	@Test
	public void TC06_VerifyExistingUser() {
		FileUtility.retrieveData("TC06_VerifyExistingUser");
		usersPO.checkExistingUser();
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
