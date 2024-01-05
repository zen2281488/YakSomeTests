package steps;

import io.cucumber.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import utils.BrowserInit;
import utils.ConfProperties;

import java.util.concurrent.TimeUnit;

public class WebDriverHooks {

    private static WebDriver driver;

    @Before
    public void setUp() {
        driver = BrowserInit.getWebdriverSelenoid(ConfProperties.getProperty("browserName"), ConfProperties.getProperty("browserMode"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
