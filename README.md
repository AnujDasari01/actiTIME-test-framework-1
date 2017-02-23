# ActiTimeProject
A repository to run Functional and UI Test Scripts in Desktop and Android Devices using Java, Selenium, TestNG and Appium.

## Requirements
* Windows OS  
* Eclipse IDE  
* JDK 7 or above  
* Maven  
* Appium  
* PDAnet 
* Android SDK

## Pre-requisites
1. Set JAVA_HOME, MAVEN_HOME and ANDROID_HOME paths in Environment Variables.
2. Import Project as an existing maven project in Eclipse. Wait till the workspace is build.

## Let's get started
*Download the project from:*  
https://github.com/AnujDasari/ActiTimeProject

## To run project in Desktop
1. Open cmd prompt, navigate to path of the project and type cmd `mvn compile`.
2. Set the platformType property to Desktop in *GeneralEnvProperties* file and set the desired browser in *StandAloneEnvProperties* file.
3. Open Driver class, right click and run as Java Application.

## To run project in Device
1. Open cmd prompt, navigate to path of the project and type cmd `mvn compile`.
2. Set the platformType to Device in *GeneralEnvProperties* file.
3. Open Driver class, right click and run as Java Application.

## To run project from commandline
`mvn exec:java`
