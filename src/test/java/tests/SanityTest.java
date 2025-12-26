package tests;

import base.BaseTest;
import driver.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SanityTest extends BaseTest {

    @Test
    public void openApplicationTest() {
        String title = DriverFactory.getDriver().getTitle();
        Assert.assertNotNull(title, "Page title should not be null");
    }
}
