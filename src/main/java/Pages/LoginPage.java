package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;


import Utils.UIActions;
import Utils.ObjectRepoReader;
import Validation.LoginValidator;

public class LoginPage {

    WebDriver driver;
    UIActions ui;

    By UsernameField = ObjectRepoReader.getLocator("LoginPage", "UsernameField");
    By PasswordField = ObjectRepoReader.getLocator("LoginPage", "PasswordField");
    By SubmitField = ObjectRepoReader.getLocator("LoginPage", "SubmitField");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.ui = new UIActions(driver);
    }

    public void login(String user, String pass, ExtentTest test) {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        ui.sendKeys(UsernameField, user, "Entering username: " + user, test);
        ui.sendKeys(PasswordField, pass, "Entering password", test);
        ui.click(SubmitField, "Clicking Login button", test);
        LoginValidator.validateLoginSuccess(driver, test, ui);
    }
}
