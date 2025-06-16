package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import Utils.UIActions;
import Utils.ObjectRepoReader;
import Validation.AddUserValidator;

public class AdminPage {

    WebDriver driver;
    UIActions ui;

    // Locators from ObjectRepository.properties
    By adminField = ObjectRepoReader.getLocator("AdminPage", "adminField");
    By addButton = ObjectRepoReader.getLocator("AdminPage", "addButton");
    By userRoleDropdown = ObjectRepoReader.getLocator("AdminPage", "userRoleDropdown");
    By userRoleSelect = ObjectRepoReader.getLocator("AdminPage", "userRoleSelect");
    By statusDropdown = ObjectRepoReader.getLocator("AdminPage", "statusDropdown");
    By statusSelect = ObjectRepoReader.getLocator("AdminPage", "statusSelect");
    By empNameField = ObjectRepoReader.getLocator("AdminPage", "empNameField");
    By usernameField = ObjectRepoReader.getLocator("AdminPage", "usernameField");
    By passwordField = ObjectRepoReader.getLocator("AdminPage", "passwordField");
    By confirmPasswordField = ObjectRepoReader.getLocator("AdminPage", "confirmPasswordField");
    By saveButton = ObjectRepoReader.getLocator("AdminPage", "saveButton");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.ui = new UIActions(driver);
    }

    public void navigateToAdmin(ExtentTest test) {
        ui.click(adminField, "Clicking on Admin tab", test);
    }

    public void navigateToAdd(ExtentTest test) {
        ui.click(addButton, "Clicking on Add button", test);
    }

    public void addUser(String username, String empName, String password, ExtentTest test) {
        ui.click(userRoleDropdown, "Clicking User Role dropdown", test);
        ui.click(userRoleSelect, "Selecting 'Admin' from User Role dropdown", test);

        ui.click(statusDropdown, "Clicking Status dropdown", test);
        ui.click(statusSelect, "Selecting 'Enabled' from Status dropdown", test);

        ui.sendKeys(empNameField, empName, "Typing Employee Name: " + empName, test);

        By empNameSuggestion = By.xpath("//div[@role='option']/span[contains(text(),'" + empName + "')]");
        ui.click(empNameSuggestion, "Selecting employee name from suggestions", test);

        ui.sendKeys(usernameField, username, "Typing username: " + username, test);
        ui.sendKeys(passwordField, password, "Typing password; " + password, test);
        ui.sendKeys(confirmPasswordField, password, "Confirming password: " + password, test);

        ui.click(saveButton, "Clicking Save button", test);

        AddUserValidator.validateAddUser(driver, test, ui);
    }
}
