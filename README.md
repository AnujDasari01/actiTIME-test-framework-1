# ActiTimeProject
A repository to run Functional and UI Test Scripts in Desktop and Android Devices using Java, Selenium, TestNG and Appium. The framework can run test scripts in chrome, firefox and internet explorer browsers in Desktop/Laptop/PC and in chrome browser on Android Device. The framework can also be used to run test scripts for an Android App. 

## Downloads and Requirements
* **Windows OS**  
* **Eclipse Luna IDE** 
* **JDK 8**  
* **Maven** 
* **Maven Plugin for Eclipse IDE** 
* **Appium**  
* **PDAnet** 
* **Android SDK** 
* **actiTIME Application** 

## Let's get started
**Download the project from:**  
https://github.com/AnujDasari/ActiTimeProject

## Pre-requisites
1. Set JAVA_HOME, MAVEN_HOME and ANDROID_HOME Environment Variables.

2. Add the above set variables to PATH System Variable.

3. Install Maven Plugin in Eclipse IDE

4. Import the downloaded project as an existing maven project.

## To run project in Desktop
1. Open cmd prompt, navigate to path of the project and type `mvn compile`.

2. Set the **runOn** property to **StandAlone** and **platformType** property to **Desktop** in *GeneralEnvProperties* file and set the desired **browser** in *StandAloneEnvProperties* file.

3. Open Driver class, right click and run as Java Application.

## To run project in Device
1. Connect Device to PC/laptop. Wait till PDAnet on the PC/laptop installs the neccesary drivers for the connected Android Device; Enable USB Debugging on the Device.

2. Open cmd prompt, and type `adb devices`. Verify that device name is displayed.

3. Open cmd prompt, navigate to path of the project and type `mvn compile`.

4. Set the **runOn** property to **StandAlone** and **platformType** to **Device** in *GeneralEnvProperties* file.

5. Open Driver class, right click and run as Java Application.

## To run project from commandline
 Open cmd prompt, navigate to path of the project and type `mvn exec:java`
