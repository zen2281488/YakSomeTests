package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageObjects.WayAutorisation;
import utils.ConfProperties;

public class DataProviderAuthTestSteps {

    private final WebDriverHooks webDriverHooks;

    public DataProviderAuthTestSteps() {
        this.webDriverHooks = new WebDriverHooks();
    }

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        webDriverHooks.getDriver().get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("practice2login"));
    }

    @When("User logs in with username {string}, description {string} and password {string}")
    public void userLogsIn(String username, String description, String loginPassword) {
        new WayAutorisation(webDriverHooks.getDriver()).sendUsername(username).sendPassword(loginPassword).sendUserDescription(description).clickSubmitButton();
    }

    @Then("Successful login message is displayed")
    public void successfulLoginMessageIsDisplayed() {
        new WayAutorisation(webDriverHooks.getDriver()).waitSuccessLoginText();
    }
}