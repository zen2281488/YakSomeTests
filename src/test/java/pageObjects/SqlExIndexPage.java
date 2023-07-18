package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SqlExIndexPage extends BasePage {

    @FindBy(css = "[name='login']")
    private WebElement usernameInput;

    @FindBy(css = "[name='psw']")
    private WebElement passwordInput;

    @FindBy(css = "[name='subm1']")
    private WebElement logInButton;
    @FindBy(css = ".none[href='/personal.php']")
    private WebElement loginString;

    public SqlExIndexPage(WebDriver browser) {
        super(browser);
    }

    @Step("Отправка текста в поле Login")
    public String getLoginText() {
        String login = loginString.getText();
        return login;
    }

    @Step("Отправка текста в поле Login")
    public SqlExIndexPage sendUsername(String text) {
        usernameInput.sendKeys(text);
        return this;
    }

    @Step("Отправка текста в поле Login")
    public SqlExIndexPage sendPassword(String text) {
        passwordInput.sendKeys(text);
        return this;
    }

    @Step("Клик по кнопке Submit")
    public SqlExIndexPage clickSubmitButton() {
        logInButton.click();
        return this;
    }

public boolean logged(){
    try {
        wait.until(ExpectedConditions.visibilityOf(loginString));
        return true;
    } catch (TimeoutException e) {
        return false;
    }

}
}
