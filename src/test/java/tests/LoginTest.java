package tests;

import base.BaseTest;
import config.ConfigReader;
import driver.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));
        Assert.assertTrue(loginPage.isLoginSuccessful(),"Login failed: Dashboard welcome message not displayed");
    }
}