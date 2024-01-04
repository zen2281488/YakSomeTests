package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.WayAutorisation;
import utils.BrowserInit;
import utils.ConfProperties;

import java.util.concurrent.TimeUnit;

public class DataProviderAuthTestSteps {

    private WayAutorisation wayAutorisation;

    private WebDriver browser;

    @Before
    public void setUp() {
        browser = BrowserInit.getWebdriverSelenoid(ConfProperties.getProperty("browserName"), ConfProperties.getProperty("browserMode"));
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().window().setSize(new Dimension(1920, 1080));
        wayAutorisation = new WayAutorisation(browser);
        PageFactory.initElements(browser, this);
    }

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        browser.get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("practice2login"));
    }

    @When("User logs in with username {string}, description {string} and password {string}")
    public void userLogsIn(String username, String description, String loginPassword) {
        wayAutorisation.sendUsername(username)
                .sendPassword(loginPassword)
                .sendUserDescription(description)
                .clickSubmitButton();
    }

    @Then("Successful login message is displayed")
    public void successfulLoginMessageIsDisplayed() {
        Assertions.assertTrue(wayAutorisation.waitSuccessLoginText(), "Авторизация не прошла. Проверьте корректность введенных данных.");
    }

    @And("User closes the browser")
    public void userCloseTheBrowser() {
        browser.close();
    }
}