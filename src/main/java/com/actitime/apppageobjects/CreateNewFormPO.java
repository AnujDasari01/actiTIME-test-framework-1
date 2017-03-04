package com.actitime.apppageobjects;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	private List<WebElement> countrySelected;
	
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
		firstNameTextBox.sendKeys("Anuj");
		lastNameTextBox.sendKeys("Dasari");
		emailTextBox.sendKeys("anuj.dsr@gmail.com");
		phoneNumberTextBox.sendKeys("9900135521");
		Helper.normalWait(driver, 5);
		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		int scrollStart = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.1;
		int scrollEnd = screenHeightEnd.intValue();
		((AppiumDriver<?>) driver).swipe(0,scrollStart,0,scrollEnd,2000);
		streetTextBox.sendKeys("Ramaiah Reddy Colony, Sector-C");
		((AppiumDriver<?>) driver).swipe(0,scrollStart,0,scrollEnd,2000);
		watchTvCheckBox.click();
		playVideoGamesCheckBox.click();
		collectStampsCheckBox.click();
		readBooksCheckBox.click();
		termsAndConditionsCheckBox.click();
		saveFormBtn.click();
		Report.captureScreenshot(driver, "FormDetails ");
	}
}
