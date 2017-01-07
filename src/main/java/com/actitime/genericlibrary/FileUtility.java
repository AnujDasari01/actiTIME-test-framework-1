package com.actitime.genericlibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtility {
	public static HashMap<String, String> testData = new HashMap<String, String>();
	public static ArrayList<String> sheetNames = new ArrayList<>();

	public static void retrieveData(String TestCaseDataId) {
		try {
			//System.out.println("Retrieving data from test data sheet");
			FileInputStream fis = new FileInputStream(
					"./src/test/resources/ExcelLib/TestData.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh;
			sh = wb.getSheet("TestData");
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

			FileInputStream fis = new FileInputStream(
					"./src/test/resources/ExcelLib/XMLFlag.xls");
			Workbook wb = WorkbookFactory.create(fis);

			// ClassLoader classLoader = Thread.currentThread()
			// .getContextClassLoader();
			// InputStream input =
			// classLoader.getResourceAsStream("XMLFlag.xls");
			// Workbook wb = WorkbookFactory.create(input);

			// Workbook wb = WorkbookFactory.create(Driver.XMLFlagSheet);
			// System.out.println("Get Sheet name methods Method: "+Driver.XMLFlagSheet
			// );

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

	public static String getPropertyValue(
			String filepath/* FileInputStream env */, String key) {
		String value = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(filepath);

		try {
			Properties prop = new Properties();
			prop.load(input);
			value = prop.getProperty(key);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return value;
	}

}
