package UITests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pageObjects.WayAutorisation;
import utils.BrowserInit;
import utils.ConfProperties;

@Epic("Тесты Авторизации.")
@Feature("Задание U3 тест авторизации с использованием DataProvider")
public class DataProviderAuthTest {
    private WebDriver browser;
    private WayAutorisation wayAutorisation;

    @BeforeEach
    @Step("Инициализация браузера")
    public void setUp() {
        browser = BrowserInit.getWebdriver();
        wayAutorisation = new WayAutorisation(browser);
    }

    @ParameterizedTest
    @MethodSource("utils.DataProviderUserData#getLogData")
    public void testLogin(String username, String password) {
        browser.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername(username).sendPassword(password).sendUserDescription(password).clickSubmitButton();
        Assertions.assertTrue(wayAutorisation.waitSuccessLoginText(), "Авторизация не прошла. Проверьте корректность введенных данных.");
    }

    @AfterEach
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}