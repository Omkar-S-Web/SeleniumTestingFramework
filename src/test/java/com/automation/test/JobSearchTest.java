package com.automation.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.pages.NaukriSearchPage;
import com.automation.pages.NaukriLoginPage; 

// Added for the critical synchronization fix
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait; 
import java.time.Duration;

// Extends BaseTest to automatically get setup() and teardown()
public class JobSearchTest extends BaseTest {
    
    // Define the unique user details required for this feature
    private final String REQUIRED_USERNAME = "sawantomkar173@gmail.com";
    private final String REQUIRED_PASSWORD = "Bruno@123";
    private final String REQUIRED_PROFILE_NAME = "Omkar Dattatray Sawant"; 
    
    // SRC-002 & SRC-003: Multi-Criteria Filter Search Test
    @Test(priority = 1)
    public void verifyAdvancedSearchForQAInPune() {
        
        // 1. Initialize Page Objects needed for this test
        NaukriLoginPage loginPage = new NaukriLoginPage(driver); 
        NaukriSearchPage searchPage = new NaukriSearchPage(driver);
        
        // --- PREREQUISITE: LOGIN ---
        loginPage.clickLoginLayer();
        loginPage.login(REQUIRED_USERNAME, REQUIRED_PASSWORD); 
        
        // --- CORE TEST LOGIC ---
        String searchKeyword = "QA";
        String searchLocation = "Pune";

        searchPage.performAdvancedSearch(searchKeyword, searchLocation);

        // 3. Synchronization and Verification 

        // CRITICAL FIX: Add a dynamic wait for the new URL and Title to load before reading them
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(15)); 

        // Wait 1: Wait for the URL to contain the location keyword (confirms navigation)
        // Converted to lowercase to handle URL case-sensitivity.
        localWait.until(ExpectedConditions.urlContains(searchLocation.toLowerCase()));

        // Wait 2: Wait for the Page Title to contain a known, stable phrase (guarantees page content stability)
        // We use "Jobs in Pune" as it is stable and case-insensitive check is not needed here.
        localWait.until(ExpectedConditions.titleContains("Qa Jobs In Pune")); 
        
        // Get the final URL and Page Title after the search is confirmed stable
        String currentUrl = driver.getCurrentUrl();
        String pageTitle = driver.getTitle();

        // Assert 1: Verify the URL contains the location parameter (Case-Insensitive Fix)
        Assert.assertTrue(currentUrl.toLowerCase().contains(searchLocation.toLowerCase()),
                          "Assertion Failed: URL did not contain expected location (" + searchLocation + ") parameter. Found URL: " + currentUrl);

        // Assert 2: Verify the PAGE TITLE contains the search keyword (Case-Insensitive Fix)
        Assert.assertTrue(pageTitle.toLowerCase().contains(searchKeyword.toLowerCase()), 
                          "Assertion Failed: Page Title did not contain the search keyword: " + searchKeyword + ". Found Title: " + pageTitle);
    }
}