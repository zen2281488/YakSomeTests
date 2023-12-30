package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfProperties;

public class HttpWatchAuth extends BasePage {
    public HttpWatchAuth(WebDriver browser) {
        super(browser);
    }

    @FindBy(id = "displayImage")
    private WebElement displayImageButton;

    @FindBy(id = "downloadImg")
    private WebElement authImage;

    @Step("Клик по кнопке Display Image")
    public HttpWatchAuth clickDisplayImageButton() {
        displayImageButton.click();
        return this;
    }

    @Step("Отправляем логин и пароль в поле авторизации")
    public HttpWatchAuth sendLogPass() {
        browser.get("https://" + ConfProperties.getProperty("httpwatchLogin") + ":" + ConfProperties.getProperty("httpwatchPassword") + "@www.httpwatch.com/httpgallery/authentication/#showExample10");
        return this;
    }

    @Step("Существует ли атрибут Src в блоке с картинкой")
    public boolean isSrcAttributePresent() {
        return authImage.getAttribute("src") != null;
    }
}