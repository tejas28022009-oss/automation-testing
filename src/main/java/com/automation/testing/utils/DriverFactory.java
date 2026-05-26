package com.automation.testing.utils;

import com.automation.testing.constants.BrowserType;
import com.automation.testing.constants.FrameworkConstants;
import com.automation.testing.exceptions.FrameworkException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(createDriver());
        }
        return driverThreadLocal.get();
    }

    private static WebDriver createDriver() {
        BrowserType browserType = BrowserType.fromString(ConfigReader.getBrowser());
        WebDriver driver;

        switch (browserType) {
            case FIREFOX -> driver = createFirefoxDriver();
            case FIREFOX_HEADLESS -> driver = createFirefoxHeadlessDriver();
            case EDGE -> driver = createEdgeDriver();
            case CHROME_HEADLESS -> driver = createChromeHeadlessDriver();
            case REMOTE_CHROME -> driver = createRemoteDriver("chrome");
            case REMOTE_FIREFOX -> driver = createRemoteDriver("firefox");
            case DOCKER_CHROME -> driver = createDockerChromeDriver();
            default -> driver = createChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(FrameworkConstants.PAGE_LOAD_WAIT));
        driver.manage().window().maximize();
        return driver;
    }

    private static ChromeOptions baseChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        return options;
    }

    private static ChromeDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = baseChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", FrameworkConstants.REPORTS_DIR);
        options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);
    }

    private static ChromeDriver createChromeHeadlessDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = baseChromeOptions();
        options.addArguments("--headless=new");
        return new ChromeDriver(options);
    }

    private static FirefoxDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static FirefoxDriver createFirefoxHeadlessDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        return new FirefoxDriver(options);
    }

    private static EdgeDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        return new EdgeDriver(options);
    }

    private static WebDriver createRemoteDriver(String browser) {
        try {
            String hubUrl = ConfigReader.get("selenium.hub.url");
            ChromeOptions options = new ChromeOptions();
            return new org.openqa.selenium.remote.RemoteWebDriver(
                    new URL(hubUrl), options);
        } catch (MalformedURLException e) {
            throw new FrameworkException("Invalid Selenium Grid URL", e);
        }
    }

    private static WebDriver createDockerChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = baseChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
