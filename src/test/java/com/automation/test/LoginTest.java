package com.automation.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.pages.NaukriLoginPage;


// This class inherits setup (driver creation/navigation) and teardown (driver closing) from BaseTest.
public class LoginTest extends BaseTest {
	
	// ----------------------------------------------------
	// 1. DATA PROVIDER METHOD (Supplies data for the DDT test)
	// ----------------------------------------------------
	@DataProvider(name = "LoginData")
	public Object[][] loginTestData() {
	    // Defines the data: 3 columns (Username, Password, Expected Name)
	    Object[][] data = new Object[][] {
	        // Run 1 (Successful Login)
	        {"sawantomkar173@gmail.com", "Bruno@123", "Omkar Dattatray Sawant"}, 
	        
	        // Run 2 (Example for failure testing/second user)
	        {"invalid@example.com", "wrongpassword", "Expected User Name"}, 
	    };
	    
	    return data;
	}
	
	// ----------------------------------------------------
	// 2. DATA-DRIVEN LOGIN TEST
	// ----------------------------------------------------
	@Test(dataProvider = "LoginData")
	public void verifySuccessfulLoginDDT(String username, String password, String expectedName) {
		
		// 'driver' is inherited from BaseTest
		NaukriLoginPage loginPage = new NaukriLoginPage(driver); 
		
		// 1. Perform Actions using the clean, reusable methods
		loginPage.clickLoginLayer();
		loginPage.login(username, password);
		
		// 2. Verification 
		String actualProfileName = loginPage.getProfileName();
		
		// 3. Assert the result
		Assert.assertEquals(actualProfileName, expectedName, "Login Assertion Failed for user: " + username); 
	}
    
	// ----------------------------------------------------
	// 3. LOGOUT TEST CASE (LGN-004)
	// ----------------------------------------------------
	@Test
	public void verifyLogoutAndRedirection() {
	 	
	 	NaukriLoginPage loginPage = new NaukriLoginPage(driver);
	 	
	 	// Prerequisite: Login successfully first
	 	loginPage.clickLoginLayer();
	 	loginPage.login("sawantomkar173@gmail.com", "Bruno@123"); 
	 	
	 	// 1. Perform Logout Action (This method needs to be added to NaukriLoginPage)
        // This will typically involve hovering over the profile icon and clicking 'Logout'.
	 	loginPage.logout();
	 	
	 	// 2. Verification: Check if the application redirects back to the public homepage 
        // by checking for an element unique to the non-logged-in state, like the "Login" button.
        boolean isLoginButtonPresent = loginPage.isLoginButtonDisplayed();
        
        Assert.assertTrue(isLoginButtonPresent, "Logout failed or redirection to homepage did not occur.");
	}

	// ----------------------------------------------------
	// 4. Other existing tests
	// ----------------------------------------------------
	
	@Test
	public void verifyProfileHeadingIsVisible() {
		
		NaukriLoginPage loginPage = new NaukriLoginPage(driver);
		
		// Prerequisite Login
		loginPage.clickLoginLayer();
		loginPage.login("sawantomkar173@gmail.com", "Bruno@123");
		
		// Verification
		String actualProfileName = loginPage.getProfileName();
		
		// Assert the result
		Assert.assertNotNull(actualProfileName, "Omkar Dattatray Sawant");

	}
	
	@Test
	public void verifyProfileLinkClickAndPause() throws InterruptedException {
	 	
	 	NaukriLoginPage loginPage = new NaukriLoginPage(driver);
	 	
	 	// Prerequisite Login
	 	loginPage.clickLoginLayer();
	 	loginPage.login("sawantomkar173@gmail.com", "Bruno@123");
	 	
	 	// Perform the action under test
	 	System.out.println("Starting 30 second pause on Profile Page...");
	 	loginPage.clickViewProfile();
	 	System.out.println("...30 second pause complete. Test finished.");
	}
}