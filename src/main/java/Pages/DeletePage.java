package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import Utils.UIActions;
import Validation.DeleteUserValidator;
import Utils.ObjectRepoReader;

public class DeletePage {

    WebDriver driver;
    UIActions ui;

    // Locators
    By deleteField = ObjectRepoReader.getLocator("DeletePage", "deleteField");
    By confirmation = ObjectRepoReader.getLocator("DeletePage", "confirmation");

    // Constructor
    public DeletePage(WebDriver driver) {
        this.driver = driver;
        this.ui = new UIActions(driver);
    }

    // Actions
    public boolean del(ExtentTest test) {
        try {
            ui.click(deleteField, "Clicking delete", test);
            ui.click(confirmation, "Confirming To Delete User", test);
            

            boolean isDeleted = DeleteUserValidator.validateDeleteUser(driver, test);
            return isDeleted;

        } catch (Exception e) {
            test.fail("Delete User process failed due to: " + e.getMessage());
            return false;
        }
    }
}
