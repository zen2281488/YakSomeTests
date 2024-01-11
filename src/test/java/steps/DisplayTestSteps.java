package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pageObjects.WayIndexPage;
import utils.BrowserInit;
import utils.ConfProperties;


public class DisplayTestSteps {

    @Step("Слайдер курсов существует.")
    @Then("Slider of Courses displayed")
    public boolean sliderCourcesDisplayed() {
        return new WayIndexPage(BrowserInit.getWebdriver()).sliderCourcesDisplayed();
    }

    @Step("Футер существует.")
    @Then("Footer should be displayed")
    public boolean footerDisplayed() {
        return new WayIndexPage(BrowserInit.getWebdriver()).footerDisplayed();
    }

    @Step("Блок с сертификатами существует.")
    @Then("Certificates block should be displayed")
    public boolean certificatesBlockDisplayed() {
        return new WayIndexPage(BrowserInit.getWebdriver()).certificatesBlockDisplayed();
    }

    @Step("Блок с контактами существует.")
    @Then("Contacts block displayed")
    public boolean contactsDisplayed() {
        return new WayIndexPage(BrowserInit.getWebdriver()).contactsDisplayed();
    }

    @Given("I am on the main page")
    public void openMainPage() {
        BrowserInit.getWebdriver().get(ConfProperties.getProperty("mainTestPage"));
    }
}