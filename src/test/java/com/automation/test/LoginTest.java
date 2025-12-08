package com.automation.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.pages.NaukriLoginPage;

public class LoginTest {
	
	//Declear the webdriver at class level 
	private WebDriver driver;
	
	//Define the setup method
	@BeforeMethod
	public void setup() {
		//Initialization and Navigation moves here!
		
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.naukri.com");
	}
	@AfterMethod
    public void teardown() {
        driver.quit();
    }
	
	@Test
	public void verifySuccessfulLogin() {
		
		// 2. Instantiate the Page Object
		// This passes the driver control to the page object class
		NaukriLoginPage loginPage = new NaukriLoginPage(driver);
		
		// 3. Perform Actions using the clean, reusable methods
		loginPage.clickLoginLayer();
		loginPage.login("sawantomkar173@gmail.com", "Bruno@123");
		
		// 4. Verification (The getProfileName() method handles the wait internally)
		String actualProfileName = loginPage.getProfileName();
		
		// 5. Assert the result
		Assert.assertEquals(actualProfileName, "Omkar Dattatray Sawant");

	}
	@Test
	public void verifyProfileHeadingIsVisible() {
		
		// 2. Instantiate the Page Object
		// This passes the driver control to the page object class
		NaukriLoginPage loginPage = new NaukriLoginPage(driver);
		
		// 3. Perform Actions using the clean, reusable methods
		loginPage.clickLoginLayer();
		loginPage.login("sawantomkar173@gmail.com", "Bruno@123");
		
		// 4. Verification (The getProfileName() method handles the wait internally)
		String actualProfileName = loginPage.getProfileName();
		
		// 5. Assert the result
		Assert.assertNotNull(actualProfileName, "Omkar Dattatray Sawant");

	}
	@Test
	public void verifyProfileLinkClickAndPause() throws InterruptedException {
	    
	    // 1. Instantiate the Page Object (Required for every test)
	    NaukriLoginPage loginPage = new NaukriLoginPage(driver);
	    
	    // 2. Perform Login Actions (Prerequisite for the operation)
	    loginPage.clickLoginLayer();
	    loginPage.login("sawantomkar173@gmail.com", "Bruno@123");
	    
	    // 3. Perform the new operation (Click link and pause 30 sec)
	    System.out.println("Starting 30 second pause on Profile Page...");
	    loginPage.clickViewProfile();
	    System.out.println("...30 second pause complete. Test finished.");

	    // Optional Assertion: Add a simple check here if needed, 
	    // but the test primarily verifies the click and wait.
	}
}