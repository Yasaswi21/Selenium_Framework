package Validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Utils.ObjectRepoReader;
import Utils.ScreenshotUtils;

import java.time.Duration;

public class DeleteUserValidator {

    private static final By SUCCESS_MESSAGE = ObjectRepoReader.getLocator("Validation", "DELETESUCCESS");
    private static final int WAIT_TIME = 10;  // Wait time in seconds

    public static boolean validateDeleteUser(WebDriver driver, ExtentTest test) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));

        try {
            if (isElementPresent(driver, SUCCESS_MESSAGE, wait)) {
                test.log(Status.PASS, "User successfully deleted.");
                return true;
            } else {
                String path = ScreenshotUtils.captureScreenshot(driver);
                test.log(Status.FAIL, "Delete success message NOT displayed.<br><img src='" + path + "' height='300' width='400'/>");
                return false;
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Exception during Delete validation: " + e.getMessage());
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
}
