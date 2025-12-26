package base;

import driver.DriverFactory;
import logging.Log;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    private static final int DEFAULT_TIMEOUT = 10;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.logger = Log.getLogger(this.getClass());
    }

    /* =========================
       Common Wait Methods
       ========================= */

    protected WebElement waitForVisibility(By locator) {
        logger.debug("Waiting for visibility of element: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /* =========================
       Common Action Methods
       ========================= */

    protected void click(By locator) {
        logger.info("Clicking on element: {}", locator);
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        logger.info("Typing '{}' into element: {}", text, locator);
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        logger.info("Getting text from element: {}", locator);
        return waitForVisibility(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            boolean displayed = waitForVisibility(locator).isDisplayed();
            logger.debug("Element displayed status {}: {}", locator, displayed);
            return displayed;
        } catch (Exception e) {
            logger.warn("Element not displayed: {}", locator);
            return false;
        }
    }

    /* =========================
       Page Utilities
       ========================= */

    protected String getPageTitle() {
        logger.info("Fetching page title");
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        logger.info("Fetching current URL");
        return driver.getCurrentUrl();
    }
}
