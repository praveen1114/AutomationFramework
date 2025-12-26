package base;

import config.ConfigReader;
import driver.DriverFactory;
import logging.Log;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    protected Logger logger = Log.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() {
        logger.info("Initializing test setup");

        String browser = ConfigReader.getProperty("browser");
        logger.info("Browser selected: {}", browser);

        DriverFactory.initDriver(browser);

        String url = ConfigReader.getProperty("url");
        logger.info("Navigating to URL: {}", url);

        DriverFactory.getDriver().get(url);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Closing browser");
        DriverFactory.quitDriver();
    }
}
