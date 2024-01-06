package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pageObjects.WayIndexPage;
import utils.ConfProperties;

public class DisplayTestSteps {

    private final WebDriverHooks webDriverHooks;

    public DisplayTestSteps() {
        this.webDriverHooks = new WebDriverHooks();
    }

    @Step("Слайдер курсов существует.")
    @Then("Slider of Courses displayed")
    public boolean sliderCourcesDisplayed() {
        return new WayIndexPage(webDriverHooks.getDriver()).sliderCourcesDisplayed();
    }

    @Step("Футер существует.")
    @Then("Footer should be displayed")
    public boolean footerDisplayed() {
        return new WayIndexPage(webDriverHooks.getDriver()).footerDisplayed();
    }

    @Step("Блок с сертификатами существует.")
    @Then("Certificates block should be displayed")
    public boolean certificatesBlockDisplayed() {
        return new WayIndexPage(WebDriverHooks.getDriver()).certificatesBlockDisplayed();
    }

    @Step("Блок с контактами существует.")
    @Then("Contacts block displayed")
    public boolean contactsDisplayed() {
        return new WayIndexPage(webDriverHooks.getDriver()).contactsDisplayed();
    }

    @Given("I am on the main page")
    public void openMainPage() {
        webDriverHooks.getDriver().get(ConfProperties.getProperty("mainTestPage"));
    }
}