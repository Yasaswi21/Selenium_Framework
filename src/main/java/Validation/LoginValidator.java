package Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.ObjectRepoReader;
import Utils.ScreenshotUtils;
import Utils.UIActions;

public class LoginValidator {

    private static final By ADMIN_DASHBOARD = ObjectRepoReader.getLocator("AdminPage", "adminField");
    private static final By INVALID_CREDENTIALS = ObjectRepoReader.getLocator("Validation", "INVALID_CREDENTIALS");

    public static void validateLoginSuccess(WebDriver driver, ExtentTest test, UIActions ui) {
        SoftAssert softAssert = new SoftAssert();

        try {
            if (driver.findElements(ADMIN_DASHBOARD).size() > 0) {
                test.log(Status.PASS, "Login successful - Admin dashboard is visible");
                softAssert.assertTrue(true, "Admin dashboard loaded");

            } else if (driver.findElements(INVALID_CREDENTIALS).size() > 0) {
                captureAndLogFailure(driver, test, softAssert, "Login failed - Invalid credentials");

            } else {
                captureAndLogFailure(driver, test, softAssert, "Login failed - No expected outcome matched");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Exception during login validation: " + e.getMessage());
            softAssert.fail("Validation exception: " + e.getMessage());
        }

        softAssert.assertAll();
    }

    private static void captureAndLogFailure(WebDriver driver, ExtentTest test, SoftAssert softAssert, String message) {
        String path = ScreenshotUtils.captureScreenshot(driver);
        test.log(Status.FAIL, message + "<br><img src='" + path + "' height='300' width='400'/>");
        softAssert.fail(message);
    }
}
