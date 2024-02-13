package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import utils.BrowserInit;

import java.util.concurrent.TimeUnit;

public class WebDriverHooks {

    @Before
    public void setUp() {
        WebDriver driver = BrowserInit.getWebdriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }
    @After
    public void baseAfter() {
        BrowserInit.closeWebdriver();
    }

}