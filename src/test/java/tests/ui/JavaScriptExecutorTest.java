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
import pageObjects.SqlExIndexPage;
import utils.ConfProperties;


@Epic("Тесты работоспособности элементов.")
public class JavaScriptExecutorTest extends BaseTest {
    private SqlExIndexPage sqlExIndexPage;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        sqlExIndexPage = new SqlExIndexPage(driver);
    }

    @Feature("Тест с использованием JavaScriptExecutor.")
    @Description("Тест проверяет наличие вертикального скролла и отсутствие горизонтального, проверяет снимается ли фокус с поля ввода Username")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @Issue("UI-WAY2 №1")
    @DisplayName("Тестирование Фокуса и Наличие и отсутствие скролла")
    public void javaScriptExecutorTest() {
        driver.get(ConfProperties.getProperty("sqlExPath"));
        sqlExIndexPage.clickUsernameInput().removeFocusLoginInput();
        Assertions.assertTrue(sqlExIndexPage.isUsernameInputNotFocused(), "Фокус с поля Username не был снят");
        Assertions.assertTrue(sqlExIndexPage.isVerticalScrollPresent(), "Вертикального скролла нет на этой странице");
        Assertions.assertTrue(sqlExIndexPage.isHorizontalScrollNotPresent(), "На этой странице появился горизонтальный скролл");
    }
}
