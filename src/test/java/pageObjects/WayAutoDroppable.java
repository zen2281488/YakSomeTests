package pageObjects;

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


    public String getDroppableBoxText() {
        return droppableBoxText.getText();
    }

    public void dragAndDropBox(){
        action.dragAndDrop(draggableBox, droppableBox).build().perform();
    }

}
