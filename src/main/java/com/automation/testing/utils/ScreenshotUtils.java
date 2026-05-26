package com.automation.testing.utils;

import com.automation.testing.constants.FrameworkConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = testName + "_" + timestamp + ".png";
        Path path = Paths.get(FrameworkConstants.SCREENSHOT_DIR, fileName);

        try {
            Files.createDirectories(path.getParent());
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(path, screenshotBytes);
            System.out.println("Screenshot saved: " + path);
            return path.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getScreenshotBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
