package utils;

import config.ConfigReader;
import pages.LoginPage;

public class LoginHelper {

    private LoginHelper() {}

    public static void loginWithValidUser() {

        LoginPage loginPage = new LoginPage();

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        if (!loginPage.isLoginSuccessful()) {
            throw new RuntimeException("Login failed. Cannot proceed with tests.");
        }
    }
}



