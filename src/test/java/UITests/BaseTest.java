package UITests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.BrowserInit;
import utils.ConfProperties;

public class BaseTest {
    protected WebDriver driver;

    @AfterEach
    @Step("Очиска данных")
    public void afterBase() {
        BrowserInit.closeWebdriver();
    }

    @BeforeEach
    @Step("Инициализация Драйвера")
    public void beforeBase() {
        driver = BrowserInit.getWebdriverSelenoid(ConfProperties.getProperty("browserName"), ConfProperties.getProperty("browserMode"));
    }
}
