package UITests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.SeleniumTutorialIndex;
import pageObjects.WayAutorisation;
import pageObjects.WayLogin;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.BrowserInit;
import utils.ConfProperties;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.TestWatcherPlugin;

@ExtendWith(TestWatcherPlugin.class)
@Epic("Тесты Авторизации.")
@Feature("Тест авторизации.")
public class AuthTest {
    private WebDriver browser;
    private WayAutorisation wayAutorisation;
    private WayLogin waylogin;
    private SeleniumTutorialIndex seleniumTutorialIndex;
    private AShot ashot;

    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        wayAutorisation = new WayAutorisation(browser);
        waylogin = new WayLogin(browser);
        seleniumTutorialIndex = new SeleniumTutorialIndex(browser);
        ashot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100));
    }


    @Description("Тест проверяет авторизацию на сайте Practice Site 2. Появляется ли текст о успешной авторизации после ввода учетных данных.")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    @Issue("UI-WAY2 №8")
    @DisplayName("Проверка авторизации на сайте Practice Site 2")
    public void practiceSiteAuthorizationTest() {
        browser.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername(ConfProperties.getProperty("p2username")).sendPassword(ConfProperties.getProperty("p2pass")).sendUserDescription(ConfProperties.getProperty("p2username")).clickSubmitButton();
        Assert.assertTrue("Авторизация не прошла. Проверьте корректность введенных данных.",wayAutorisation.waitSuccessLoginText());
    }


    @Description("Тест проверяет успешно ли проходит авторизация на сайте Way2 при вводе учетных данных пользователя.")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    @Issue("UI-WAY2 №10")
    @DisplayName("Проверка авторизации на сайте Way2")
    public void wayAuthorizationTest() {
        browser.get(ConfProperties.getProperty("loginWay2"));
        waylogin.loginSendEmail(ConfProperties.getProperty("way2LogEmail")).loginSendPassword(ConfProperties.getProperty("way2LogPassword")).clickCommit();
        Assert.assertTrue("Аватар не был загружен, авторизация не прошла успешно.", seleniumTutorialIndex.avatarImgDisplayed());
    }
    public WebDriver getDriver() {
        return browser;
    }
    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}
