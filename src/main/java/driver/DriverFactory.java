package driver;

import logging.Log;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = Log.getLogger(DriverFactory.class);

    private DriverFactory() {}

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String browser) {
        if (browser == null || browser.isEmpty()) {
            throw new RuntimeException("Browser value is NULL or EMPTY. Check config.properties");
        }
        WebDriver webDriver = null;
        switch(browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                webDriver = new ChromeDriver(options);
                logger.info("Launching Chrome browser");
                break;

            case "firefox":
                webDriver = new FirefoxDriver();
                logger.info("Launching Firefox browser");
                break;
            default:
                logger.error("Invalid browser provided: {}", browser);
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
