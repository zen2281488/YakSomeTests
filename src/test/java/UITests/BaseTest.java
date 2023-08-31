package UITests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.BrowserInit;

public class BaseTest {
    protected WebDriver driver;

    @AfterEach
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }

    @BeforeEach
    @Step("Инициализация Драйвера")
    public void before() {
        driver = BrowserInit.getWebdriver();
    }
}
