package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.Base;
import Pages.AdminPage;
import Pages.LoginPage;
import Pages.UpdatePage;
import Utils.DataProviderUtils;
import Utils.UserSummaryTracker;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class UpdateUserTest extends Base {

    @BeforeClass
    public void validlogin() {
        testThread.set(extent.createTest("Login to OrangeHRM - UpdateUserTest"));
        ExtentTest test = getTest();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("Admin", "admin123", test);
    }

    @Test(dataProvider = "updateuserdata", dataProviderClass = DataProviderUtils.class)
    public void upUser(String username, String new_username, String new_password) {
        testThread.set(extent.createTest("Update User: " + username));
        ExtentTest test = getTest();

        try {
            AdminPage adminPage = new AdminPage(getDriver());
            adminPage.navigateToAdmin(test);

            UpdatePage updatePage = new UpdatePage(getDriver());
            updatePage.searchUser(username, test);
            boolean isUpdated = updatePage.updateUser(new_username, new_password, test);

            if (isUpdated) {
                test.log(Status.PASS, "User " + username + " updated successfully to " + new_username);
            } else {
                test.log(Status.FAIL, "Failed to update user: " + username);
            }

            UserSummaryTracker.updateUser(username, new_username, isUpdated);

        } catch (Exception e) {
            test.log(Status.FAIL, "Update User Test failed due to: " + e.getMessage());
            UserSummaryTracker.updateUser(username, new_username, false);
        }
    }
}
