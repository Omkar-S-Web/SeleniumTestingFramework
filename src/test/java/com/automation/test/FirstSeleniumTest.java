package com.automation.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstSeleniumTest {
	
	@Test
	public void launchBrowserTest() {
		// Selenium Manager automatically finds the correct chromeDriver
		WebDriver driver = new ChromeDriver();
		
		//Open a simple url
		driver.get("https://www.naukri.com");
		
		driver.findElement(By.id("login_Layer")).click();
	
		//Input username
		driver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']")).sendKeys("sawantomkar173@gmail.com");
		//Input password
		driver.findElement(By.xpath("//input[@placeholder='Enter your password']")).sendKeys("Bruno@123");
		//Click on login
		driver.findElement(By.cssSelector(".btn-primary.loginButton")).click();
		
		//Explicit wait to handle the dynamic chat popup 
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".crossIcon.chatBot.chatBot-ic-cross"))).click();
		}
		catch(Exception e) {
			System.out.println("Chatbot cross icon not found or already closed. Continuing...");
		}		
		//Verification Steps
		// Retrieve the profile name
		String actualProfileName = driver.findElement(By.className("info__heading")).getText();
		
		//Assert that the retrieved name matches the expected name
		Assert.assertEquals(actualProfileName, "Omkar Dattatray Sawant");
//		driver.quit();
	}
    
}
