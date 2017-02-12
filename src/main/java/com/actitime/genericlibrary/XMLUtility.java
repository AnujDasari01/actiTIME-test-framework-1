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
import com.actitime.driver.Driver;

/*
 * Updated on 1/7/2017
 */

/**
 * This is XMLUtility class which creates and runs testng xml programmatically
 **/
public class XMLUtility {
	/**
	 * This method creates the XML suite file dynamically for standalone and grid configurations
	 **/
	
	public static void createXml(String sheetName, String browser) throws Exception {
		if (new Driver().getRunOn().equalsIgnoreCase("Grid")) {
			try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory
						.newDocumentBuilder();
				Document document = documentBuilder.newDocument();

				// Type the suite tag element in the XML file
				Element rootElementsuite = document.createElement("suite");
				Element rootElementlisteners = document
						.createElement("listeners");

				// Type the root elements in the XML file
				Element rootElementtest = document.createElement("test");
				Element rootElementParameters = document.createElement("parameter");
				Element rootElementClass = document.createElement("classes");

				// Append values to the root elements
				rootElementParameters.setAttribute("name", "browser");
				rootElementParameters.setAttribute("value", "firefox");
				rootElementsuite.appendChild(rootElementlisteners);
				rootElementsuite.appendChild(rootElementtest);
				rootElementtest.appendChild(rootElementParameters);
				rootElementtest.appendChild(rootElementClass);
				document.appendChild(rootElementsuite);

				// Add listeners
				Element childelementlisteners = document
						.createElement("listener");
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
					Element rootElementgroups = document
							.createElement("methods");
					// Assign attribute to the root elements
					childelementClass.setAttribute("name",
							"com.actitime.testscripts." + sheetName);

					// Assign attribute to the root elements
					rootElementsuite.setAttribute("name", "actiTIME Browser");
					rootElementsuite.setAttribute("preserve-order", "true");
					rootElementsuite.setAttribute("parallel", "tests");
					rootElementtest.setAttribute("name",
							"actiTIME - Desktop Smoke");
					rootElementClass.appendChild(childelementClass);
					childelementClass.appendChild(rootElementgroups);
					

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {

						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
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

					Element rootElementgroups = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass.setAttribute("name",
							"com.actitime.testscripts." + sheetName);

					// Assign attribute to the root elements
					rootElementsuite.setAttribute("name",
							"actiTIME Android Device");
					rootElementsuite.setAttribute("preserve-order", "true");
					rootElementsuite.setAttribute("parallel", "tests");
					rootElementtest.setAttribute("name",
							"actiTIME - Device Smoke");
					rootElementClass.appendChild(childelementClass);
					childelementClass.appendChild(rootElementgroups);

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {

						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);

						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {

						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);

						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
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

					Element rootElementgroups = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass.setAttribute("name",
							"com.actitime.testscripts." + sheetName);

					// Assign attribute to the root elements
					rootElementsuite.setAttribute("name",
							"actiTIME Android App");
					rootElementsuite.setAttribute("preserve-order", "true");
					rootElementsuite.setAttribute("parallel", "tests");
					rootElementtest
							.setAttribute("name", "actiTIME - App Smoke");
					rootElementClass.appendChild(childelementClass);
					childelementClass.appendChild(rootElementgroups);

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {

						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);

						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);

						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
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

				// // Generate the required XML output file
				// TransformerFactory transformerFactory = TransformerFactory
				// .newInstance();
				// Transformer transfor mer =
				// transformerFactory.newTransformer();
				// DOMSource source = new DOMSource(document);

				// Prints all the Generated Xml code in the File object
				StreamResult result = new StreamResult(fstream);
				transformer.transform(source, result);

				// close the generated file.
				out.close();
			} catch (DOMException e) {
				e.printStackTrace();
			}

		} else {
			try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory
						.newDocumentBuilder();
				Document document = documentBuilder.newDocument();

				// Type the suite tag element in the XML file
				Element rootElementsuite = document.createElement("suite");
				Element rootElementlisteners = document
						.createElement("listeners");

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
				Element childelementlisteners = document
						.createElement("listener");
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
					Element rootElementgroups = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass.setAttribute("name",
							"com.actitime.testscripts." + sheetName);

					// Assign attribute to the root elements
					rootElementsuite.setAttribute("name", "actiTIME Browser");
					rootElementsuite.setAttribute("preserve-order", "true");
					rootElementtest.setAttribute("name",
							"actiTIME - Desktop Smoke");
					rootElementClass.appendChild(childelementClass);
					childelementClass.appendChild(rootElementgroups);

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {
						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);
						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {

						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);
						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
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

					Element rootElementgroups = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass.setAttribute("name",
							"com.actitime.testscripts." + sheetName);

					// Assign attribute to the root elements
					rootElementsuite.setAttribute("name",
							"actiTIME Android Device");
					rootElementsuite.setAttribute("preserve-order", "true");
					rootElementtest.setAttribute("name",
							"actiTIME - Device Smoke");
					rootElementClass.appendChild(childelementClass);
					childelementClass.appendChild(rootElementgroups);

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {

						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);

						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {

						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);

						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
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

					Element rootElementgroups = document
							.createElement("methods");

					// Assign attribute to the root elements
					childelementClass.setAttribute("name",
							"com.actitime.testscripts." + sheetName);

					// Assign attribute to the root elements
					rootElementsuite.setAttribute("name",
							"actiTIME Android App");
					rootElementsuite.setAttribute("preserve-order", "true");
					rootElementtest
							.setAttribute("name", "actiTIME - App Smoke");
					rootElementClass.appendChild(childelementClass);
					childelementClass.appendChild(rootElementgroups);

					// Obtain the column value flag = "Y" in a loop
					for (int elementcounterY = 0; elementcounterY < totalnoofelementsflaggedtorun; elementcounterY++) {

						String element = "include";
						Element emy = document.createElement(element);
						ArrayList<String> flagElement1 = FileUtility
								.getFlaggedMethods(sheetName);

						emy.setAttribute("name",
								flagElement1.get(elementcounterY));
						rootElementgroups.appendChild(emy);
					}

					// Obtain the column value flag = "N" in a loop
					for (int elementcounterN = 0; elementcounterN < totalnoofelementsflaggednottorun; elementcounterN++) {
						String element = "exclude";
						Element emn = document.createElement(element);
						ArrayList<String> flagElement2 = FileUtility
								.getNotFlaggedMethods(sheetName);

						emn.setAttribute("name",
								flagElement2.get(elementcounterN));
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

				// // Generate the required XML output file
				// TransformerFactory transformerFactory = TransformerFactory
				// .newInstance();
				// Transformer transfor mer =
				// transformerFactory.newTransformer();
				// DOMSource source = new DOMSource(document);

				// Prints all the Generated Xml code in the File object
				StreamResult result = new StreamResult(fstream);
				transformer.transform(source, result);

				// close the generated file.
				out.close();
			} catch (DOMException e) {
				e.printStackTrace();
			}

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
