package Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.ObjectRepoReader;
import Utils.ScreenshotUtils;

public class DeleteUserValidator {

	private static final By SUCCESS_MESSAGE = ObjectRepoReader.getLocator("Validation", "DELETESUCCESS");

    public static void validateDeleteUser(WebDriver driver, ExtentTest test) {
        SoftAssert softAssert = new SoftAssert();

        try {
            if (driver.findElements(SUCCESS_MESSAGE).size() > 0) {
            	 test.log(Status.PASS,"User Successfully Deleted");
                softAssert.assertTrue(true, "Delete success message verified.");
            } else {
                String path = ScreenshotUtils.captureScreenshot(driver);
                test.log(Status.FAIL, "Delete success message NOT displayed.<br><img src='" + path + "' height='300' width='400'/>");
                softAssert.fail("Delete success message not found.");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Exception during Delete validation: " + e.getMessage());
            softAssert.fail("Exception: " + e.getMessage());
        }

        softAssert.assertAll();
    }
}
