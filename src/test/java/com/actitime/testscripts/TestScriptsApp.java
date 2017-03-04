package com.actitime.testscripts;

import org.testng.annotations.Test;
import com.actitime.driver.SuperReference;

/*
 * ActiTime Application Test Scripts
 */
public class TestScriptsApp extends SuperReference {
	@Test
	public void TC01_FillFormDetails() throws InterruptedException {
		appCreateNewFormPO.createNewForm();
	}

}
