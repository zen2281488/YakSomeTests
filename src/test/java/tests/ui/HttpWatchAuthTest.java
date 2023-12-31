package tests.ui;

import io.github.artsok.RepeatedIfExceptionsTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.HttpWatchAuth;
import utils.ConfProperties;

@Epic("U13. Basic Auth")
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
        driver.get("https://" + ConfProperties.getProperty("httpwatchLogin")
                + ":" + ConfProperties.getProperty("httpwatchPassword") + "@"
                + ConfProperties.getProperty("mainAuthTestUrl")
                + ConfProperties.getProperty("authTestPage"));
        httpWatchAuth.clickDisplayImageButton().isImageVisible();
    }
}