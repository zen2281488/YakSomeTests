package UITests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pageObjects.WayIndexPage;
import utils.BrowserInit;
import utils.ConfProperties;

@Epic("Тесты отображения элементов страницы.")
public class DisplayTest {
    private WebDriver browser;
    private WayIndexPage wayIndexPage;

    @BeforeEach
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
        Assertions.assertTrue(wayIndexPage.contactsDisplayed(), "Конейнер с Контактами не отображается или отсутствует.");
    }


    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение блока с Сертификатами.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №4")
    @DisplayName("Проверка наличия и отображения блока с Сертификатами.")
    public void certificatesBlockVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assertions.assertTrue(wayIndexPage.certificatesBlockDisplayed(), "Блок с Сертификатами не отображается либо не существует.");
    }


    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение Футера.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №6")
    @DisplayName("Проверка наличия и отображения Футера")
    public void footerVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assertions.assertTrue(wayIndexPage.footerDisplayed(), "Футер не отображается или не существует.");
    }


    @AfterEach
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}
