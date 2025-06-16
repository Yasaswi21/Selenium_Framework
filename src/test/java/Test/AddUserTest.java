package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.Base;
import Pages.LoginPage;
import Pages.AdminPage;
import Utils.DataProviderUtils;
import com.aventstack.extentreports.ExtentTest;

public class AddUserTest extends Base {

    @BeforeClass
    public void validlogin() {
        testThread.set(extent.createTest("Login to OrangeHRM - AddUserTest"));
        ExtentTest test = getTest();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("Admin", "admin123", test);
    }

    @Test(dataProvider = "userdata", dataProviderClass = DataProviderUtils.class)
    public void addUser(String username, String empName, String password) {
        testThread.set(extent.createTest("Add User: " + username + " - AddUserTest"));
        ExtentTest test = getTest();

        AdminPage adminPage = new AdminPage(getDriver());
        adminPage.navigateToAdmin(test);
        adminPage.navigateToAdd(test);
        adminPage.addUser(username, empName, password, test);
    }
}
