package Test;



import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.Base;
import Pages.AdminPage;
import Pages.LoginPage;
import Pages.UpdatePage;
import Utils.DataProviderUtils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class UpdateUserTest extends Base {

    @BeforeClass
    public void validlogin() throws InterruptedException {
        testThread.set(extent.createTest("Login to OrangeHRM - UpdateUserTest"));
        ExtentTest test = getTest();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("Admin", "admin123", test);
    }

    @Test(dataProvider = "updateuserdata", dataProviderClass = DataProviderUtils.class)
    public void upUser(String username , String new_username, String new_password) {
        testThread.set(extent.createTest("Update User: "+ username +" Update User Test"));
        ExtentTest test = getTest();
        try {
        AdminPage adminPage = new AdminPage(getDriver());
        adminPage.navigateToAdmin(test);
        
        UpdatePage updatePage = new UpdatePage(getDriver());
        updatePage.searchUser(username, test);
        updatePage.updateUser(new_username, new_password, test);
        }catch(Exception e) {
        	test.log(Status.FAIL, e.getMessage());
        }
    }
}