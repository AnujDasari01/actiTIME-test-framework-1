package com.actitime.apppageobjects;

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

	@FindBy(id = "")
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
		int count = 0;
		while (count < 11) {
			for (int i = 0; i < countryList.size(); i++) {
				String country = countryList.get(i).getText();
				System.out.println(countryList.size());
				System.out.println("Country is : " + country);
				System.out.println(country.equalsIgnoreCase(FileUtility
						.getTestData().get("Country")));
				if (country.equalsIgnoreCase(FileUtility.getTestData().get(
						"Country"))) {
					Helper.normalWait(driver, 2);
					countryList.get(i).click();
				} else if (!(country.equalsIgnoreCase(FileUtility.getTestData()
						.get("Country"))) && count < 11) {
					// Helper.scrollDown(driver);
					count++;
					continue;
				}

				else if (count == 10) {
					count = 0;
					Helper.scrollDown(driver);
					Helper.normalWait(driver, 5);
					continue;
				}
			}
		}
		Helper.scrollDown(driver);
		termsAndConditionsCheckBox.click();
		saveFormBtn.click();
		Report.captureScreenshot(driver, "FormDetails ");
	}
}
