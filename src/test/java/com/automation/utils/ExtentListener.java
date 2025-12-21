package com.automation.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.test.BaseTest;
import com.aventstack.extentreports.Status;

public class ExtentListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.createTest(result.getMethod().getMethodName());
    }

   @Override
public void onTestFailure(ITestResult result) {
    // Log the exception text
    ExtentManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());

    // Link the actual image file to the report
    String testName = result.getName();
    // Path must match exactly where ScreenshotUtils saves it
    String screenshotPath = "D:/Testing failures SS/" + testName + ".png"; 
    
    ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
}
    @Override
    public void onFinish(ITestContext context) {
        if (ExtentManager.getInstance() != null) {
            ExtentManager.getInstance().flush();
        }
    }
}