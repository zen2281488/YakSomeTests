package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WayAutojquery {
    private final WebDriver browser;

    @FindBy(css = "body")
    private WebElement body;

    private WebDriverWait wait;

    public WayAutojquery(WebDriver browser, WebDriverWait wait) {
        this.browser = browser;
        this.wait = wait;
        PageFactory.initElements(browser, this);
    }

    @Step("Body существует.")
    public boolean bodyExistAssert() {
        return wait.until(ExpectedConditions.visibilityOf(body)).isDisplayed();
    }
}
