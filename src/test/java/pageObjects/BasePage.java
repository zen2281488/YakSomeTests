package pageObjects;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserInit;

public class BasePage {

    protected WebDriver browser;
    protected WebDriverWait wait;
    protected Actions action;
    protected JavascriptExecutor js;

    public BasePage(WebDriver browser) {
        this.browser = browser;
        this.wait = new WebDriverWait(browser, 15);
        this.action = new Actions(browser);
        this.js = (JavascriptExecutor) browser;
        PageFactory.initElements(browser, this);
    }

}
