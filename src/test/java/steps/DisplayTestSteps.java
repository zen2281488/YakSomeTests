package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pageObjects.WayIndexPage;
import tests.ui.BaseTestCucumber;
import utils.ConfProperties;

public class DisplayTestSteps extends BaseTestCucumber {
    private WayIndexPage wayIndexPage;

    @Before
    public void initialize() {
        setUp();
        wayIndexPage = new WayIndexPage(driver);
    }

    @After
    public void tearDown() {
        baseAfter();
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
        driver.get(ConfProperties.getProperty("mainTestPage"));
    }

    @And("User close the browser")
    public void userClosesTheBrowser() {
        driver.close();
    }
}