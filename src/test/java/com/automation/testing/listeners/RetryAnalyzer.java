package com.automation.testing.listeners;

import com.automation.testing.constants.FrameworkConstants;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < FrameworkConstants.RETRY_COUNT) {
            retryCount++;
            System.out.println("Retrying test '" + result.getName()
                    + "' - attempt " + retryCount + " of " + FrameworkConstants.RETRY_COUNT);
            return true;
        }
        return false;
    }
}
