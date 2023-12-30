package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Step("Существует ли атрибут Src в блоке с картинкой")
    public boolean isImageVisible() {
        wait.until(ExpectedConditions.visibilityOf(authImage));
        return true;
    }
}