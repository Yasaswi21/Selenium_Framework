package Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Utils.ObjectRepoReader;
import Utils.ScreenshotUtils;
import Utils.UIActions;

import java.time.Duration;

public class AddUserValidator {

    // Locators
    private static final By ADDSUCCESS = ObjectRepoReader.getLocator("Validation", "ADDSUCCESS");
    private static final By INVALID_EMP_NAME = ObjectRepoReader.getLocator("Validation", "INVALID_EMP_NAME");
    private static final By DUPLICATE_USERNAME = ObjectRepoReader.getLocator("Validation", "DUPLICATE_USERNAME");
    private static final By CANCEL_BUTTON = ObjectRepoReader.getLocator("Validation", "CANCEL_BUTTON");

    private static final int WAIT_TIME = 10;  // Wait time in seconds

    public static boolean validateAddUser(WebDriver driver, ExtentTest test, UIActions ui) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));

        try {
            if (isElementPresent(driver, ADDSUCCESS, wait)) {
                test.log(Status.PASS, "User added successfully.");
                return true;

            } else if (isElementPresent(driver, INVALID_EMP_NAME, wait)) {
                handleFailure(driver, test, ui, "Invalid Employee Name", CANCEL_BUTTON, wait);
                return false;

            } else if (isElementPresent(driver, DUPLICATE_USERNAME, wait)) {
                handleFailure(driver, test, ui, "Username already exists", CANCEL_BUTTON, wait);
                return false;

            } else {
                handleFailure(driver, test, ui, "Unknown state - no success or error message found", null, wait);
                return false;
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Exception during user validation: " + e.getMessage());
            return false;
        }
    }

    private static boolean isElementPresent(WebDriver driver, By locator, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static void handleFailure(WebDriver driver, ExtentTest test, UIActions ui, String message, By optionalClick, WebDriverWait wait) {
        String path = ScreenshotUtils.captureScreenshot(driver);
        test.log(Status.FAIL, message + "<br><img src='" + path + "' height='300' width='400'/>");

        try {
            if (optionalClick != null) {
                wait.until(ExpectedConditions.elementToBeClickable(optionalClick));
                ui.click(optionalClick, "Clicking cancel or closing error", test);
            }
        } catch (Exception e) {
            test.log(Status.WARNING, "Failed to click cancel or close error: " + e.getMessage());
        }
    }
}
