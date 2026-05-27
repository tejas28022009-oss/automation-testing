package com.automation.testing.stepdefinitions;

import com.automation.testing.pages.AmazonHomePage;
import com.automation.testing.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AmazonSearchSteps {

    private final AmazonHomePage amazonHomePage = new AmazonHomePage(DriverFactory.getDriver());

    @Given("the user is on Amazon homepage")
    public void userIsOnAmazonHomepage() {
        String title = amazonHomePage.getPageTitle();
        System.out.println("Page title: " + title);
        Assert.assertNotNull(title);
    }

    @When("the user searches for {string}")
    public void userSearchesFor(String product) {
        amazonHomePage.searchFor(product);
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        Assert.assertTrue(amazonHomePage.areResultsDisplayed(),
                "No search results were displayed");
        System.out.println("Search results found: " + amazonHomePage.getResultCount());
    }
}
