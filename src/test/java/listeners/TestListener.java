package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporting.ExtentManager;
import driver.DriverFactory;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getExtentReports();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, result.getThrowable());
        String path = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), result.getMethod().getMethodName());
        test.get().addScreenCaptureFromPath(path);
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
