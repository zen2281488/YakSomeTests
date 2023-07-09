package utils;

import UITests.AuthTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

public class TestWatcherPlugin implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        AuthTest testInstance = (AuthTest) context.getRequiredTestInstance();
        WebDriver driver = testInstance.getDriver();
        ScreenShot.captureScreenshot(driver);
    }
}
