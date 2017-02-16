package com.actitime.genericlibrary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.testng.TestNG;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

/*
 * Updated on 1/7/2017
 */

/**
 * This is XMLUtility class which creates and runs testng xml programmatically
 **/
public class XMLUtility {

	/**
	 * This method creates the XML suite file dynamically for standalone and
	 * grid configurations
	 **/
	public static void createXmlForGridConfig(String sheetName,
			String browser1, String browser2, String browser3) throws Exception {
		try {

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// Type the suite tag element in the XML file
			Element rootElementsuite = document.createElement("suite");
			Element rootElementlisteners = document.createElement("listeners");

			// Assign attribute to the root elements
			rootElementsuite.setAttribute("preserve-order", "true");
			rootElementsuite.setAttribute("parallel", "tests");
			rootElementsuite.appendChild(rootElementlisteners);

			// Add listeners
			Element childelementlisteners = document.createElement("listener");
			childelementlisteners.setAttribute("class-name",
					"org.uncommons.reportng.HTMLReporter");
			rootElementlisteners.appendChild(childelementlisteners);
			childelementlisteners = document.createElement("listener");
			childelementlisteners.setAttribute("class-name",
					"org.uncommons.reportng.JUnitXMLReporter");
			rootElementlisteners.appendChild(childelementlisteners);
			document.appendChild(rootElementsuite);

			/* My Logic */
			if (sheetName.equalsIgnoreCase("TestScriptsWeb")) {
				System.out.println("Web Test Scripts!");
				rootElementsuite.setAttribute("name", "actiTIME Browser");

				if (browser1 == null) {
					/**
					 * This is configuration for test 1
					 **/
					// Type the root elements in the XML file
					Element rootElementtest1 = document.createElement("test");
					Element rootElementParameters1 = document
							.createElement("parameter");
					Element rootElementClass1 = document
							.createElement("classes");

					// Append test1 to the suite
					rootElementsuite.appendChild(rootElementtest1);

					// Add test1 parameters
					rootElementParameters1.setAttribute("name", "browser");
					rootElementParameters1.setAttribute("value", browser2);

					// Append test1 parameters and class
					rootElementtest1.appendChild(rootElementParameters1);
					rootElementtest1.appendChild(rootElementClass1);

					// Add description for test1
					Element childelementClass1 = document
							.createElement("class");
					Element rootElementgroups1 = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass1.setAttribute("name",
							"com.actitime.testscripts." + sheetName);
					rootElementtest1.setAttribute("name", "actiTIME - "
							+ browser2 + " Smoke Test");
					rootElementClass1.appendChild(childelementClass1);
					childelementClass1.appendChild(rootElementgroups1);

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggedtorun1 = FileUtility
							.getFlaggedMethods(sheetName).size();

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggednottorun1 = FileUtility
							.getNotFlaggedMethods(sheetName).size();

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun1; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups1.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun1; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
						rootElementgroups1.appendChild(emn);
					}

					/**
					 * This is configuration for test 2
					 **/
					// Type the root elements in the XML file
					Element rootElementtest2 = document.createElement("test");
					Element rootElementParameters2 = document
							.createElement("parameter");
					Element rootElementClass2 = document
							.createElement("classes");

					// Append test2 to the suite
					rootElementsuite.appendChild(rootElementtest2);

					// Add test2 parameters
					rootElementParameters2.setAttribute("name", "browser");
					rootElementParameters2.setAttribute("value", browser3);

					// Append test2 parameters and class
					rootElementtest2.appendChild(rootElementParameters2);
					rootElementtest2.appendChild(rootElementClass2);

					// Add description for test2
					Element childelementClass2 = document
							.createElement("class");
					Element rootElementgroups2 = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass2.setAttribute("name",
							"com.actitime.testscripts." + sheetName);
					rootElementtest2.setAttribute("name", "actiTIME - "
							+ browser3 + " Smoke Test");
					rootElementClass2.appendChild(childelementClass2);
					childelementClass2.appendChild(rootElementgroups2);

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggedtorun2 = FileUtility
							.getFlaggedMethods(sheetName).size();

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggednottorun2 = FileUtility
							.getNotFlaggedMethods(sheetName).size();

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun2; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement3 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement3.get(elementcounterY));
						rootElementgroups2.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun2; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement4 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement4.get(elementcounterN));
						rootElementgroups2.appendChild(emn);
					}
				}

				if (browser2 == null) {
					/**
					 * This is configuration for test 1
					 **/
					// Type the root elements in the XML file
					Element rootElementtest1 = document.createElement("test");
					Element rootElementParameters1 = document
							.createElement("parameter");
					Element rootElementClass1 = document
							.createElement("classes");

					// Append test1 to the suite
					rootElementsuite.appendChild(rootElementtest1);

					// Add test1 parameters
					rootElementParameters1.setAttribute("name", "browser");
					rootElementParameters1.setAttribute("value", browser1);

					// Append test1 parameters and class
					rootElementtest1.appendChild(rootElementParameters1);
					rootElementtest1.appendChild(rootElementClass1);

					// Add description for test1
					Element childelementClass1 = document
							.createElement("class");
					Element rootElementgroups1 = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass1.setAttribute("name",
							"com.actitime.testscripts." + sheetName);
					rootElementtest1.setAttribute("name", "actiTIME - "
							+ browser1 + " Smoke Test");
					rootElementClass1.appendChild(childelementClass1);
					childelementClass1.appendChild(rootElementgroups1);

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggedtorun1 = FileUtility
							.getFlaggedMethods(sheetName).size();

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggednottorun1 = FileUtility
							.getNotFlaggedMethods(sheetName).size();

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun1; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups1.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun1; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
						rootElementgroups1.appendChild(emn);
					}

					/**
					 * This is configuration for test 2
					 **/
					// Type the root elements in the XML file
					Element rootElementtest2 = document.createElement("test");
					Element rootElementParameters2 = document
							.createElement("parameter");
					Element rootElementClass2 = document
							.createElement("classes");

					// Append test2 to the suite
					rootElementsuite.appendChild(rootElementtest2);

					// Add test2 parameters
					rootElementParameters2.setAttribute("name", "browser");
					rootElementParameters2.setAttribute("value", browser3);

					// Append test2 parameters and class
					rootElementtest2.appendChild(rootElementParameters2);
					rootElementtest2.appendChild(rootElementClass2);

					// Add description for test2
					Element childelementClass2 = document
							.createElement("class");
					Element rootElementgroups2 = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass2.setAttribute("name",
							"com.actitime.testscripts." + sheetName);
					rootElementtest2.setAttribute("name", "actiTIME - "
							+ browser3 + " Smoke Test");
					rootElementClass2.appendChild(childelementClass2);
					childelementClass2.appendChild(rootElementgroups2);

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggedtorun2 = FileUtility
							.getFlaggedMethods(sheetName).size();

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggednottorun2 = FileUtility
							.getNotFlaggedMethods(sheetName).size();

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun2; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement3 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement3.get(elementcounterY));
						rootElementgroups2.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun2; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement4 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement4.get(elementcounterN));
						rootElementgroups2.appendChild(emn);
					}

				}

				if (browser3 == null) {
					/**
					 * This is configuration for test 1
					 **/
					// Type the root elements in the XML file
					Element rootElementtest1 = document.createElement("test");
					Element rootElementParameters1 = document
							.createElement("parameter");
					Element rootElementClass1 = document
							.createElement("classes");

					// Append test1 to the suite
					rootElementsuite.appendChild(rootElementtest1);

					// Add test1 parameters
					rootElementParameters1.setAttribute("name", "browser");
					rootElementParameters1.setAttribute("value", browser1);

					// Append test1 parameters and class
					rootElementtest1.appendChild(rootElementParameters1);
					rootElementtest1.appendChild(rootElementClass1);

					// Add description for test1
					Element childelementClass1 = document
							.createElement("class");
					Element rootElementgroups1 = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass1.setAttribute("name",
							"com.actitime.testscripts." + sheetName);
					rootElementtest1.setAttribute("name", "actiTIME - "
							+ browser1 + " Smoke Test");
					rootElementClass1.appendChild(childelementClass1);
					childelementClass1.appendChild(rootElementgroups1);

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggedtorun1 = FileUtility
							.getFlaggedMethods(sheetName).size();

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggednottorun1 = FileUtility
							.getNotFlaggedMethods(sheetName).size();

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun1; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups1.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun1; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
						rootElementgroups1.appendChild(emn);
					}

					/**
					 * This is configuration for test 2
					 **/
					// Type the root elements in the XML file
					Element rootElementtest2 = document.createElement("test");
					Element rootElementParameters2 = document
							.createElement("parameter");
					Element rootElementClass2 = document
							.createElement("classes");

					// Append test2 to the suite
					rootElementsuite.appendChild(rootElementtest2);

					// Add test2 parameters
					rootElementParameters2.setAttribute("name", "browser");
					rootElementParameters2.setAttribute("value", browser2);

					// Append test2 parameters and class
					rootElementtest2.appendChild(rootElementParameters2);
					rootElementtest2.appendChild(rootElementClass2);

					// Add description for test2
					Element childelementClass2 = document
							.createElement("class");
					Element rootElementgroups2 = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass2.setAttribute("name",
							"com.actitime.testscripts." + sheetName);
					rootElementtest2.setAttribute("name", "actiTIME - "
							+ browser2 + " Smoke Test");
					rootElementClass2.appendChild(childelementClass2);
					childelementClass2.appendChild(rootElementgroups2);

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggedtorun2 = FileUtility
							.getFlaggedMethods(sheetName).size();

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggednottorun2 = FileUtility
							.getNotFlaggedMethods(sheetName).size();

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun2; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement3 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement3.get(elementcounterY));
						rootElementgroups2.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun2; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement4 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement4.get(elementcounterN));
						rootElementgroups2.appendChild(emn);
					}

				}
				
				if(browser1!=null && browser2!=null && browser3!=null) {
					/**
					 * This is configuration for test 1
					 **/
					// Type the root elements in the XML file
					Element rootElementtest1 = document.createElement("test");
					Element rootElementParameters1 = document
							.createElement("parameter");
					Element rootElementClass1 = document
							.createElement("classes");

					// Append test2 to the suite
					rootElementsuite.appendChild(rootElementtest1);

					// Add test2 parameters
					rootElementParameters1.setAttribute("name", "browser");
					rootElementParameters1.setAttribute("value", browser1);

					// Append test2 parameters and class
					rootElementtest1.appendChild(rootElementParameters1);
					rootElementtest1.appendChild(rootElementClass1);

					// Add description for test2
					Element childelementClass1 = document
							.createElement("class");
					Element rootElementgroups1 = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass1.setAttribute("name",
							"com.actitime.testscripts." + sheetName);
					rootElementtest1.setAttribute("name", "actiTIME - "
							+ browser1 + " Smoke Test");
					rootElementClass1.appendChild(childelementClass1);
					childelementClass1.appendChild(rootElementgroups1);

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggedtorun1 = FileUtility
							.getFlaggedMethods(sheetName).size();

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggednottorun1 = FileUtility
							.getNotFlaggedMethods(sheetName).size();

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun1; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups1.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun1; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
						rootElementgroups1.appendChild(emn);
					}
					
					/**
					 * This is configuration for test 2
					 **/
					// Type the root elements in the XML file
					Element rootElementtest2 = document.createElement("test");
					Element rootElementParameters2 = document
							.createElement("parameter");
					Element rootElementClass2 = document
							.createElement("classes");

					// Append test2 to the suite
					rootElementsuite.appendChild(rootElementtest2);

					// Add test2 parameters
					rootElementParameters2.setAttribute("name", "browser");
					rootElementParameters2.setAttribute("value", browser2);

					// Append test2 parameters and class
					rootElementtest2.appendChild(rootElementParameters2);
					rootElementtest2.appendChild(rootElementClass2);

					// Add description for test2
					Element childelementClass2 = document
							.createElement("class");
					Element rootElementgroups2 = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass2.setAttribute("name",
							"com.actitime.testscripts." + sheetName);
					rootElementtest2.setAttribute("name", "actiTIME - "
							+ browser2 + " Smoke Test");
					rootElementClass2.appendChild(childelementClass2);
					childelementClass2.appendChild(rootElementgroups2);

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggedtorun2 = FileUtility
							.getFlaggedMethods(sheetName).size();

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggednottorun2 = FileUtility
							.getNotFlaggedMethods(sheetName).size();

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun2; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement3 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement3.get(elementcounterY));
						rootElementgroups2.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun2; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement4 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement4.get(elementcounterN));
						rootElementgroups2.appendChild(emn);
					}

					/**
					 * This is configuration for test 3
					 **/
					// Type the root elements in the XML file
					Element rootElementtest3 = document.createElement("test");
					Element rootElementParameters3 = document
							.createElement("parameter");
					Element rootElementClass3 = document
							.createElement("classes");

					// Append test2 to the suite
					rootElementsuite.appendChild(rootElementtest3);

					// Add test2 parameters
					rootElementParameters3.setAttribute("name", "browser");
					rootElementParameters3.setAttribute("value", browser3);

					// Append test2 parameters and class
					rootElementtest3.appendChild(rootElementParameters3);
					rootElementtest3.appendChild(rootElementClass3);

					// Add description for test2
					Element childelementClass3 = document
							.createElement("class");
					Element rootElementgroups3 = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass3.setAttribute("name",
							"com.actitime.testscripts." + sheetName);
					rootElementtest3.setAttribute("name", "actiTIME - "
							+ browser3 + " Smoke Test");
					rootElementClass3.appendChild(childelementClass3);
					childelementClass3.appendChild(rootElementgroups3);

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggedtorun3 = FileUtility
							.getFlaggedMethods(sheetName).size();

					// Get the number of parameter to be created in XML
					int totalnoofelementsflaggednottorun3 = FileUtility
							.getNotFlaggedMethods(sheetName).size();

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun3; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement5 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement5.get(elementcounterY));
						rootElementgroups3.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun3; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement6 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement6.get(elementcounterN));
						rootElementgroups3.appendChild(emn);
					}

				}
			}

			if (sheetName.equalsIgnoreCase("TestScriptsDevice")) {
				System.out.println("Device Test Scripts!");
				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggedtorun = FileUtility
						.getFlaggedMethods(sheetName).size();

				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggednottorun = FileUtility
						.getNotFlaggedMethods(sheetName).size();

				// Type the parameter set of lines in the XML file
				Element childelementClass = document.createElement("class");

				Element rootElementgroups = document.createElement("methods");

				// Assign attribute to the root elements
				childelementClass.setAttribute("name",
						"com.actitime.testscripts." + sheetName);

				// Assign attribute to the root elements
				rootElementsuite
						.setAttribute("name", "actiTIME Android Device");
				rootElementsuite.setAttribute("preserve-order", "true");
				rootElementsuite.setAttribute("parallel", "tests");
				// rootElementtest.setAttribute("name",
				// "actiTIME - Device Smoke");
				// rootElementClass.appendChild(childelementClass);
				childelementClass.appendChild(rootElementgroups);

				// Obtain the column value flag = "Y" in a loop
				for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {

					String element = "include";
					Element emy = document.createElement(element);
					ArrayList<String> flagElement1 = FileUtility
							.getFlaggedMethods(sheetName);

					emy.setAttribute("name", flagElement1.get(elementcounterY));
					rootElementgroups.appendChild(emy);
				}

				// Obtain the column value flag = "N" in a loop
				for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {

					String element = "exclude";
					Element emn = document.createElement(element);
					ArrayList<String> flagElement2 = FileUtility
							.getNotFlaggedMethods(sheetName);

					emn.setAttribute("name", flagElement2.get(elementcounterN));
					rootElementgroups.appendChild(emn);
				}

			}

			if (sheetName.equalsIgnoreCase("TestScriptsApp")) {
				System.out.println("App Test Scripts!");
				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggedtorun = FileUtility
						.getFlaggedMethods(sheetName).size();

				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggednottorun = FileUtility
						.getNotFlaggedMethods(sheetName).size();

				// Type the parameter set of lines in the XML file
				Element childelementClass = document.createElement("class");

				Element rootElementgroups = document.createElement("methods");

				// Assign attribute to the root elements
				childelementClass.setAttribute("name",
						"com.actitime.testscripts." + sheetName);

				// Assign attribute to the root elements
				rootElementsuite.setAttribute("name", "actiTIME Android App");
				rootElementsuite.setAttribute("preserve-order", "true");
				rootElementsuite.setAttribute("parallel", "tests");
				// rootElementtest.setAttribute("name", "actiTIME - App Smoke");
				// rootElementClass.appendChild(childelementClass);
				childelementClass.appendChild(rootElementgroups);

				// Obtain the column value flag = "Y" in a loop
				for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {

					String element = "include";
					Element emy = document.createElement(element);
					ArrayList<String> flagElement1 = FileUtility
							.getFlaggedMethods(sheetName);

					emy.setAttribute("name", flagElement1.get(elementcounterY));
					rootElementgroups.appendChild(emy);
				}

				// Obtain the column value flag = "N" in a loop
				for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {
					String element = "exclude";
					Element emn = document.createElement(element);
					ArrayList<String> flagElement2 = FileUtility
							.getNotFlaggedMethods(sheetName);

					emn.setAttribute("name", flagElement2.get(elementcounterN));
					rootElementgroups.appendChild(emn);
				}

			}

			// Generate the file.
			FileWriter fstream = new FileWriter("./testng.xml");
			BufferedWriter out = new BufferedWriter(fstream);

			// Generate the required XML output file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMImplementation domImpl = document.getImplementation();
			DocumentType doctype = domImpl.createDocumentType("suite",
					"SYSTEM", "http://testng.org/testng-1.0.dtd");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
					doctype.getSystemId());
			DOMSource source = new DOMSource(document);

			// Prints all the Generated Xml code in the File object
			StreamResult result = new StreamResult(fstream);
			transformer.transform(source, result);

			// close the generated file.
			out.close();
		} catch (DOMException e) {
			e.printStackTrace();
		}

	}

	
	public static void createXmlForStandAloneConfig(String sheetName,
			String browser) throws Exception {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// Type the suite tag element in the XML file
			Element rootElementsuite = document.createElement("suite");
			Element rootElementlisteners = document.createElement("listeners");

			// Type the root elements in the XML file
			Element rootElementtest = document.createElement("test");
			Element rootElementParameters = document.createElement("parameter");
			Element rootElementClass = document.createElement("classes");

			// Append values to the root elements
			rootElementParameters.setAttribute("name", "browser");
			rootElementParameters.setAttribute("value", browser);
			rootElementsuite.appendChild(rootElementlisteners);
			rootElementsuite.appendChild(rootElementtest);
			rootElementtest.appendChild(rootElementParameters);
			rootElementtest.appendChild(rootElementClass);
			document.appendChild(rootElementsuite);

			// Add listeners
			Element childelementlisteners = document.createElement("listener");
			childelementlisteners.setAttribute("class-name",
					"org.uncommons.reportng.HTMLReporter");
			rootElementlisteners.appendChild(childelementlisteners);
			childelementlisteners = document.createElement("listener");
			childelementlisteners.setAttribute("class-name",
					"org.uncommons.reportng.JUnitXMLReporter");
			rootElementlisteners.appendChild(childelementlisteners);

			/* My Logic */
			if (sheetName.equalsIgnoreCase("TestScriptsWeb")) {
				System.out.println("Web Test Scripts!");

				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggedtorun = FileUtility
						.getFlaggedMethods(sheetName).size();

				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggednottorun = FileUtility
						.getNotFlaggedMethods(sheetName).size();

				// Type the parameter set of lines in the XML file
				Element childelementClass = document.createElement("class");
				Element rootElementgroups = document.createElement("methods");

				// Assign attribute to the root elements
				childelementClass.setAttribute("name",
						"com.actitime.testscripts." + sheetName);

				// Assign attribute to the root elements
				rootElementsuite.setAttribute("name", "actiTIME Browser");
				rootElementsuite.setAttribute("preserve-order", "true");
				rootElementtest
						.setAttribute("name", "actiTIME - Desktop Smoke");
				rootElementClass.appendChild(childelementClass);
				childelementClass.appendChild(rootElementgroups);

				// Obtain the column value flag = "Y" in a loop
				for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {
					String element = "include";
					Element emy = document.createElement(element);
					ArrayList<String> flagElement1 = FileUtility
							.getFlaggedMethods(sheetName);
					emy.setAttribute("name", flagElement1.get(elementcounterY));
					rootElementgroups.appendChild(emy);
				}

				// Obtain the column value flag = "N" in a loop
				for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {

					String element = "exclude";
					Element emn = document.createElement(element);
					ArrayList<String> flagElement2 = FileUtility
							.getNotFlaggedMethods(sheetName);
					emn.setAttribute("name", flagElement2.get(elementcounterN));
					rootElementgroups.appendChild(emn);
				}

			}

			if (sheetName.equalsIgnoreCase("TestScriptsDevice")) {
				System.out.println("Device Test Scripts!");
				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggedtorun = FileUtility
						.getFlaggedMethods(sheetName).size();

				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggednottorun = FileUtility
						.getNotFlaggedMethods(sheetName).size();

				// Type the parameter set of lines in the XML file
				Element childelementClass = document.createElement("class");

				Element rootElementgroups = document.createElement("methods");

				// Assign attribute to the root elements
				childelementClass.setAttribute("name",
						"com.actitime.testscripts." + sheetName);

				// Assign attribute to the root elements
				rootElementsuite
						.setAttribute("name", "actiTIME Android Device");
				rootElementsuite.setAttribute("preserve-order", "true");
				rootElementtest.setAttribute("name", "actiTIME - Device Smoke");
				rootElementClass.appendChild(childelementClass);
				childelementClass.appendChild(rootElementgroups);

				// Obtain the column value flag = "Y" in a loop
				for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {

					String element = "include";
					Element emy = document.createElement(element);
					ArrayList<String> flagElement1 = FileUtility
							.getFlaggedMethods(sheetName);

					emy.setAttribute("name", flagElement1.get(elementcounterY));
					rootElementgroups.appendChild(emy);
				}

				// Obtain the column value flag = "N" in a loop
				for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {

					String element = "exclude";
					Element emn = document.createElement(element);
					ArrayList<String> flagElement2 = FileUtility
							.getNotFlaggedMethods(sheetName);

					emn.setAttribute("name", flagElement2.get(elementcounterN));
					rootElementgroups.appendChild(emn);
				}

			}

			if (sheetName.equalsIgnoreCase("TestScriptsApp")) {
				System.out.println("App Test Scripts!");
				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggedtorun = FileUtility
						.getFlaggedMethods(sheetName).size();

				// Get the number of parameter to be created in XML
				int totalnoofelementsflaggednottorun = FileUtility
						.getNotFlaggedMethods(sheetName).size();

				// Type the parameter set of lines in the XML file
				Element childelementClass = document.createElement("class");

				Element rootElementgroups = document.createElement("methods");

				// Assign attribute to the root elements
				childelementClass.setAttribute("name",
						"com.actitime.testscripts." + sheetName);

				// Assign attribute to the root elements
				rootElementsuite.setAttribute("name", "actiTIME Android App");
				rootElementsuite.setAttribute("preserve-order", "true");
				rootElementtest.setAttribute("name", "actiTIME - App Smoke");
				rootElementClass.appendChild(childelementClass);
				childelementClass.appendChild(rootElementgroups);

				// Obtain the column value flag = "Y" in a loop
				for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {

					String element = "include";
					Element emy = document.createElement(element);
					ArrayList<String> flagElement1 = FileUtility
							.getFlaggedMethods(sheetName);

					emy.setAttribute("name", flagElement1.get(elementcounterY));
					rootElementgroups.appendChild(emy);
				}

				// Obtain the column value flag = "N" in a loop
				for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {
					String element = "exclude";
					Element emn = document.createElement(element);
					ArrayList<String> flagElement2 = FileUtility
							.getNotFlaggedMethods(sheetName);

					emn.setAttribute("name", flagElement2.get(elementcounterN));
					rootElementgroups.appendChild(emn);
				}

			}

			// Generate the file.
			FileWriter fstream = new FileWriter("./testng.xml");
			BufferedWriter out = new BufferedWriter(fstream);

			// Generate the required XML output file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMImplementation domImpl = document.getImplementation();
			DocumentType doctype = domImpl.createDocumentType("suite",
					"SYSTEM", "http://testng.org/testng-1.0.dtd");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
					doctype.getSystemId());
			DOMSource source = new DOMSource(document);

			// Prints all the Generated Xml code in the File object
			StreamResult result = new StreamResult(fstream);
			transformer.transform(source, result);

			// close the generated file.
			out.close();
		} catch (DOMException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method runs the XML suite file dynamically
	 **/

	public static void autoRunXml() {
		List<String> files = new ArrayList<String>();
		files.add("./testng.xml");
		TestNG tng = new TestNG();
		tng.setOutputDirectory(Report.makDir());
		tng.setUseDefaultListeners(false);
		tng.setTestSuites(files);
		tng.run();

	}
}
