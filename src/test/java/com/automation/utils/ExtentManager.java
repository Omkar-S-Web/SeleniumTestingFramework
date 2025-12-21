package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Use forward slashes for Windows paths in Java to avoid errors
            ExtentSparkReporter spark = new ExtentSparkReporter("D:/Testing report/TestReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static ExtentTest createTest(String name) {
        ExtentTest t = getInstance().createTest(name);
        test.set(t);
        return t;
    }
    // THIS IS THE CRITICAL MISSING METHOD
    public static ExtentTest getTest() {
        return test.get();
    }
}