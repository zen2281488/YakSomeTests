package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class WayAutoTabs extends BasePage {
    public WayAutoTabs(WebDriver browser) {
        super(browser);
    }

    @FindBy(css = "#example-1-tab-1 .demo-frame")
    private WebElement tabNewWindowIframe;

    @FindBy(css = "[target='_blank']")
    private WebElement tabNewWindowHref;

    @Step("Фокус на айфрейм и клик по ссылке")
    public WayAutoTabs clickTabNewWindowHref() {
        browser.switchTo().frame(tabNewWindowIframe);
        tabNewWindowHref.click();
        browser.switchTo().defaultContent();
        return this;
    }

    @Step("Фокус на вторую вкладку и клик по ссылке")
    public WayAutoTabs switchAndClickTab() {
        String[] handles = browser.getWindowHandles().toArray(new String[0]);
        browser.switchTo().window(handles[handles.length - 1]);
        tabNewWindowHref.click();
        return this;
    }

    @Step("Получаем количество вкладок")
    public int getTabsNumber() {
        Set<String> handles = browser.getWindowHandles();
        return handles.size();
    }
}