package Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.ObjectRepoReader;
import Utils.ScreenshotUtils;
import Utils.UIActions;

public class AddUserValidator {

    // Locators
    private static final By addButton = ObjectRepoReader.getLocator("AdminPage", "addButton");
    private static final By INVALID_EMP_NAME = ObjectRepoReader.getLocator("Validation", "INVALID_EMP_NAME");
    private static final By DUPLICATE_USERNAME = ObjectRepoReader.getLocator("Validation", "DUPLICATE_USERNAME");
    private static final By CANCEL_BUTTON = ObjectRepoReader.getLocator("Validation", "CANCEL_BUTTON");

    public static void validateAddUser(WebDriver driver, ExtentTest test, UIActions ui) {
        SoftAssert softAssert = new SoftAssert();

        try {
            if (driver.findElements(addButton).size() > 0) {
                logPass(test, softAssert, "User added successfully");

            } else if (driver.findElements(INVALID_EMP_NAME).size() > 0) {
                handleFailure(driver, test, ui, softAssert, "Invalid Employee Name", CANCEL_BUTTON);

            } else if (driver.findElements(DUPLICATE_USERNAME).size() > 0) {
                handleFailure(driver, test, ui, softAssert, "Username already exists", CANCEL_BUTTON);

            } else {
                handleFailure(driver, test, ui, softAssert, "Unknown state - no success or error message found", null);
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Exception during user validation: " + e.getMessage());
            softAssert.fail("Exception occurred: " + e.getMessage());
        }

        softAssert.assertAll();
    }

    private static void logPass(ExtentTest test, SoftAssert softAssert, String message) {
        test.log(Status.PASS, message);
        softAssert.assertTrue(true, message);
    }

    private static void handleFailure(WebDriver driver, ExtentTest test, UIActions ui, SoftAssert softAssert,
                                      String message, By optionalClick) {
        String path = ScreenshotUtils.captureScreenshot(driver);
        test.log(Status.FAIL, message + "<br><img src='" + path + "' height='300' width='400'/>");
        softAssert.fail(message);

    }
}
