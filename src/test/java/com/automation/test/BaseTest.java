package com.automation.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // NEW IMPORT
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.utils.ScreenshotUtils;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        
        // 1. New Headless syntax (more stable)
        options.addArguments("--headless=new"); 
        
        // 2. IMPORTANT: Add a real User-Agent to bypass bot detection
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        
        // 3. Essential stability flags
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-blink-features=AutomationControlled"); // Hides "Automation" flag
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get("https://www.naukri.com/");
    }
    // This allows the ExtentListener to access the driver for screenshots
    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            // Even in headless mode, Selenium can still take screenshots!
            ScreenshotUtils.captureScreenshot(driver, result.getName());
        }
        
        if (driver != null) {
            driver.quit();
        }
    }
}