package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WayAutojquery extends BasePage {
    @FindBy(css = "body")
    private WebElement body;

    public WayAutojquery(WebDriver browser) {
        super(browser);
    }

    @Step("Body существует.")
    public boolean waitBody() {
        return wait.until(ExpectedConditions.visibilityOf(body)).isDisplayed();
    }
}
