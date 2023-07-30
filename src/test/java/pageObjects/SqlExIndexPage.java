package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        return loginString.getText();
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

    @Step("Убираем фокус с поля ввода")
    public SqlExIndexPage removeFocusLoginInput() {
        js.executeScript("arguments[0].blur();", usernameInput);
        return this;
    }

    @Step("Клик по полю Username")
    public SqlExIndexPage clickUsernameInput() {
        usernameInput.click();
        return this;
    }

    @Step("Поле Username не в фокусе")
    public boolean isUsernameInputNotFocused() {
        return !usernameInput.equals(js.executeScript("return document.activeElement;"));
    }

    @Step("Вертикальный скролл присутствует")
    public boolean isVerticalScrollPresent() {
        return (boolean) js.executeScript("return document.documentElement.scrollHeight > document.documentElement.clientHeight;");
    }

    @Step("Горизонтальный скролл отсутствует")
    public boolean isHorizontalScrollNotPresent() {
        return !(boolean) js.executeScript("return document.documentElement.scrollWidth > document.documentElement.clientWidth;");
    }
}
