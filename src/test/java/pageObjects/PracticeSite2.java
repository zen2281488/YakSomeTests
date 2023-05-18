package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeSite2 {
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


    public PracticeSite2(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public PracticeSite2 sendText(WebElement element, String text) {
        element.sendKeys(text);
        return this;
    }

    public PracticeSite2 buttonClick(WebElement element) {
        element.click();
        return this;
    }


}
