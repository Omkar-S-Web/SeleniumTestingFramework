package com.automation.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

// We make this abstract because we only intend for other classes to inherit from it.
public abstract class BaseTest {

    // 1. Declare protected WebDriver
    // 'protected' allows LoginTest to access the driver without making it public.
    protected WebDriver driver;
    protected String baseUrl = "https://www.naukri.com";

    // 2. Parameterized Setup Method (Reads parameter from testng.xml)
    @BeforeMethod
    @Parameters("browser") 
    public void setup(String browserName) {
        
        System.out.println("Initializing WebDriver for: " + browserName);

        // Simple if/else to handle the browser parameter
        if (browserName.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else {
            // For simplicity, we'll default to Chrome if the parameter is missing/wrong
            System.out.println("Unsupported browser, defaulting to Chrome.");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    // 3. Teardown Method (Closes the driver)
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}