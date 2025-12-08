package com.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NaukriLoginPage {
	
	
    //Private field to store the driver
    private WebDriver driver;
    
    // Login Button that opens the form
    public static final By LOGIN_BUTTON_LAYER = By.id("login_Layer");

    // Username field inside the form
    public static final By USERNAME_FIELD = By.xpath("//input[@placeholder='Enter your active Email ID / Username']");
    
    // Password field inside the form
    public static final By PASSWORD_FIELD = By.xpath("//input[@placeholder='Enter your password']");
    
    // Login submit button inside the form
    public static final By SUBMIT_BUTTON = By.cssSelector(".btn-primary.loginButton");
    
    // The profile name on the dashboard (Verification element)
    public static final By PROFILE_NAME_HEADING = By.className("info__heading"); 
    
    //The profile link open
    public static final By PROFILE_VIEW_LINK = By.cssSelector("a[href*='/mnjuser/profile']");
    
    // Constructor to initialize the driver field
    public NaukriLoginPage(WebDriver driver) {
    	this.driver = driver;
    }
    public void clickLoginLayer() {
    	// How do you find the element using the driver, passing in the locator variable,
        // and then perform the click action?
    	driver.findElement(NaukriLoginPage.LOGIN_BUTTON_LAYER).click();
    }
    public void login(String username, String password) {
    	
    	// 1. ADD THIS EXPLICIT WAIT: Wait for the username field to be ready
        WebDriverWait loginWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginWait.until(ExpectedConditions.elementToBeClickable(USERNAME_FIELD));
        
        // 1. Enter username
        driver.findElement(NaukriLoginPage.USERNAME_FIELD).sendKeys(username);

        // 2. Enter password
        driver.findElement(NaukriLoginPage.PASSWORD_FIELD).sendKeys(password);

        // 3. Click submit
        driver.findElement(NaukriLoginPage.SUBMIT_BUTTON).click();
    }
    // Add this method to the NaukriLoginPage class
public String getProfileName() {
    // 1. Initialize the Explicit Wait
    WebDriverWait dashboardWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    
    // 2. Wait until the profile heading element becomes visible (The locator is stored as PROFILE_NAME_HEADING)
    dashboardWait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_NAME_HEADING)); 
    
    // 3. Find the element and return the text for verification
    return driver.findElement(PROFILE_NAME_HEADING).getText();
}
public void clickViewProfile() throws InterruptedException{
	// 1. ADD EXPLICIT WAIT: Wait for the profile link to be clickable
    WebDriverWait profileWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    profileWait.until(ExpectedConditions.elementToBeClickable(PROFILE_VIEW_LINK));
    
    // 2. Click the profile link
    driver.findElement(PROFILE_VIEW_LINK).click();
    
    // 3. Wait for 30 seconds, as requested (30000 milliseconds)
    Thread.sleep(30000);
	}

}