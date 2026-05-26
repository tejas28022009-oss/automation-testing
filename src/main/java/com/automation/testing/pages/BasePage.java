package com.automation.testing.pages;

import com.automation.testing.constants.FrameworkConstants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        highlight(element);
        element.click();
    }

    protected void clickUsingJs(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        highlight(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    protected String getAttribute(WebElement element, String attribute) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute(attribute);
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    protected void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    protected void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    protected void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    protected void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    protected void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitForTextToBePresent(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected void highlight(WebElement element) {
        try {
            js.executeScript("arguments[0].style.border='2px solid red'", element);
        } catch (Exception ignored) {}
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    public String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }
}
