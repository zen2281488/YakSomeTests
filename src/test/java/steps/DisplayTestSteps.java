package steps;


import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.WayIndexPage;
import utils.BrowserInit;
import utils.ConfProperties;

import java.util.concurrent.TimeUnit;

public class DisplayTestSteps {
    private WebDriver browser;
    protected WebDriverWait wait;
    private WayIndexPage wayIndexPage;

    @Before
    public void setUp() {
        browser = BrowserInit.getWebdriverSelenoid(ConfProperties.getProperty("browserName"), ConfProperties.getProperty("browserMode"));
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().window().setSize(new Dimension(1920, 1080));
        wayIndexPage = new WayIndexPage(browser);
    }

    @Step("Слайдер курсов существует.")
    @Then("Slider of Courses displayed")
    public boolean sliderCourcesDisplayed() {
        return wayIndexPage.sliderCourcesDisplayed();
    }

    @Step("Футер существует.")
    @Then("Footer should be displayed")
    public boolean footerDisplayed() {
        return wayIndexPage.footerDisplayed();
    }

    @Step("Блок с сертификатами существует.")
    @Then("Certificates block should be displayed")
    public boolean certificatesBlockDisplayed() {
        return wayIndexPage.certificatesBlockDisplayed();
    }

    @Step("Блок с контактами существует.")
    @Then("Contacts block displayed")
    public boolean contactsDisplayed() {
        return wayIndexPage.contactsDisplayed();
    }

    @Given("I am on the main page")
    public void openMainPage() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
    }

    @And("User close the browser")
    public void userClosesTheBrowser() {
        browser.close();
    }
}