package utils;

import driver.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static String captureScreenshotBase64(WebDriver driver) {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BASE64);
    }
    public static String captureScreenshot(String testName) {


        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            return null;
        }

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String screenshotDir =
                System.getProperty("user.dir") + "/target/extent-reports/screenshots";

        // Ensure directory exists
        new File(screenshotDir).mkdirs();

        String screenshotPath =
                screenshotDir + testName + "_" + timestamp + ".png";

        try {
            File src =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            FileUtils.copyFile(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return screenshotPath;
    }
}
