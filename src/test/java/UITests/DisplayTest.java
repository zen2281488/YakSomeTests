package UITests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfProperties;

@Epic("Тесты отображения элементов страницы.")
public class DisplayTest extends BaseTest {
    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие блока Контактов в Хедере")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения контактов в Хэдере")
    public void headerVisibilityTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        Assertions.assertTrue(wayIndexPage.contactsDisplayed(), "Конейнер с Контактами не отображается или отсутствует.");
    }

    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение блока с Сертификатами.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №4")
    @DisplayName("Проверка наличия и отображения блока с Сертификатами.")
    public void certificatesBlockVisibilityTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        Assertions.assertTrue(wayIndexPage.certificatesBlockDisplayed(), "Блок с Сертификатами не отображается либо не существует.");
    }

    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение Футера.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №6")
    @DisplayName("Проверка наличия и отображения Футера")
    public void footerVisibilityTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        Assertions.assertTrue(wayIndexPage.footerDisplayed(), "Футер не отображается или не существует.");
    }
}
