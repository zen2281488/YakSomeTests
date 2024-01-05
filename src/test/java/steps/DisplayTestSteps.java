package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pageObjects.WayIndexPage;
import utils.ConfProperties;

public class DisplayTestSteps {

    private WayIndexPage wayIndexPage;

    private TestContext testContext;

    public DisplayTestSteps() {
        this.testContext = new TestContext();
        wayIndexPage = new WayIndexPage(testContext.getDriver());
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
        testContext.getDriver().get(ConfProperties.getProperty("mainTestPage"));
    }
}