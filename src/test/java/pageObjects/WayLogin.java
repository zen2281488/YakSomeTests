package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfProperties;

public class WayLogin extends BasePage {
    @FindBy(css = "[href='/secure/673/identity/sign_up']")
    private WebElement signUpHref;

    @FindBy(css = "#user_name")
    private WebElement fullNameInput;

    @FindBy(css = "#user_email")
    private WebElement emailSignInInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "[name='commit']")
    private WebElement commitButton;

    @FindBy(css = "#email")
    private WebElement emailLoginInput;

    public WayLogin(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    @Step("Отправка текста в поле Full Name.")
    public WayLogin sendFullName() {
        fullNameInput.sendKeys(ConfProperties.getProperty("way2RegFullName"));
        return this;
    }

    @Step("Отправка текста в поле Email.")
    public WayLogin sendEmail() {
        emailSignInInput.sendKeys(ConfProperties.getProperty("way2RegEmail"));
        return this;
    }

    @Step("Отправка текста в поле Password.")
    public WayLogin sendPassword() {
        passwordInput.sendKeys(ConfProperties.getProperty("way2RegPassword"));
        return this;
    }

    @Step("Клик по гиперссылке Sign Up")
    public WayLogin signUpHrefClick() {
        signUpHref.click();
        return this;
    }

    @Step("Клик по кнопке Commit")
    public WayLogin clickCommit() {
        commitButton.click();
        return this;
    }

    @Step("Отправка текста в поле Email.")
    public WayLogin loginSendEmail() {
        emailLoginInput.sendKeys(ConfProperties.getProperty("way2LogEmail"));
        return this;
    }

    @Step("Отправка текста в поле Password.")
    public WayLogin loginSendPassword() {
        passwordInput.sendKeys(ConfProperties.getProperty("way2LogPassword"));
        return this;
    }


}
