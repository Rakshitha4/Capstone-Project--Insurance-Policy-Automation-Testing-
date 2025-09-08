package com.parabank.tests;

import com.parabank.pages.LoginPage;
import com.parabank.pages.LoginPage.LoginStatus;
import com.parabank.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.parabank.listeners.TestListener.class)
public class LoginTests extends BaseTest { 

    @Test(description = "Verify valid login attempt")
    public void validLogin() {
        LoginPage login = new LoginPage(driver);

        // Read credentials from config (UPDATE YOUR config.properties if these don't work)
        String validUser = ConfigReader.get("validUsername");
        String validPass = ConfigReader.get("validPassword");

        // IMPORTANT: Add logging here to see what credentials are being used
        System.out.println("Attempting valid login with user: " + validUser);

        // Perform login and wait for outcome
        LoginStatus status = login.performLoginAndWait(validUser, validPass);

        Assert.assertEquals(status, LoginStatus.SUCCESS, "Valid login should succeed.");
        // Logout only if we actually logged in
        if (login.isLoggedIn()) {
            System.out.println("Valid login successful, logging out.");
            login.logout();
        } else {
            System.out.println("Valid login failed, status: " + status);
        }
    }


}