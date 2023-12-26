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

    @FindBy(css = "[id=example-1-tab-2] .freme_box .demo-frame")
    private WebElement inputAlertIframe;
    @FindBy(xpath = "//button[contains(text(),'de')]")
    private WebElement inputAlertButton;

    @FindBy(css = "#demo")
    private WebElement alertText;

    @Step("Переключение на нужный iframe и кликаем на кнопку вызова ввода текста в алерт")
    public void clickInputAlertButton() {
        browser.switchTo().frame(inputAlertIframe);
        inputAlertButton.click();
    }

    @Step("Переключаемся на панель INPUT ALERTS")
    public void clickInputAlertsPanel() {
        inputAlertPanel.click();
    }

    @Step("Отсылаем текст в алерт")
    public void sendAlertText(String text) {
        Alert alert = browser.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
        browser.switchTo().defaultContent();
    }

    @Step("Получаем текст из тега <p> фрейма")
    public String getAlertText() {
        browser.switchTo().frame(inputAlertIframe);
        var text = alertText.getText();
        browser.switchTo().defaultContent();
        return text;
    }
}