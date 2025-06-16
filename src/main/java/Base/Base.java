package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

    // Thread-safe WebDriver and ExtentTest
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    protected static ExtentReports extent;

    public WebDriver getDriver() {
        return driver.get();
    }

    public ExtentTest getTest() {
        return testThread.get();
    }

    @BeforeSuite
    public void setUpExtent() {
        extent = ExtentReportManager.getInstance();
    }

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {
        WebDriver localDriver = null;

        try {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                localDriver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                localDriver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                localDriver = new EdgeDriver();
            }
        } catch (Exception e) {
            System.out.println("Browser setup failed: " + e.getMessage());
        }

        localDriver.manage().window().maximize();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.set(localDriver);
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    @AfterSuite
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
