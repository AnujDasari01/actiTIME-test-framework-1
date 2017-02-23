# ActiTimeProject
A repository to run Functional and UI Test Scripts in Desktop and Android Devices using Java, Selenium, TestNG and Appium.

## Requirements
* Windows OS  
* Eclipse Luna IDE from [here](http://www.eclipse.org/downloads/packages/eclipse-standard-44/lunar)  
* JDK 7 or above from [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)  
* Maven from [here](https://maven.apache.org/download.cgi)  
* Appium from [here](https://appium.io/)  
* PDAnet for Desktop from [here](http://pdanet.co/a/) and PDAnet in Device from Google Play Store.
* Android SDK from [here](https://developer.android.com/studio/index.html) 
* actiTIME Application from [here](https://www.actitime.com/download.php) 

## Pre-requisites
1. Set JAVA_HOME, MAVEN_HOME and ANDROID_HOME paths in Environment Variables.
2. Import Project as an existing maven project in Eclipse. Wait till the workspace is build.

## Let's get started
*Download the project from:*  
https://github.com/AnujDasari/ActiTimeProject

## To run project in Desktop
1. Open cmd prompt, navigate to path of the project and type `mvn compile`.
2. Set the platformType property to Desktop in *GeneralEnvProperties* file and set the desired browser in *StandAloneEnvProperties* file.
3. Open Driver class, right click and run as Java Application.

## To run project in Device
1. Open cmd prompt, navigate to path of the project and type `mvn compile`.
2. Set the platformType to Device in *GeneralEnvProperties* file.
3. Open Driver class, right click and run as Java Application.

## To run project from commandline
`mvn exec:java`
