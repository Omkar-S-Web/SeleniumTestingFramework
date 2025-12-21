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
    // Remove the timestamp so the filename is just "verifyLogin.png"
    String fileName = testName + ".png"; 
    String filePath = "D:/Testing failures SS/" + fileName;

    try {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(filePath));
        return filePath;
    } catch (IOException e) {
        return null;
    }
}
}