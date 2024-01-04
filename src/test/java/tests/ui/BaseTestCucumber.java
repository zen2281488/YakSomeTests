package tests.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import utils.BrowserInit;
import utils.ConfProperties;

import java.util.concurrent.TimeUnit;

public class BaseTestCucumber {
    protected WebDriver driver;

    @Step("Очиска данных")
    public void baseAfter() {
        BrowserInit.closeWebdriver();
    }

    @Step("Инициализация Драйвера")
    public void setUp() {
        driver = BrowserInit.getWebdriverSelenoid(ConfProperties.getProperty("browserName"), ConfProperties.getProperty("browserMode"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }
}
