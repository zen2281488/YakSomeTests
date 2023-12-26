package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.Set;

public class WayAutoTabs extends BasePage {
    public WayAutoTabs(WebDriver browser) {
        super(browser);
    }

    @FindBy(css = "[id=example-1-tab-1] .freme_box .demo-frame")
    private WebElement tabNewWindowIframe;

    @FindBy(css = "[target='_blank']")
    private WebElement tabNewWindowHref;

    @Step("Фокус на айфрейм и клик по ссылке")
    public void clickTabNewWindowHref() {
        browser.switchTo().frame(tabNewWindowIframe);
        tabNewWindowHref.click();
        browser.switchTo().defaultContent();
    }

    @Step("Фокус на вторую вкладку и клик по ссылке")
    public void switchAndClickTab() {
        Set<String> handles = browser.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        iterator.next();
        String secondTab = iterator.next();
        browser.switchTo().window(secondTab);
        tabNewWindowHref.click();
    }

    @Step("Получаем количество вкладок")
    public int getTabsNumber() {
        Set<String> handles = browser.getWindowHandles();
        return handles.size();
    }

}