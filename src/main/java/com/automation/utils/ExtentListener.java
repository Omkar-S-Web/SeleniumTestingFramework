package com.automation.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

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

    public void onTestFailure(ITestResult result) {
    	if (driver != null) {
    	    try {
    	        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
    	        test.get().addScreenCaptureFromPath(screenshotPath);
    	    } catch (Exception e) {
    	        test.get().log(Status.INFO, "Could not attach screenshot: " + e.getMessage());
    	    }
    	}
//        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        // Link the screenshot from your D: drive into the report
        // Note: You may need to pass the driver to ScreenshotUtils here if capturing within the listener
    }

    public void onFinish(ITestContext context) {
        extent.flush(); // This generates the final report file
    }
}