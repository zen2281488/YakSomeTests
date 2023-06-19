package UITests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.*;
import utils.BrowserInit;
import utils.ConfProperties;

@Epic("Тесты Авторизации.")
public class AuthTest {
    private WebDriver browser;
    private WayAutorisation wayAutorisation;
    private WayLogin waylogin;
    private SeleniumTutorialIndex seleniumTutorialIndex;

    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        wayAutorisation = new WayAutorisation(browser);
        waylogin = new WayLogin(browser);
        seleniumTutorialIndex = new SeleniumTutorialIndex(browser);
    }

    @Feature("Тест авторизации.")
    @Description("Тест проверяет авторизацию на сайте Practice Site 2. Появляется ли текст о успешной авторизации после ввода учетных данных.")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    @Issue("UI-WAY2 №8")
    @DisplayName("Проверка авторизации на сайте Practice Site 2")
    public void practiceSiteAuthorizationTest() {
        browser.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername(ConfProperties.getProperty("p2username")).sendPassword(ConfProperties.getProperty("p2pass")).sendUserDescription(ConfProperties.getProperty("p2username")).clickSubmitButton().waitSuccessLoginText();
        Assert.assertEquals("Текст уведомления о успешной авторизации не совпадает с ожидаемым.", "You're logged in!!", wayAutorisation.getSuccessLogText());
    }

    @Feature("Тест авторизации.")
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

    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }}
