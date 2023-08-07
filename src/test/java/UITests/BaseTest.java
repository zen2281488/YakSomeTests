package UITests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pageObjects.*;
import utils.BrowserInit;

public class BaseTest {
    protected SqlExIndexPage sqlExIndexPage;
    protected WebDriver driver;
    protected WayAutorisation wayAutorisation;
    protected WayLogin waylogin;
    protected SeleniumTutorialIndex seleniumTutorialIndex;
    protected WayIndexPage wayIndexPage;
    protected WayAutojquery wayAutojquery;

    @AfterEach
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }

    @BeforeEach
    public void before() {
        driver = BrowserInit.getWebdriver();
        sqlExIndexPage = new SqlExIndexPage(driver);
        wayAutorisation = new WayAutorisation(driver);
        waylogin = new WayLogin(driver);
        seleniumTutorialIndex = new SeleniumTutorialIndex(driver);
        wayIndexPage = new WayIndexPage(driver);
        wayAutojquery = new WayAutojquery(driver);
    }
}
