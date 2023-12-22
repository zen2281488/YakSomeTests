package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WayAutoDroppable extends BasePage {
    public WayAutoDroppable(WebDriver browser) {
        super(browser);
    }

    @FindBy(css = "#draggable")
    private WebElement draggableBox;
    @FindBy(css = "#droppable")
    private WebElement droppableBox;
    @FindBy(css = "#example-1-tab-1 > div > iframe")
    public WebElement iframe;
    @FindBy(css = "#droppable > p")
    private WebElement droppableBoxText;

    @Step("Переключение на нужный iframe и совершение Drag And Drop элемента")
    public String getDroppableBoxText() {
        browser.switchTo().frame(iframe);
        var text = droppableBoxText.getText();
        browser.switchTo().defaultContent();
        return text;
    }

    @Step("Drag and drop элемента: перетащить элемент {draggableBox} в элемент {droppableBox}")
    public void dragAndDropBox() {
        browser.switchTo().frame(iframe);
        action.dragAndDrop(draggableBox, droppableBox).build().perform();
        browser.switchTo().defaultContent();
    }
}