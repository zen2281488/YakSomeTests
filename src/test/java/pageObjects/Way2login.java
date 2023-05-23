package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Way2login {
    private final WebDriver browser;
    @FindBy(css = "[href='/secure/673/identity/sign_up']")
    public WebElement signUpHref;

    @FindBy(css = "#user_name")
    public WebElement fullNameInput;

    @FindBy(css = "#user_email")
    public WebElement emailSignInInput;

    @FindBy(css = "#password")
    public WebElement passwordInput;

    @FindBy(css = "[name='commit']")
    public WebElement commitButton;

    @FindBy(css = "#email")
    public WebElement emailLoginInput;

    public Way2login(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
}
