package com.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils; // This will stop being red after Step 2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        
        // UPDATED: Path changed to your specific D: drive folder
        String directory = "D:/Testing failures SS/"; 
        File folder = new File(directory);
        
        // This will automatically create the folder on your D: drive if it's missing
        if (!folder.exists()) {
            folder.mkdirs(); 
        }

        String filePath = directory + fileName;

        try {
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(filePath));
            System.out.println("Screenshot saved to: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
        return filePath;
    }
}