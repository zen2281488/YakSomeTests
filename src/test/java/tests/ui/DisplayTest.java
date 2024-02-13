package tests.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Step;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.WayIndexPage;
import utils.ConfProperties;

@Epic("Тесты отображения элементов страницы.")
public class DisplayTest extends BaseTest {
    private WayIndexPage wayIndexPage;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        wayIndexPage = new WayIndexPage(driver);
    }

    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие блока Контактов в Хедере")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения контактов в Хэдере")
    public void headerVisibilityTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        Assertions.assertTrue(wayIndexPage.contactsDisplayed(), "Конейнер с Контактами не отображается или отсутствует.");
    }

    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение блока с Сертификатами.")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @Issue("UI-WAY2 №4")
    @DisplayName("Проверка наличия и отображения блока с Сертификатами.")
    public void certificatesBlockVisibilityTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        Assertions.assertTrue(wayIndexPage.certificatesBlockDisplayed(), "Блок с Сертификатами не отображается либо не существует.");
    }

    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение Футера.")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @Issue("UI-WAY2 №6")
    @DisplayName("Проверка наличия и отображения Футера")
    public void footerVisibilityTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        Assertions.assertTrue(wayIndexPage.footerDisplayed(), "Футер не отображается или не существует.");
    }
}