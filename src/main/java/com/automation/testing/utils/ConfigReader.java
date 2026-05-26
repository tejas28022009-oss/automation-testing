package com.automation.testing.utils;

import com.automation.testing.constants.FrameworkConstants;
import com.automation.testing.exceptions.FrameworkException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigReader {

    private static Properties properties;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        properties = new Properties();
        String env = System.getProperty("environment", "dev");
        try {
            String path = FrameworkConstants.CONFIG_DIR + "/" + env + ".properties";
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            throw new FrameworkException("Failed to load config for environment: " + env, e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new FrameworkException("Property '" + key + "' not found in config");
        }
        return value;
    }

    public static String getBrowser() {
        return System.getProperty("browser", get("browser"));
    }

    public static String getUrl() {
        return get("url");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(get("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(get("explicit.wait"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static String getEnvironment() {
        return FrameworkConstants.ENV;
    }
}
