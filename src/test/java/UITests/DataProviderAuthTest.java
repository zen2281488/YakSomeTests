package UITests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pageObjects.SeleniumTutorialIndex;
import pageObjects.WayAutorisation;
import pageObjects.WayLogin;
import utils.BrowserInit;
import utils.ConfProperties;

public class DataProviderAuthTest {
    private WebDriver browser;
    private WayAutorisation wayAutorisation;
    private WayLogin waylogin;
    private SeleniumTutorialIndex seleniumTutorialIndex;

    @Test(dataProvider = "loginData",dataProviderClass = utils.DataProviderUserData.class)
    public void testLogin(String username, String password) {
        browser = BrowserInit.getWebdriver();
        wayAutorisation = new WayAutorisation(browser);
        browser.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername(username).sendPassword(password).sendUserDescription(password).clickSubmitButton();
        Assert.assertTrue("Авторизация не прошла. Проверьте корректность введенных данных.", wayAutorisation.waitSuccessLoginText());
        browser.quit();
    }
}