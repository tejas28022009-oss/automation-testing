package com.automation.testing.constants;

public final class FrameworkConstants {

    private FrameworkConstants() {}

    public static final String PROJECT_DIR = System.getProperty("user.dir");
    public static final String RESOURCES_DIR = PROJECT_DIR + "/src/test/resources";
    public static final String CONFIG_DIR = RESOURCES_DIR + "/config";
    public static final String FEATURES_DIR = RESOURCES_DIR + "/features";
    public static final String TESTDATA_DIR = RESOURCES_DIR + "/testdata";
    public static final String REPORTS_DIR = RESOURCES_DIR + "/reports";
    public static final String EXTENT_CONFIG = RESOURCES_DIR + "/extent-config.xml";
    public static final String SCREENSHOT_DIR = REPORTS_DIR + "/screenshots";
    public static final String ENV = System.getProperty("environment", "dev");
    public static final String CONFIG_FILE = CONFIG_DIR + "/" + ENV + ".properties";

    public static final int EXPLICIT_WAIT = 15;
    public static final int PAGE_LOAD_WAIT = 30;
    public static final int SCRIPT_TIMEOUT = 10;
    public static final int RETRY_COUNT = 2;
}
