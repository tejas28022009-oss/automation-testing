package com.automation.testing.stepdefinitions;

import com.automation.testing.utils.ConfigReader;
import com.automation.testing.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class VerifyFrameworkSteps {

    @Given("I open the application")
    public void iOpenTheApplication() {
        DriverFactory.getDriver().get(ConfigReader.getUrl());
    }

    @Then("the browser should load successfully")
    public void theBrowserShouldLoadSuccessfully() {
        String title = DriverFactory.getDriver().getTitle();
        Assert.assertNotNull(title, "Browser did not load - title is null");
        System.out.println("========================================");
        System.out.println("FRAMEWORK VERIFIED SUCCESSFULLY");
        System.out.println("Browser: " + ConfigReader.getBrowser());
        System.out.println("Environment: " + ConfigReader.getEnvironment());
        System.out.println("Page Title: " + title);
        System.out.println("========================================");
    }
}
