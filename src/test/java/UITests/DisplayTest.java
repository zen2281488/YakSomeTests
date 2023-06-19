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

@Epic("Тесты Авторизации, отображения и работоспособности элементов страницы.")
public class DisplayTest {
    private WebDriver browser;
    private WayIndexPage wayIndexPage;

    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        wayIndexPage = new WayIndexPage(browser);
    }

    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие блока Контактов в Хедере")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения контактов в Хэдере")
    public void headerVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assert.assertTrue("Конейнер с Контактами не отображается или отсутствует.", wayIndexPage.contactsDisplayed());
    }


    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение блока с Сертификатами.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №4")
    @DisplayName("Проверка наличия и отображения блока с Сертификатами.")
    public void certificatesBlockVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assert.assertTrue("Блок с Сертификатами не отображается либо не существует.", wayIndexPage.certificatesBlockDisplayed());
    }


    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение Футера.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №6")
    @DisplayName("Проверка наличия и отображения Футера")
    public void footerVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assert.assertTrue("Футер не отображается или не существует.", wayIndexPage.footerDisplayed());
    }


    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}
