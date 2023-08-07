package UITests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.ConfProperties;
import utils.CookieUtils;
import utils.TestWatcherPlugin;

@ExtendWith(TestWatcherPlugin.class)
@Epic("Тесты Авторизации.")
@Feature("Тест авторизации.")
public class CookieAuthTest extends BaseTest {
    private final String COOKIE_FILE_PATH = ConfProperties.getProperty("COOKIE_FILE_PATH");

    @Test
    @DisplayName("Авторизация и сохранение куков в файл,с последующим чтением из него")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест авторизации пользователя и сохранение куков в файл, чтение куков из файла при повторном тестировании")
    public void testLoginAndSaveCookiesToFile() {
        driver.get(ConfProperties.getProperty("sqlExPath"));
        if (!CookieUtils.cookieExist(COOKIE_FILE_PATH)) {
            sqlExIndexPage
                    .sendUsername(ConfProperties.getProperty("sqlExLogin"))
                    .sendPassword(ConfProperties.getProperty("sqlExPass"))
                    .clickSubmitButton();
            CookieUtils.saveCookiesToFile(driver.manage().getCookies(), COOKIE_FILE_PATH);
        } else {
            CookieUtils.addCookie(driver, CookieUtils.loadCookiesFromFile(COOKIE_FILE_PATH));
            driver.navigate().refresh();
        }
        Assertions.assertEquals(ConfProperties.getProperty("nickname"), sqlExIndexPage.getLoginText(), "Вход не был совершен.");
    }
}
