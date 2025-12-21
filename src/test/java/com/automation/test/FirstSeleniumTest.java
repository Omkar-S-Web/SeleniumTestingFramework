package com.automation.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class FirstSeleniumTest extends BaseTest { // Now extends BaseTest

    @Test
    public void launchBrowserTest() {
        // DO NOT create 'new ChromeDriver()' here!
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        driver.get("https://www.naukri.com");
        
        // Wait for login to be clickable before clicking
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login_Layer")));
        loginBtn.click();

        // Use visibility waits for all inputs
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your active Email ID / Username']")))
            .sendKeys("sawantomkar173@gmail.com");
            
        driver.findElement(By.xpath("//input[@placeholder='Enter your password']")).sendKeys("Bruno@123");
        driver.findElement(By.cssSelector(".btn-primary.loginButton")).click();

        // Profile verification
        String actualName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("info__heading"))).getText();
        Assert.assertEquals(actualName, "Omkar Dattatray Sawant");
    }
}