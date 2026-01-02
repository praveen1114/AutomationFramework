package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10;

    private WaitUtils() {}

    private static WebDriverWait getWait(WebDriver driver, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    /* =====================
       Page Load
       ===================== */

    public static void waitForPageLoad(WebDriver driver) {
        getWait(driver, 20).until(d ->
                ((JavascriptExecutor) d)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }

    /* =====================
       Explicit Waits
       ===================== */

    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return getWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return getWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForInvisibility(WebDriver driver, By locator) {
        return getWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitForTitleContains(WebDriver driver, String title) {
        return getWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.titleContains(title));
    }

    public static void waitForUrlContains(WebDriver driver, String urlFraction) {
        getWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.urlContains(urlFraction));
    }

    /* =====================
       Custom Timeout
       ===================== */

    public static WebElement waitForVisibility(
            WebDriver driver, By locator, int timeout) {
        return getWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
