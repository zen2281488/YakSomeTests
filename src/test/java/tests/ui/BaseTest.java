package tests.ui;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import utils.BrowserInit;
import utils.ConfProperties;
import utils.FailedTestExtension;
import utils.TestWatcherPlugin;

import java.util.concurrent.TimeUnit;


@ExtendWith(FailedTestExtension.class)
@ExtendWith(TestWatcherPlugin.class)
public class BaseTest {
    protected WebDriver driver;

    @AfterEach
    @Step("Очиска данных")
    public void baseAfter() {
        BrowserInit.closeWebdriver();
    }

    @BeforeEach
    @Step("Инициализация Драйвера")
    public void baseBefore() {
        driver = BrowserInit.getWebdriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }
}
