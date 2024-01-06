package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pageObjects.WayIndexPage;
import utils.ConfProperties;

public class DisplayTestSteps {

    private TestContext testContext;

    public DisplayTestSteps() {
        this.testContext = new TestContext();
    }

    @Step("Слайдер курсов существует.")
    @Then("Slider of Courses displayed")
    public boolean sliderCourcesDisplayed() {
        return new WayIndexPage(testContext.getDriver()).sliderCourcesDisplayed();
    }

    @Step("Футер существует.")
    @Then("Footer should be displayed")
    public boolean footerDisplayed() {
        return new WayIndexPage(testContext.getDriver()).footerDisplayed();
    }

    @Step("Блок с сертификатами существует.")
    @Then("Certificates block should be displayed")
    public boolean certificatesBlockDisplayed() {
        return new WayIndexPage(testContext.getDriver()).certificatesBlockDisplayed();
    }

    @Step("Блок с контактами существует.")
    @Then("Contacts block displayed")
    public boolean contactsDisplayed() {
        return new WayIndexPage(testContext.getDriver()).contactsDisplayed();
    }

    @Given("I am on the main page")
    public void openMainPage() {
        testContext.getDriver().get(ConfProperties.getProperty("mainTestPage"));
    }
}