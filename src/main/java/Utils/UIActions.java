package Utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class UIActions {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public UIActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void click(By locator, String description, ExtentTest test) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            test.log(Status.PASS, description);
        } catch (Exception e) {
            new ScreenshotUtils();
			String path = ScreenshotUtils.captureScreenshot(driver);
            test.log(Status.FAIL, description + " - FAILED: " + e.getMessage()
                + "<br><img src='" + path + "' height='300' width='400'/>");
        }
    }

    public void clear(By locator, String description, ExtentTest test) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click(); // focus
            element.sendKeys(Keys.CONTROL + "a", Keys.DELETE); // more reliable than clear()
            test.log(Status.PASS, description + " - Cleared successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, description + " - FAILED to clear: " + e.getMessage());
            captureFailureScreenshot(test);
        }
    }



    public void sendKeys(By locator, String text, String description, ExtentTest test) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
            test.log(Status.PASS, description);
        } catch (Exception e) {
        	new ScreenshotUtils();
			String path = ScreenshotUtils.captureScreenshot(driver);
            test.log(Status.FAIL, description + " - FAILED: " + e.getMessage()
                + "<br><img src='" + path + "' height='300' width='400'/>");
        }
    }

    public void selectByVisibleText(By locator, String text, String description, ExtentTest test) {
        try {
            Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
            select.selectByVisibleText(text);
            test.log(Status.PASS, description + ": " + text);
        } catch (Exception e) {
            test.log(Status.FAIL, description + " - FAILED: " + e.getMessage());
            captureFailureScreenshot(test);
        }
    }

    public void selectByIndex(By locator, int index, String description, ExtentTest test) {
        try {
            Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
            select.selectByIndex(index);
            test.log(Status.PASS, description + ": Index " + index);
        } catch (Exception e) {
            test.log(Status.FAIL, description + " - FAILED: " + e.getMessage());
            captureFailureScreenshot(test);
        }
    }

    public void selectByValue(By locator, String value, String description, ExtentTest test) {
        try {
            Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
            select.selectByValue(value);
            test.log(Status.PASS, description + ": Value " + value);
        } catch (Exception e) {
            test.log(Status.FAIL, description + " - FAILED: " + e.getMessage());
            captureFailureScreenshot(test);
        }
    }

    public void checkCheckbox(By locator, String description, ExtentTest test) {
        try {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            test.log(Status.PASS, description);
        } catch (Exception e) {
            test.log(Status.FAIL, description + " - FAILED: " + e.getMessage());
            captureFailureScreenshot(test);
        }
    }

    public void uncheckCheckbox(By locator, String description, ExtentTest test) {
        try {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
            if (checkbox.isSelected()) {
                checkbox.click();
            }
            test.log(Status.PASS, description);
        } catch (Exception e) {
            test.log(Status.FAIL, description + " - FAILED: " + e.getMessage());
            captureFailureScreenshot(test);
        }
    }

    public void radioButton(By locator, String value, String description, ExtentTest test) {
        try {
            List<WebElement> radioButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            for (WebElement rb : radioButtons) {
                if (rb.getAttribute("value").equalsIgnoreCase(value)) {
                    if (!rb.isSelected()) {
                        rb.click();
                    }
                    break;
                }
            }
            test.log(Status.PASS, description + ": Selected " + value);
        } catch (Exception e) {
            test.log(Status.FAIL, description + " - FAILED: " + e.getMessage());
            captureFailureScreenshot(test);
        }
    }

    public void hover(By locator, String description, ExtentTest test) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            actions.moveToElement(element).perform();
            test.log(Status.PASS, description);
        } catch (Exception e) {
            test.log(Status.FAIL, description + " - FAILED: " + e.getMessage());
            captureFailureScreenshot(test);
        }
    }

    private void captureFailureScreenshot(ExtentTest test) {
        try {
            String path = ScreenshotUtils.captureScreenshot(driver);
            test.addScreenCaptureFromPath(path);
        } catch (Exception ex) {
            test.log(Status.WARNING, "Screenshot capture failed: " + ex.getMessage());
        }
    }
}
