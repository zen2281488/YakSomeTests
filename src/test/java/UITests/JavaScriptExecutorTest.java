package UITests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pageObjects.SqlExIndexPage;
import utils.BrowserInit;
import utils.ConfProperties;

@Epic("Тесты работоспособности элементов.")

public class JavaScriptExecutorTest {
    private static WebDriver browser;
    private SqlExIndexPage sqlExIndexPage;

    @AfterAll
    public static void tearDown() {
        browser.quit();
    }

    @BeforeEach
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        sqlExIndexPage = new SqlExIndexPage(browser);
    }

    @Feature("Тест с использованием JavaScriptExecutor.")
    @Description("Тест проверяет наличие вертикального скролла и отсутствие горизонтального, проверяет снимается ли фокус с поля ввода Username")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Тестирование Фокуса и Наличие и отсутствие скролла")
    public void focusRemoveTest() {
        browser.get(ConfProperties.getProperty("sqlExPath"));
        sqlExIndexPage.clickUsernameInput().removeFocusLoginInput();
        Assertions.assertTrue(sqlExIndexPage.isUsernameInputNotFocused(), "Фокус с поля Username не был снят");
        Assertions.assertTrue(sqlExIndexPage.isVerticalScrollPresent(), "Вертикального скролла нет на этой странице");
        Assertions.assertTrue(sqlExIndexPage.isHorizontalScrollNotPresent(), "На этой странице появился горизонтальный скролл");

    }

}
