package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageObjects.WayAutorisation;
import utils.ConfProperties;

import static steps.WebDriverHooks.getDriver;

public class DataProviderAuthTestSteps {

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        getDriver().get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("practice2login"));
    }

    @When("User logs in with username {string}, description {string} and password {string}")
    public void userLogsIn(String username, String description, String loginPassword) {
        new WayAutorisation(getDriver()).sendUsername(username).sendPassword(loginPassword).sendUserDescription(description).clickSubmitButton();
    }

    @Then("Successful login message is displayed")
    public void successfulLoginMessageIsDisplayed() {
        new WayAutorisation(getDriver()).waitSuccessLoginText();
    }
}