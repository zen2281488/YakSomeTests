package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumTutorialIndex extends BasePage {
    @FindBy(css = "img.gravatar")
    private WebElement avatarImg;

    public SeleniumTutorialIndex(WebDriver browser) {
        super(browser);
    }

    @Step("Ожидание появления аватара")
    public boolean avatarImgExist() {
        try {
            wait.until(ExpectedConditions.visibilityOf(avatarImg));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
