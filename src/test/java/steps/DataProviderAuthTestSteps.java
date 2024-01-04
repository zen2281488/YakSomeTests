package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import pageObjects.WayAutorisation;
import tests.ui.BaseTestCucumber;
import utils.ConfProperties;

public class DataProviderAuthTestSteps extends BaseTestCucumber {

    private WayAutorisation wayAutorisation;

    @Before
    public void initialize() {
        setUp();
        wayAutorisation = new WayAutorisation(driver);
    }

    @After
    public void tearDown() {
        baseAfter();
    }

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        driver.get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("practice2login"));
    }

    @When("User logs in with username {string}, description {string} and password {string}")
    public void userLogsIn(String username, String description, String loginPassword) {
        wayAutorisation.sendUsername(username).sendPassword(loginPassword).sendUserDescription(description).clickSubmitButton();
    }

    @Then("Successful login message is displayed")
    public void successfulLoginMessageIsDisplayed() {
        wayAutorisation.waitSuccessLoginText();
    }

    @And("User closes the browser")
    public void userCloseTheBrowser() {
        driver.close();
    }
}