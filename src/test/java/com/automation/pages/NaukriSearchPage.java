package com.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys; // Import Keys for the final ENTER action

public class NaukriSearchPage {
	
    private WebDriver driver;
    private WebDriverWait wait;

    // --- LOCATORS ---
    // 1. Locator for the main search placeholder text (The element you click to open search)
    private final By searchClickArea = By.className("nI-gNb-sb__placeholder"); 

    // 2. Locator for the keyword input field (after searchClickArea is clicked)
    private final By keywordInput = By.xpath("//input[@placeholder='Enter keyword / designation / companies']");
    	
    // 3. Locator for the Experience dropdown
    private final By experienceDropdown = By.xpath("//input[@placeholder='Select experience']");

    // 4. Locator for the specific 3 years experience option
    private final By threeYearsOption = By.xpath("//li[@title='3 years']");
    	
    // 5. Locator for the Location input field (after clicking its area)
    private final By locationInput = By.xpath("//input[@placeholder='Enter location']");

    // ----------------------------------------------------------------------
    // CONSTRUCTOR
    // ----------------------------------------------------------------------
    public NaukriSearchPage(WebDriver driver) {
    	this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    // ----------------------------------------------------------------------
    // ACTION METHODS
    // ----------------------------------------------------------------------
    
    /**
     * Performs a search with keyword, experience, and location filters.
     * @param keyword The skill or designation (e.g., "QA").
     * @param location The city name (e.g., "Pune").
     */
    public void performAdvancedSearch(String keyword, String location) {
        // 1. Click the main search area to activate the search fields
        wait.until(ExpectedConditions.elementToBeClickable(searchClickArea)).click();
        
        // 2. Input the Keyword (e.g., QA/Testing)
        wait.until(ExpectedConditions.visibilityOfElementLocated(keywordInput)).sendKeys(keyword);
        
        // 3. Select 3 Years Experience
        wait.until(ExpectedConditions.elementToBeClickable(experienceDropdown)).click(); // Click to open dropdown
        wait.until(ExpectedConditions.elementToBeClickable(threeYearsOption)).click(); // Click 3 years option
        
        // 4. Input Location 
        wait.until(ExpectedConditions.elementToBeClickable(locationInput)).sendKeys(location);
        
        // 5. Use Keys.ENTER to trigger the search and initiate navigation
        driver.findElement(locationInput).sendKeys(Keys.ENTER); 
    }
}