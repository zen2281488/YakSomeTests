package UITests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pageObjects.SqlExIndexPage;
import utils.BrowserInit;
import utils.ConfProperties;
import utils.CookieUtils;
import utils.TestWatcherPlugin;

import java.util.Set;

@ExtendWith(TestWatcherPlugin.class)
@Epic("Тесты Авторизации.")
@Feature("Тест авторизации.")
public class CookieAuthTest {
    private static final String COOKIE_FILE_PATH = "cookies.txt";
    private static WebDriver driver;
    private static SqlExIndexPage sqlExIndexPage;

    @BeforeAll
    public static void setupDriver() {
        driver = BrowserInit.getWebdriver();
        sqlExIndexPage = new SqlExIndexPage(driver);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Авторизация и сохранение куков в файл")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест авторизации пользователя и сохранение куков в файл")
    public void testLoginAndSaveCookiesToFile() {
        driver.get("https://www.sql-ex.ru/");
        if (!CookieUtils.cookieExist(COOKIE_FILE_PATH)) {
            sqlExIndexPage
                    .sendUsername(ConfProperties.getProperty("sqlExLogin"))
                    .sendPassword(ConfProperties.getProperty("sqlExPass"))
                    .clickSubmitButton();
            Set<Cookie> cookies = driver.manage().getCookies();
            CookieUtils.saveCookiesToFile(cookies, COOKIE_FILE_PATH);
        } else {
            Set<Cookie> cookiess = CookieUtils.loadCookiesFromFile(COOKIE_FILE_PATH);
            for (Cookie cookie : cookiess) {
                driver.manage().addCookie(cookie);
            }
            driver.navigate().refresh();
        }
        Assertions.assertEquals(ConfProperties.getProperty("nickname"), sqlExIndexPage.getLoginText());

    }

    public WebDriver getDriver() {
        return driver;
    }
}
