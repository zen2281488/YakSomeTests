package UITests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.ConfProperties;

@Epic("Тесты Авторизации.")
@Feature("Задание U3 тест авторизации с использованием DataProvider")
public class DataProviderAuthTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("utils.DataProviderUserData#getLogData")
    public void testLogin(String username, String password) {
        driver.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername(username).sendPassword(password).sendUserDescription(password).clickSubmitButton();
        Assertions.assertTrue(wayAutorisation.waitSuccessLoginText(), "Авторизация не прошла. Проверьте корректность введенных данных.");
    }
}