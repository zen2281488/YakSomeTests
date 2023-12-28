package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Alert;

public class WayAutoAlerts extends BasePage {
    public WayAutoAlerts(WebDriver browser) {
        super(browser);
    }

    @FindBy(css = "[href='#example-1-tab-2']")
    private WebElement inputAlertPanel;

    @FindBy(css = "#example-1-tab-2 .demo-frame")
    private WebElement inputAlertIframe;

    @FindBy(css = "body > button")
    private WebElement inputAlertButton;

    @FindBy(id = "demo")
    private WebElement alertText;

    @Step("Переключение на нужный iframe и кликаем на кнопку вызова ввода текста в алерт")
    public WayAutoAlerts clickInputAlertButton() {
        browser.switchTo().frame(inputAlertIframe);
        inputAlertButton.click();
        return this;
    }

    @Step("Переключаемся на панель INPUT ALERTS")
    public WayAutoAlerts clickInputAlertsPanel() {
        inputAlertPanel.click();
        return this;
    }

    @Step("Отсылаем текст в алерт")
    public WayAutoAlerts sendAlertText(String text) {
        Alert alert = browser.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
        browser.switchTo().defaultContent();
        return this;
    }

    @Step("Получаем текст из тега <p> фрейма")
    public String getAlertText() {
        browser.switchTo().frame(inputAlertIframe);
        var text = alertText.getText();
        browser.switchTo().defaultContent();
        return text;
    }
}