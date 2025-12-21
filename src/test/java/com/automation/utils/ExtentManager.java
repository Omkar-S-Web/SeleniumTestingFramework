package com.automation.utils;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // UPDATED: Path changed to your specific D: drive folder
            String directory = "D:/Testing report/";
            String reportPath = directory + "NaukriTestReport.html";
            
            // Create the folder if it doesn't exist
            File folder = new File(directory);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            
            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("Naukri Automation Results");
            spark.config().setDocumentTitle("QA Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Omkar");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}