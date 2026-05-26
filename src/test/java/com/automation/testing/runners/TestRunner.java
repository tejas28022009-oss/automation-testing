package com.automation.testing.runners;

import com.automation.testing.listeners.TestListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.automation.testing.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        monochrome = true,
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
