package com.automation.testing.base;

import com.automation.testing.utils.ConfigReader;
import com.automation.testing.utils.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.getDriver().get(ConfigReader.getUrl());
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
