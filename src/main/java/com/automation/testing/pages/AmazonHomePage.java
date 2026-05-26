package com.automation.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AmazonHomePage extends BasePage {

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(css = "[data-component-type='s-search-result']")
    private List<WebElement> searchResults;

    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String product) {
        sendKeys(searchBox, product);
        click(searchButton);
    }

    public boolean areResultsDisplayed() {
        return !searchResults.isEmpty();
    }

    public int getResultCount() {
        return searchResults.size();
    }
}
