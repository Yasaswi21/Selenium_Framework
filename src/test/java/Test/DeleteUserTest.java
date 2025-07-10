package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.Base;
import Pages.AdminPage;
import Pages.DeletePage;
import Pages.LoginPage;
import Pages.UpdatePage;
import Utils.DataProviderUtils;
import Utils.UserSummaryTracker;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DeleteUserTest extends Base {

    @BeforeClass
    public void validlogin() {
        testThread.set(extent.createTest("Login to OrangeHRM - DeleteUserTest"));
        ExtentTest test = getTest();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("Admin", "admin123", test);
    }

    @Test(dataProvider = "deleteuserdata", dataProviderClass = DataProviderUtils.class)
    public void deleteUser(String username) {
        testThread.set(extent.createTest("Delete User: " + username));
        ExtentTest test = getTest();

        try {
            AdminPage adminPage = new AdminPage(getDriver());
            adminPage.navigateToAdmin(test);

            UpdatePage updatePage = new UpdatePage(getDriver());
            updatePage.searchUser(username, test);

            DeletePage deletePage = new DeletePage(getDriver());
            boolean isDeleted = deletePage.del(test);

            if (isDeleted) {
                test.log(Status.PASS, "User " + username + " deleted successfully");
            } else {
                test.log(Status.FAIL, "Failed to delete user: " + username);
            }

            UserSummaryTracker.deleteUser(username, isDeleted);

        } catch (Exception e) {
            test.log(Status.FAIL, "Delete User Test failed due to: " + e.getMessage());
            UserSummaryTracker.deleteUser(username, false);
        }
    }
}
