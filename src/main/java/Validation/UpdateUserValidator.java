package Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.ObjectRepoReader;
import Utils.ScreenshotUtils;

public class UpdateUserValidator {

	private static final By SUCCESS_MESSAGE = ObjectRepoReader.getLocator("Validation", "UPDATESUCCESS");

    public static void validateUpdateUser(WebDriver driver, ExtentTest test) {
        SoftAssert softAssert = new SoftAssert();

        try {
            if (driver.findElements(SUCCESS_MESSAGE).size() > 0) {
            	 test.log(Status.PASS,"User Successfully Updated");
                softAssert.assertTrue(true, "Update success message verified.");
            } else {
                String path = ScreenshotUtils.captureScreenshot(driver);
                test.log(Status.FAIL, "Update success message NOT displayed.<br><img src='" + path + "' height='300' width='400'/>");
                softAssert.fail("Update success message not found.");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Exception during update validation: " + e.getMessage());
            softAssert.fail("Exception: " + e.getMessage());
        }

        softAssert.assertAll();
    }
}
