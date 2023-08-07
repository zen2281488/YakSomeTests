package UITests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfProperties;


@Epic("Тесты работоспособности элементов.")
public class JavaScriptExecutorTest extends BaseTest {

    @Feature("Тест с использованием JavaScriptExecutor.")
    @Description("Тест проверяет наличие вертикального скролла и отсутствие горизонтального, проверяет снимается ли фокус с поля ввода Username")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
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
