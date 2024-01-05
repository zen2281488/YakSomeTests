package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageObjects.WayAutorisation;
import utils.ConfProperties;

public class DataProviderAuthTestSteps {

    private WayAutorisation wayAutorisation;

    private TestContext testContext;

    public DataProviderAuthTestSteps() {
        this.testContext = new TestContext();
        wayAutorisation = new WayAutorisation(testContext.getDriver());
    }

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        testContext.getDriver().get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("practice2login"));
    }

    @When("User logs in with username {string}, description {string} and password {string}")
    public void userLogsIn(String username, String description, String loginPassword) {
        wayAutorisation.sendUsername(username).sendPassword(loginPassword).sendUserDescription(description).clickSubmitButton();
    }

    @Then("Successful login message is displayed")
    public void successfulLoginMessageIsDisplayed() {
        wayAutorisation.waitSuccessLoginText();
    }
}