package tests.ui;

import io.github.artsok.ParameterizedRepeatedIfExceptionsTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.MethodSource;
import pageObjects.WayAuthorization;
import utils.ConfProperties;

@Epic("Тесты Авторизации.")
@Feature("Задание U3 тест авторизации с использованием DataProvider")
public class DataProviderAuthTest extends BaseTest {
    private WayAuthorization wayAuthorization;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        wayAuthorization = new WayAuthorization(driver);
    }

    @ParameterizedRepeatedIfExceptionsTest(name = "Login: {0} , password: {1}",
            repeatedName = " (Repeat {currentRepetition} of {totalRepetitions})",
            repeats = 10, exceptions = RuntimeException.class, minSuccess = 2)
    @MethodSource("utils.DataProviderUserData#getLogData")

    public void testLogin(String username, String password) {
        driver.get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("practice2login"));
        wayAuthorization.sendUsername(username)
                .sendPassword(password)
                .sendUserDescription(password)
                .clickSubmitButton()
                .waitSuccessLoginText();
    }
}