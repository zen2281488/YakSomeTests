package tests.ui;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.HttpWatchAuth;
import utils.ConfProperties;

@Epic("U11. Tabs")
public class HttpWatchAuthTest extends BaseTest {
    private HttpWatchAuth httpWatchAuth;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        httpWatchAuth = new HttpWatchAuth(driver);
    }

    @RepeatedIfExceptionsTest(repeats = 2)
    @DisplayName("Тест авторизации")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тестирование авторизации, наличия картинки при ее успехе")
    public void testBasicAuth() {
        driver.get(ConfProperties.getProperty("mainAuthTestUrl") + ConfProperties.getProperty("authTestPage"));
        httpWatchAuth.clickDisplayImageButton().sendLogPass();
        Assertions.assertFalse(httpWatchAuth.isSrcAttributePresent());
    }
}