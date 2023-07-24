package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WayAutorisation extends BasePage {

    @FindBy(css = "#username")
    private WebElement usernameInput;

    @FindBy(css = "#password")
    private WebElement passInput;

    @FindBy(css = "#formly_1_input_username_0")
    private WebElement usernameDescriptionInput;

    @FindBy(css = "p:nth-child(2)")
    private WebElement successlogtext;
    @FindBy(css = ".alert.alert-danger")
    private WebElement errorlogtext;
    @FindBy(css = ".btn-danger")
    private WebElement loginButton;


    public WayAutorisation(WebDriver browser) {
        super(browser);
    }

    @Step("Отправка текста в поле Username.")
    public WayAutorisation sendUsername(String text) {
        usernameInput.sendKeys(text);
        return this;
    }

    @Step("Отправка текста в поле Password.")
    public WayAutorisation sendPassword(String text) {
        passInput.sendKeys(text);
        return this;
    }

    @Step("Отправка текста в поле User Description.")
    public WayAutorisation sendUserDescription(String text) {
        usernameDescriptionInput.sendKeys(text);
        return this;
    }

    @Step("Клик по кнопке Submit")
    public WayAutorisation clickSubmitButton() {
        loginButton.click();
        return this;
    }

    @Step("Текст о успешном входе в систему существует.")
    public boolean waitSuccessLoginText() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successlogtext));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Извлечение текста о входе в систему.")
    public String getSuccessLogText() {
        return successlogtext.getText();
    }

}
