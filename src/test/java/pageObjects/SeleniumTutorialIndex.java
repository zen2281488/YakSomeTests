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

    @FindBy(css = ".user-signout")
    private WebElement logoutHref;


    public SeleniumTutorialIndex(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    @Step("Клик по Аватару.")
    public SeleniumTutorialIndex clickAvatarImg() {
        avatarImg.click();
        return this;
    }

    @Step("Клик на ссылку Logout.")
    public SeleniumTutorialIndex clickLogoutHref() {
        logoutHref.click();
        return this;
    }

    @Step("Аватар существует.")
    public SeleniumTutorialIndex waitAvatarImg() {
        wait.until(ExpectedConditions.visibilityOf(avatarImg)).isDisplayed();
        return this;
    }
}
