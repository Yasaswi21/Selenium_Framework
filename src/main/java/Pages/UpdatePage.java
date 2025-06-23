package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import Utils.UIActions;
import Validation.UpdateUserValidator;
import Utils.ObjectRepoReader;

public class UpdatePage {

	WebDriver driver;
	UIActions ui;

	// Locators
	By adminField = ObjectRepoReader.getLocator("UpdatePage", "adminField");
	By usernameField = ObjectRepoReader.getLocator("UpdatePage", "usernameField");
	By searchfield = ObjectRepoReader.getLocator("UpdatePage", "searchField");
	By editField = ObjectRepoReader.getLocator("UpdatePage", "editField");
	By checkBoxField = ObjectRepoReader.getLocator("UpdatePage", "checkBoxField");
	By passwordField = ObjectRepoReader.getLocator("UpdatePage", "passwordField");
	By confirmPasswordField = ObjectRepoReader.getLocator("UpdatePage", "confirmPasswordField");
	By saveButton = ObjectRepoReader.getLocator("UpdatePage", "saveButton");

	//Constructor
	public UpdatePage(WebDriver driver) {
		this.driver = driver;
		this.ui = new UIActions(driver);
	}
	
	public void searchUser(String username, ExtentTest test) throws InterruptedException {
		ui.sendKeys(usernameField, username,"Typing username: " + username, test);
		Thread.sleep(2000);
		ui.click(searchfield, "Searching for: " + username, test);
		Thread.sleep(2000);
	}

	public void updateUser(String new_username, String new_password, ExtentTest test) throws InterruptedException {
		ui.click(editField, "Clicking Edit to Update User", test);

		ui.clear(usernameField, "Clearing Username", test);
		ui.sendKeys(usernameField, new_username,"Updating New Username: " + new_username, test);
		
		ui.checkCheckbox(checkBoxField, "Clicking CheckBox For Password Change", test);
		
		ui.sendKeys(passwordField, new_password,"Entering New Password: " + new_password, test);
		ui.sendKeys(confirmPasswordField, new_password,"Confirming New Password: " + new_password, test);

		ui.click(saveButton, "Saving Update user details", test);
		Thread.sleep(2000);
		UpdateUserValidator.validateUpdateUser(driver, test);
	}
}
