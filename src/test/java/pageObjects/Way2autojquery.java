package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Way2autojquery {
    private final WebDriver browser;

    @FindBy(css = "body")
    public WebElement body;

    public Way2autojquery(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
}
