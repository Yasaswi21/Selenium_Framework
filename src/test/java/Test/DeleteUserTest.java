package Test;



import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.Base;
import Pages.AdminPage;
import Pages.DeletePage;
import Pages.LoginPage;
import Pages.UpdatePage;
import Utils.DataProviderUtils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DeleteUserTest extends Base {

    @BeforeClass
    public void validlogin() throws InterruptedException {
        testThread.set(extent.createTest("Login to OrangeHRM - Delete User Test"));
        ExtentTest test = getTest();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("Admin", "admin123", test);
    }

    @Test(dataProvider = "deleteuserdata", dataProviderClass = DataProviderUtils.class)
    public void upUser(String username) {
        testThread.set(extent.createTest("Delete User: "+ username +" Delete User Test"));
        ExtentTest test = getTest();
        
        try {
        AdminPage adminPage = new AdminPage(getDriver());
        adminPage.navigateToAdmin(test);
        
        UpdatePage updatePage = new UpdatePage(getDriver());
        updatePage.searchUser(username, test);
        
        DeletePage deletePage = new DeletePage(getDriver());
        deletePage.del(test);
        
        }catch(Exception e) {
        	test.log(Status.FAIL, e.getMessage());
        }
    }
}