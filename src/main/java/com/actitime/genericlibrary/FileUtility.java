package com.actitime.genericlibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/*
 * Updated on 1/7/2017
 */

import com.actitime.driver.Driver;

/**
 * This is FileUtility class to read data from Test Data sheets
 **/

public class FileUtility {
	private static HashMap<String, String> testData = new HashMap<String, String>();
	private static ArrayList<String> sheetNames = new ArrayList<>();

	public static HashMap<String, String> getTestData() {
		return testData;
	}

	public static ArrayList<String> getSheetNames() {
		return sheetNames;
	}

	public static void retrieveData(String TestCaseDataId) {
		try {
			FileInputStream fis = new FileInputStream(
					"./src/test/resources/ExcelLib/TestData.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh;
			String env = Driver.getType();
			if (env.equalsIgnoreCase("Desktop")) {
				sh = wb.getSheet("Desktop");
			} else {
				sh = wb.getSheet("Device");
			}
			Row rheader = sh.getRow(0);
			Row rValues = null;
			int i = 0;
			boolean flag = true;
			do {
				i++;
				if (TestCaseDataId.equals(sh.getRow(i).getCell(0)
						.getStringCellValue().toString())) {
					rValues = sh.getRow(i);
					for (int j = 0; j < rheader.getLastCellNum(); j++) {
						testData.put(rheader.getCell(j).getStringCellValue()
								.toString(), rValues.getCell(j)
								.getStringCellValue().toString());
					}
					flag = false;
					i = sh.getLastRowNum();
				}

			} while (i < sh.getLastRowNum() || flag == true);
		} catch (EncryptedDocumentException e) {
			System.out.println("Test data file is excrypted. Please check");
		} catch (InvalidFormatException e) {
			System.out.println("InvalidFormat Exception");
		} catch (IOException e) {
			System.out.println("Exception with respect to Input/Output file");
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("Test Name not found in Test data sheet");
		}

	}

	public static ArrayList<String> getFlaggedMethods(String sheetname) {
		ArrayList<String> testCaseToExecute = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(
					"./src/test/resources/ExcelLib/XMLFlag.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			int i = 0;
			int RowCount = sh.getLastRowNum() + 1;
			do {
				if ((sh.getRow(i).getCell(1).getStringCellValue().toString())
						.equals("Y"))
					testCaseToExecute.add(sh.getRow(i).getCell(0)
							.getStringCellValue().toString());
				i++;

			} while (RowCount != i);

		} catch (EncryptedDocumentException e) {
			System.out.println("Test data file is excrypted. Please check");
		} catch (InvalidFormatException e) {
			System.out.println("InvalidFormat Exception");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Exception with respect to Input/Output file");
		}
		return testCaseToExecute;

	}

	public static ArrayList<String> getNotFlaggedMethods(String sheetname) {
		ArrayList<String> testCaseNotToExecute = new ArrayList<>();
		try {

			FileInputStream fis = new FileInputStream(
					"./src/test/resources/ExcelLib/XMLFlag.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			int i = 0;
			int RowCount = sh.getLastRowNum() + 1;
			do {
				if ((sh.getRow(i).getCell(1).getStringCellValue().toString())
						.equals("N"))
					testCaseNotToExecute.add(sh.getRow(i).getCell(0)
							.getStringCellValue().toString());
				i++;

			} while (RowCount != i);

		} catch (EncryptedDocumentException e) {
			System.out.println("Test data file is excrypted. Please check");
		} catch (InvalidFormatException e) {
			System.out.println("InvalidFormat Exception");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Exception with respect to Input/Output file");
		}
		return testCaseNotToExecute;

	}

	public static ArrayList<String> getSheetNameMethods() {
		try {
			sheetNames.clear();
			FileInputStream fis = new FileInputStream(
					"./src/test/resources/ExcelLib/XMLFlag.xls");
			Workbook wb = WorkbookFactory.create(fis);
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				sheetNames.add(wb.getSheetName(i));
			}
		} catch (EncryptedDocumentException e) {
			System.out.println("Test data file is excrypted. Please check");
		} catch (InvalidFormatException e) {
			System.out.println("InvalidFormat Exception");
		} catch (IOException e) {
			System.out.println("Exception with respect to Input/Output file");
		}
		return sheetNames;
	}

}
