package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Locators
    private final By openLoginPopupButton = By.xpath("//button[@class='Login-btn'][text()='Login']");
    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By SignInButton = By.xpath("//button[contains(@class, 'login-btn')]");
    private final By welcomeMeaasge = By.xpath("//h6[contains(text(), 'Welcome')]");
    private final By errorMessage = By.id("login-error");

    /* ===================== PAGE ACTIONS ===================== */

    public LoginPage enterUsername(String username) {
        logger.info("Entering username");
        type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        logger.info("Entering password");
        type(passwordInput, password);
        return this;
    }


    public void clickLogin() {
        logger.info("Clicking Login button");
        click(openLoginPopupButton);
    }

    public void clickSignInButton() {
        logger.info("Clicking Sign Into Dashboard button");
        click(SignInButton);
    }
    public LoginPage waitForPageToBeReady() {
        waitForVisibility(openLoginPopupButton);
        return this;
    }

    public boolean isLoginSuccessful() {
        logger.info("Verifying successful login by checking Welcome message");
        return isDisplayed(welcomeMeaasge);
    }

    public void login(String username, String password)  {
        logger.info("Performing login");
        clickLogin();
        enterUsername(username);
        enterPassword(password);
        clickSignInButton();

    }


    /* ===================== PAGE VERIFICATIONS ===================== */

    public boolean isErrorDisplayed() {
        logger.info("Checking login error message visibility");
        return isDisplayed(errorMessage);
    }
}
