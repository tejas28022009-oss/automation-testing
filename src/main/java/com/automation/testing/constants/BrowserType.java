package com.automation.testing.constants;

public enum BrowserType {
    CHROME,
    FIREFOX,
    EDGE,
    CHROME_HEADLESS,
    FIREFOX_HEADLESS,
    REMOTE_CHROME,
    REMOTE_FIREFOX,
    DOCKER_CHROME;

    public static BrowserType fromString(String browser) {
        for (BrowserType bt : values()) {
            if (bt.name().equalsIgnoreCase(browser)) {
                return bt;
            }
        }
        return CHROME;
    }
}
