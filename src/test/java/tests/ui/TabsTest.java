package tests.ui;


import io.github.artsok.RepeatedIfExceptionsTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.WayAutoTabs;
import utils.ConfProperties;

@Epic("U11. Tabs")
public class TabsTest extends BaseTest {
    private WayAutoTabs wayAutoTabs;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        wayAutoTabs = new WayAutoTabs(driver);
    }

    @RepeatedIfExceptionsTest(repeats = 2)
    @DisplayName("Тест вкладок")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест количества вкладок после совершения действий на странице, тест работоспособности ссылки открывающей вкладку")
    public void testTabs() {
        driver.get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("wayAutomationTabs"));
        wayAutoTabs.clickTabNewWindowHref().switchAndClickTab();
        Assertions.assertEquals(3, wayAutoTabs.getTabsNumber());
    }
}