package steps;

import org.openqa.selenium.WebDriver;

public class TestContext {

    private WebDriver driver;

    public TestContext() {
        this.driver = WebDriverHooks.getDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
