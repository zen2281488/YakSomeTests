package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pageObjects.WayIndexPage;
import utils.ConfProperties;

import static steps.WebDriverHooks.getDriver;

public class DisplayTestSteps {

    @Step("Слайдер курсов существует.")
    @Then("Slider of Courses displayed")
    public boolean sliderCourcesDisplayed() {
        return new WayIndexPage(getDriver()).sliderCourcesDisplayed();
    }

    @Step("Футер существует.")
    @Then("Footer should be displayed")
    public boolean footerDisplayed() {
        return new WayIndexPage(getDriver()).footerDisplayed();
    }

    @Step("Блок с сертификатами существует.")
    @Then("Certificates block should be displayed")
    public boolean certificatesBlockDisplayed() {
        return new WayIndexPage(getDriver()).certificatesBlockDisplayed();
    }

    @Step("Блок с контактами существует.")
    @Then("Contacts block displayed")
    public boolean contactsDisplayed() {
        return new WayIndexPage(getDriver()).contactsDisplayed();
    }

    @Given("I am on the main page")
    public void openMainPage() {
        getDriver().get(ConfProperties.getProperty("mainTestPage"));
    }
}