package com.automation.testing.stepdefinitions;

import com.automation.testing.utils.ConfigReader;
import com.automation.testing.utils.DriverFactory;
import com.automation.testing.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Starting scenario: " + scenario.getName());
        System.out.println("Environment: " + ConfigReader.getEnvironment());
        System.out.println("Browser: " + ConfigReader.getBrowser());
        System.out.println("========================================");
        DriverFactory.getDriver().get(ConfigReader.getUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtils.getScreenshotBytes(DriverFactory.getDriver());
            scenario.attach(screenshot, "image/png", "failed-" + scenario.getName());
            System.out.println("FAILED: " + scenario.getName());
        } else {
            System.out.println("PASSED: " + scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
