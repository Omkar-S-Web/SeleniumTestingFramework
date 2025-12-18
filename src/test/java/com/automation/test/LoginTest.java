package com.automation.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider; 
import com.automation.pages.NaukriLoginPage;
import com.automation.utils.CsvReader; 

public class LoginTest extends BaseTest {
    
    private final String CSV_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/testData/loginCredentials.csv";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return CsvReader.getCsvData(CSV_FILE_PATH);
    }
    
    @Test(priority = 1, dataProvider = "loginData")
    public void verifyLogin(String username, String password, String expectedResult) {
        
        // CLEANUP: Removes surrounding quotes and trims spaces
        username = username.trim().replaceAll("^\"|\"$", "");
        password = password.trim().replaceAll("^\"|\"$", "");
        expectedResult = expectedResult.trim().replaceAll("^\"|\"$", "");
        
        NaukriLoginPage loginPage = new NaukriLoginPage(driver);
        loginPage.clickLoginLayer();
        loginPage.login(username, password);
        
        if (expectedResult.equalsIgnoreCase("Success")) {
            // Valid credentials path
            String profileName = loginPage.getProfileNameFromDropdown();
            Assert.assertTrue(profileName.contains("Omkar"), "Login failed for valid user.");
            loginPage.logout();
        } else {
            // Invalid credentials path
            // This verifies the 'server-err' message appears
            String actualError = loginPage.getInvalidLoginErrorMessage();
            Assert.assertTrue(actualError.contains("Invalid details"), 
                "Expected error not found. Actual: " + actualError);
            
            // CRITICAL: Close the modal so the next test can find the 'Login' button
            loginPage.closeLoginModal();
        }
    }
}