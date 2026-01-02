package base;

import config.ConfigReader;
import driver.DriverFactory;
import logging.Log;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver(ConfigReader.getProperty("browser"));
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}


