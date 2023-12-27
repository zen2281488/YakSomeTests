package tests.ui;


import io.github.artsok.RepeatedIfExceptionsTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.WayAutoAlerts;
import utils.ConfProperties;

@Epic("U11. Tabs")
public class AlertsTest extends BaseTest {
    private WayAutoAlerts wayAutoAlerts;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        wayAutoAlerts = new WayAutoAlerts(driver);
    }

    @RepeatedIfExceptionsTest(repeats = 2)
    @DisplayName("Тест вывода записаного в поле Алерт текста")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест изменения состояния текста в блоке фрейма, при введении текста в поле Алерта, которое появляется при нажатии кнопки.")
    public void testAlertInput() {
        driver.get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("wayAutomationAlerts"));
        wayAutoAlerts.clickInputAlertsPanel().clickInputAlertButton().sendAlertText(ConfProperties.getProperty("customAlertText"));
        Assertions.assertEquals("Hello " + ConfProperties.getProperty("customAlertText") + "! How are you today?", wayAutoAlerts.getAlertText());
    }
}