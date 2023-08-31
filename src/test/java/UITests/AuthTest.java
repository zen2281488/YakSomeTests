package UITests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageObjects.SeleniumTutorialIndex;
import pageObjects.WayAutorisation;
import pageObjects.WayLogin;
import utils.ConfProperties;
import utils.TestWatcherPlugin;

@ExtendWith(TestWatcherPlugin.class)
@Epic("Тесты Авторизации.")
@Feature("Тест авторизации.")
public class AuthTest extends BaseTest {

    private WayAutorisation wayAutorisation;
    private WayLogin waylogin;
    private SeleniumTutorialIndex seleniumTutorialIndex;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        wayAutorisation = new WayAutorisation(driver);
        waylogin = new WayLogin(driver);
        seleniumTutorialIndex = new SeleniumTutorialIndex(driver);
    }

    @Description("Тест проверяет авторизацию на сайте Practice Site 2. Появляется ли текст о успешной авторизации после ввода учетных данных.")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    @Issue("UI-WAY2 №8")
    @DisplayName("Проверка авторизации на сайте Practice Site 2")
    public void practiceSiteAuthorizationTest() {
        driver.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername(ConfProperties.getProperty("p2username")).sendPassword(ConfProperties.getProperty("p2pass")).sendUserDescription(ConfProperties.getProperty("p2username")).clickSubmitButton();
        Assertions.assertTrue(wayAutorisation.waitSuccessLoginText(), "Авторизация не прошла. Проверьте корректность введенных данных.");
    }

    @Description("Тест проверяет успешно ли проходит авторизация на сайте Way2 при вводе учетных данных пользователя.")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    @Issue("UI-WAY2 №10")
    @DisplayName("Проверка авторизации на сайте Way2")
    public void wayAuthorizationTest() {
        driver.get(ConfProperties.getProperty("loginWay2"));
        waylogin.loginSendEmail(ConfProperties.getProperty("way2LogEmail")).loginSendPassword(ConfProperties.getProperty("way2LogPassword")).clickCommit();
        Assertions.assertTrue(seleniumTutorialIndex.avatarImgExist(), "Аватар не был загружен, авторизация не прошла успешно.");
    }
}
