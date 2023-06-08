package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfProperties;

public class WayLogin extends BasePage {
    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "[name='commit']")
    private WebElement commitButton;

    @FindBy(css = "#email")
    private WebElement emailLoginInput;

    @FindBy(css = ".toast-wrapper")
    private WebElement exeptText;

    public WayLogin(WebDriver browser) {
        super(browser);
    }

    @Step("Клик по кнопке Commit")
    public WayLogin clickCommit() {
        commitButton.click();
        return this;
    }

    @Step("Отправка текста в поле Email.")
    public WayLogin loginSendEmail(String text) {
        emailLoginInput.sendKeys(text);
        return this;
    }

    @Step("Отправка текста в поле Password.")
    public WayLogin loginSendPassword(String text) {
        passwordInput.sendKeys(text);
        return this;
    }

    @Step("Сообщения о ошибке не появилось.")
    public boolean exeptText() {
        return exeptText.isDisplayed();
    }
}
