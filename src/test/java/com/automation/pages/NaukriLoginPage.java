package com.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions; 
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NaukriLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    
    // --- LOCATORS ---
    
    // Login Layer/Button (On the main page)
    // CRITICAL UPDATE: Using XPath targeting the text, which is more stable than the dynamic ID "login_Layer".
    private final By loginLayerButton = By.xpath("//a[contains(text(), 'Login')]");

    // Login Form Fields (Inside the modal)
    private final By usernameField = By.xpath("//input[@placeholder='Enter your active Email ID / Username']");
    private final By passwordField = By.xpath("//input[@placeholder='Enter your password']");
    private final By submitButton = By.cssSelector(".btn-primary.loginButton");
    
    // Elements after Successful Login
    private final By profileNameHeading = By.className("info__heading"); 
    private final By profileViewLink = By.cssSelector("a[href*='/mnjuser/profile']");
    
    // Logout Locators
    private final By profileIcon = By.className("nI-gNb-icon-img"); 
    private final By logoutLink = By.xpath("//a[@title='Logout']");   
    // ----------------------------------------------------------------------
    // CONSTRUCTOR
    // ----------------------------------------------------------------------
    public NaukriLoginPage(WebDriver driver) {
    	this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    // ----------------------------------------------------------------------
    // ACTION METHODS
    // ----------------------------------------------------------------------
    
    public void clickLoginLayer() {
        // Updated wait: Wait for presence, then clickability, to handle page stability issues
        wait.until(ExpectedConditions.presenceOfElementLocated(loginLayerButton));
        wait.until(ExpectedConditions.elementToBeClickable(loginLayerButton)).click();
    }
    
    public void login(String username, String password) {
    	// 1. Wait for the username field to be ready
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        
        // 2. Enter username, password, and click submit
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
        
        // 3. Wait for the login to complete and the profile icon to be present
        // Updated wait: Use presence for profile icon for robustness
        wait.until(ExpectedConditions.presenceOfElementLocated(profileIcon));
    }
    
    public String getProfileName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileNameHeading)); 
        return driver.findElement(profileNameHeading).getText();
    }
    
    public void clickViewProfile() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(profileViewLink));
        driver.findElement(profileViewLink).click();
        Thread.sleep(10000); // 10-second hard wait (Not recommended, but kept as per your original file)
    }
    
    // ----------------------------------------------------------------------
    // LOGOUT METHOD
    // ----------------------------------------------------------------------
   public void logout() {
        // Wait for profile icon to be visible/clickable and click it to open the menu
        wait.until(ExpectedConditions.elementToBeClickable(profileIcon)).click();
        
        // Hard wait to allow the complex dropdown menu to fully render.
        try {
            System.out.println("Waiting 5 seconds for Logout link to stabilize...");
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Wait for the Logout link to appear and click it
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
    
    // ----------------------------------------------------------------------
    // LOGOUT VERIFICATION METHOD
    // ----------------------------------------------------------------------
    public boolean isLoginButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginLayerButton));
            return driver.findElement(loginLayerButton).isDisplayed();
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}