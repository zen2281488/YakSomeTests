package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTutorialIndex {
    private final WebDriver browser;

    @FindBy(css = "img.gravatar")
    private WebElement avatarImg;

    @FindBy(css = ".user-signout")
    private WebElement logoutHref;

    private WebDriverWait wait;


    public SeleniumTutorialIndex(WebDriver browser, WebDriverWait wait) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        this.wait = wait;
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
    public boolean avatarImgExist() {
        return wait.until(ExpectedConditions.visibilityOf(avatarImg)).isDisplayed();
    }
}
