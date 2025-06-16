package Test;

import org.testng.annotations.Test;
import Base.Base;
import Pages.LoginPage;
import com.aventstack.extentreports.ExtentTest;

public class LoginTest extends Base {

    @Test(priority = 1)
    public void validlogin() {
        testThread.set(extent.createTest("Login to OrangeHRM"));
        ExtentTest test = getTest();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("Admin", "admin123", test);
    }
}
