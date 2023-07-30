package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

public class TestWatcherPlugin implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = getWebDriverFromTestInstance(context.getRequiredTestInstance());
        if (driver != null) {
            ScreenShot.captureScreenshot(driver);
        }
    }

    private WebDriver getWebDriverFromTestInstance(Object testInstance) {
        try {
            Field driverField = testInstance.getClass().getDeclaredField("browser");
            driverField.setAccessible(true);
            return (WebDriver) driverField.get(testInstance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
