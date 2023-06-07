package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumTutorialIndex extends BasePage {
    @FindBy(css = "img.gravatar")
    private WebElement avatarImg;

    public SeleniumTutorialIndex(WebDriver browser) {
        super(browser);
    }

    @Step("Аватар существует.")
    public boolean waitAvatarImg() {
        return wait.until(ExpectedConditions.visibilityOf(avatarImg)).isDisplayed();
    }
}
