package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WayAuthorization extends BasePage {

    @FindBy(css = "#username")
    private WebElement usernameInput;

    @FindBy(css = "#password")
    private WebElement passInput;

    @FindBy(id = "formly_1_input_username_0")
    private WebElement usernameDescriptionInput;

    @FindBy(css = "p:nth-child(2)")
    private WebElement successlogtext;
    @FindBy(css = ".alert.alert-danger")
    private WebElement errorlogtext;
    @FindBy(css = ".btn-danger")
    private WebElement loginButton;

    public WayAuthorization(WebDriver browser) {
        super(browser);
    }

    @Step("Отправка текста в поле Username.")
    public WayAuthorization sendUsername(String text) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(text);
        return this;
    }

    @Step("Отправка текста в поле Password.")
    public WayAuthorization sendPassword(String text) {
        wait.until(ExpectedConditions.visibilityOf(passInput));
        passInput.clear();
        passInput.sendKeys(text);
        return this;
    }

    @Step("Отправка текста в поле User Description.")
    public WayAuthorization sendUserDescription(String text) {
        wait.until(ExpectedConditions.visibilityOf(usernameDescriptionInput));
        usernameDescriptionInput.clear();
        usernameDescriptionInput.sendKeys(text);
        return this;
    }

    @Step("Клик по кнопке Submit")
    public WayAuthorization clickSubmitButton() {
        loginButton.click();
        return this;
    }

    @Step("Текст о успешном входе в систему существует.")
    public void waitSuccessLoginText() {
           wait.until(ExpectedConditions.visibilityOf(successlogtext));
    }

    @Step("Извлечение текста о входе в систему.")
    public String getSuccessLogText() {
        return successlogtext.getText();
    }

}
