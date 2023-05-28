package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumTutorialIndex {
    private final WebDriver browser;
    @FindBy(css = "img.gravatar")

    public WebElement avatarImg;
    @FindBy(css = ".user-signout")

    public WebElement logoutHref;

    public SeleniumTutorialIndex(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
}
