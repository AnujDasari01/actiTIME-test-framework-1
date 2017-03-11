package com.actitime.apppageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.genericlibrary.FileUtility;
import com.actitime.genericlibrary.Helper;
import com.actitime.genericlibrary.Report;

public class CreateNewFormPO {
	WebDriver driver;

	@FindBy(id = "edt_firstname")
	private WebElement firstNameTextBox;

	@FindBy(id = "edt_lastname")
	private WebElement lastNameTextBox;

	@FindBy(id = "edt_email")
	private WebElement emailTextBox;

	@FindBy(id = "edt_phone")
	private WebElement phoneNumberTextBox;

	@FindBy(id = "radio_gender_male")
	private WebElement maleGenderRadioBtn;

	@FindBy(id = "radio_gender_female")
	private WebElement femaleGenderRadioBtn;

	@FindBy(id = "edt_street")
	private WebElement streetTextBox;

	@FindBy(id = "spinner_countries_main")
	private WebElement countryDropDown;

	@FindBy(id = "text1")
	private List<WebElement> countryList;

	@FindBy(id = "text1")
	private List<WebElement> stateList;

	@FindBy(id = "spinner_states_main")
	private WebElement stateDropDown;

	@FindBy(id = "chkbox_tv")
	private WebElement watchTvCheckBox;

	@FindBy(id = "chkbox_games")
	private WebElement playVideoGamesCheckBox;

	@FindBy(id = "chkbox_books")
	private WebElement readBooksCheckBox;

	@FindBy(id = "chkbox_stamps")
	private WebElement collectStampsCheckBox;

	@FindBy(id = "chkbox_termsandconditions")
	private WebElement termsAndConditionsCheckBox;

	@FindBy(id = "btn_save")
	private WebElement saveFormBtn;

	public CreateNewFormPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This methods creates a new form
	 **/
	public void createNewForm() {
		Helper.explicitWait(firstNameTextBox, driver);
		firstNameTextBox.sendKeys(FileUtility.getTestData().get("First_Name"));
		lastNameTextBox.sendKeys(FileUtility.getTestData().get("Last_Name"));
		emailTextBox.sendKeys(FileUtility.getTestData().get("Email_Address"));
		phoneNumberTextBox.sendKeys(FileUtility.getTestData().get("Phone"));
		Helper.scrollDown(driver);
		streetTextBox.sendKeys(FileUtility.getTestData().get("Street"));
		countryDropDown.click();
		int countriesCount = 0;
		while (countriesCount < 11) {
			for (int i = 0; i < countryList.size(); i++) {
				String country = countryList.get(i).getText();
				if (country.equalsIgnoreCase(FileUtility.getTestData().get(
						"Country"))) {
					countriesCount = 12;
					countryList.get(i).click();
					break;
				} else if (!(country.equalsIgnoreCase(FileUtility.getTestData()
						.get("Country")))) {
					countriesCount++;
					if (countriesCount == 11) {
						countriesCount = 0;
						Helper.scrollDown(driver);
						continue;
					}
				}
			}
		}

		stateDropDown.click();
		int statesCount = 0;
		while (statesCount < 11) {
			for (int i = 0; i < stateList.size(); i++) {
				String state = stateList.get(i).getText();
				if (state.equalsIgnoreCase(FileUtility.getTestData().get(
						"State"))) {
					statesCount = 12;
					stateList.get(i).click();
					break;
				} else if (!(state.equalsIgnoreCase(FileUtility.getTestData()
						.get("State")))) {
					statesCount++;
					if (statesCount == 11) {
						statesCount = 0;
						Helper.scrollDown(driver);
						continue;
					}
				}
			}
		}

		Helper.scrollDown(driver);
		String hobbies = FileUtility.getTestData().get("Hobbies");
		hobbies.replaceAll("\\s+","");
		List<String> hobbiesList = Arrays.asList(hobbies.split(","));
		for(int i = 0; i<hobbiesList.size();i++) {
			String s = hobbiesList.get(i);
			System.out.println(s);
			if(hobbiesList.get(i).equalsIgnoreCase("watch tv")) {
				watchTvCheckBox.click();
			}
			else if(hobbiesList.get(i).equalsIgnoreCase(" read books")) {
				readBooksCheckBox.click();
			}
			else if(hobbiesList.get(i).equalsIgnoreCase("video games")) {
				playVideoGamesCheckBox.click();
			}
			else if(hobbiesList.get(i).equalsIgnoreCase("collect stamps")) {
				collectStampsCheckBox.click();
			}
			
		}
		termsAndConditionsCheckBox.click();
		saveFormBtn.click();
		Report.captureScreenshot(driver, "CreateNewForm ");
	}
}
