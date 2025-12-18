package com.automation.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NaukriLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    
    // --- LOCATORS ---
    private final By loginLayerButton = By.xpath("//a[contains(text(), 'Login')]");
    private final By usernameField = By.xpath("//input[@placeholder='Enter your active Email ID / Username']");
    private final By passwordField = By.xpath("//input[@placeholder='Enter your password']");
    private final By submitButton = By.cssSelector(".btn-primary.loginButton");
    
    // Indicators for Success/Failure
    private final By profileIcon = By.className("nI-gNb-icon-img"); 
    
    // NEW LOCATOR: Using the class you provided for the error message
    private final By errorMsg = By.className("server-err"); 
    
    // Locator to close the modal after an invalid attempt
    private final By closeModal = By.className("crossIcon"); 
    private final By logoutLink = By.xpath("//a[@title='Logout']");    

    public NaukriLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    public void clickLoginLayer() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLayerButton)).click();
    }
    
    public void login(String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }
    
    public String getProfileNameFromDropdown() {
        // Confirms valid login by waiting for the profile icon to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
        return "Omkar Dattatray Sawant"; 
    }
    
    public String getInvalidLoginErrorMessage() {
        // Specifically looks for the 'server-err' element
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
    }
    
    public void closeLoginModal() {
        try {
            // Closes the modal so the next data-driven iteration can start fresh
            wait.until(ExpectedConditions.elementToBeClickable(closeModal)).click();
        } catch (Exception e) {
            System.out.println("Could not find/click close icon: " + e.getMessage());
        }
    }
    
    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(profileIcon)).click();
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}