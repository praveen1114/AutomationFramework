package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import driver.DriverFactory;
import org.testng.*;
import reporting.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener, IInvokedMethodListener {

    private static final ExtentReports extent = ExtentManager.getExtentReports();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getTestClass().getName() + " :: " +
                        result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        String base64 = ScreenshotUtils.captureScreenshotBase64(
                DriverFactory.getDriver()
        );

        test.get().addScreenCaptureFromBase64String(
                base64,
                result.getMethod().getMethodName()
        );
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {

        if (method.isConfigurationMethod() &&
                result.getStatus() == ITestResult.FAILURE) {

            ExtentTest extentTest = test.get();
            if (extentTest != null) {
                extentTest.fail("Configuration method failed: " + method.getTestMethod().getMethodName());
                extentTest.fail(result.getThrowable());
            }
        }
    }

        @Override
        public void onFinish (ITestContext context){
            extent.flush();
        }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP,
                "Test Skipped due to configuration failure or dependency issue");
    }

    }

