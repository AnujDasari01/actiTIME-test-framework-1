# ActiTimeProject
A repository to run Functional and UI Test Scripts in Desktop and Android Devices using Java, Selenium, TestNG and Appium.

## Requirements
* **Windows OS**  
* **Eclipse Luna IDE** from [here](http://www.eclipse.org/downloads/packages/eclipse-standard-44/lunar)  
* **JDK 8** from [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)  
* **Maven** from [here](https://maven.apache.org/download.cgi)  
* **Appium** from [here](https://appium.io/)  
* **PDAnet** for Desktop from [here](http://pdanet.co/a/) and PDAnet in Device from Google Play Store.
* **Android SDK** from [here](https://developer.android.com/studio/index.html) 
* **actiTIME Application** from [here](https://www.actitime.com/download.php) 

## Let's get started
**Download the project from:**  
https://github.com/AnujDasari/ActiTimeProject

## Pre-requisites
1. Set JAVA_HOME, MAVEN_HOME and ANDROID_HOME Environment Variables.
![set_env_variables](https://cloud.githubusercontent.com/assets/18676770/23308633/c91470b6-fad1-11e6-870e-797686ca1075.png)  

2. Add the above set variables to PATH System Variable.
![set_path_variable](https://cloud.githubusercontent.com/assets/18676770/23308705/0fe0ae1a-fad2-11e6-9409-d5e1de8f3e4d.png)

3. Import the downloaded project as an existing maven project.
![import_project_into_eclipse](https://cloud.githubusercontent.com/assets/18676770/23309097/668334ee-fad3-11e6-85db-b5fcf3273691.png)

## To run project in Desktop
1. Open cmd prompt, navigate to path of the project and type `mvn compile`.
![compile_project](https://cloud.githubusercontent.com/assets/18676770/23309453/ba0c2bec-fad4-11e6-97f7-c2131af7ce99.png)
2. Set the **runOn** property to **StandAlone** and **platformType** property to **Desktop** in *GeneralEnvProperties* file and set the desired **browser** in *StandAloneEnvProperties* file.
![set_desktop_general_prop](https://cloud.githubusercontent.com/assets/18676770/23309574/30c52568-fad5-11e6-81f6-3ffb163eccd0.png)
![set_desktop_standalone_prop](https://cloud.githubusercontent.com/assets/18676770/23309596/5b6688f2-fad5-11e6-9940-151f8fbbc840.png)
3. Open Driver class, right click and run as Java Application.
![run_driver_class](https://cloud.githubusercontent.com/assets/18676770/23309671/9f943f2e-fad5-11e6-8c32-0238e5f133af.png)

## To run project in Device
1. Open cmd prompt, navigate to path of the project and type `mvn compile`.
2. Set the platformType to Device in *GeneralEnvProperties* file.
3. Open Driver class, right click and run as Java Application.

## To run project from commandline
`mvn exec:java`
