package com.automation.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.test.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

   @Override
public void onTestFailure(ITestResult result) {
    test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

    try {
        // 1. Get the instance of the test class that failed
        Object currentClass = result.getInstance();
        
        // 2. Access the driver from the BaseTest (make sure getDriver() exists in BaseTest)
        WebDriver driver = ((BaseTest) currentClass).getDriver();

        if (driver != null) {
            // 3. Take screenshot and add to report
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    } catch (Exception e) {
        test.get().log(Status.INFO, "Could not attach screenshot: " + e.getMessage());
    }
}
    public void onFinish(ITestContext context) {
        extent.flush(); // This generates the final report file
    }
}