package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageObjects.WayAuthorization;
import utils.BrowserInit;
import utils.ConfProperties;

public class DataProviderAuthTestSteps {

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        BrowserInit.getWebdriver().get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("practice2login"));
    }

    @When("User logs in with username {string}, description {string} and password {string}")
    public void userLogsIn(String username, String description, String loginPassword) {
        new WayAuthorization(BrowserInit.getWebdriver()).sendUsername(username).sendPassword(loginPassword).sendUserDescription(description).clickSubmitButton();
    }

    @Then("Successful login message is displayed")
    public void successfulLoginMessageIsDisplayed() {
        new WayAuthorization(BrowserInit.getWebdriver()).waitSuccessLoginText();
    }
}