package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class wayAutorisation {
    private final WebDriver browser;

    @FindBy(css = "#username")
    public WebElement usernameInput;

    @FindBy(css = "#password")
    public WebElement passInput;

    @FindBy(css = "#formly_1_input_username_0")
    public WebElement usernameDescriptionInput;

    @FindBy(css = "p:nth-child(2)")
    public WebElement successlogtext;

    @FindBy(css = ".btn-danger")
    public WebElement loginButton;


    public wayAutorisation(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }



}
