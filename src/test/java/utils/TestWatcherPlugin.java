package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.lang.reflect.Field;


public class TestWatcherPlugin implements TestWatcher {
    private boolean isFirstFailure = true;

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = getWebDriverFromTestInstance(context.getRequiredTestInstance());
        if (driver != null) {
            ScreenShot.captureScreenshot(driver);
        }
        if (isFirstFailure) {
            try {
                FailedTestRecorder.newFailedTestFile();
                isFirstFailure = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private WebDriver getWebDriverFromTestInstance(Object testInstance) {
        try {
            Field driverField = testInstance.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            return (WebDriver) driverField.get(testInstance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
