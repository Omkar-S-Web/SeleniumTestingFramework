package com.automation.test;

import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.utils.ScreenshotUtils;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
public void setup() {
    ChromeOptions options = new ChromeOptions();
    
    // 1. Hide the "I am a bot" flag
    options.addArguments("--disable-blink-features=AutomationControlled");
    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
    
    // 2. Set modern headless mode
    options.addArguments("--headless=new"); 
    options.addArguments("--window-size=1920,1080");

    // 3. Add a realistic User-Agent so you don't look like a script
    options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

    driver = new ChromeDriver(options);
    // 4. Navigate immediately to prevent timeout
    driver.get("https://www.naukri.com"); 
}

    // Required for the ExtentListener to grab the driver instance upon failure
    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                // Give the browser 1 second to render the "Access Denied" page 
                // so the screenshot isn't just a blank white screen.
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // This saves the file to D:/Testing failures SS/
            ScreenshotUtils.captureScreenshot(driver, result.getName());
        }
        
        if (driver != null) {
            driver.quit();
        }
    }
}