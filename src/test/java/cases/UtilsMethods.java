package cases;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.IntStream;

public class UtilsMethods {

    private final WebDriver browser;
    private int activeElementCounter;
    public Actions action;
    public WebDriverWait wait;
    public JavascriptExecutor js;


    public UtilsMethods(WebDriver browser) {
        this.browser = browser;
        action = new Actions(browser);
        wait = new WebDriverWait(browser, 15);
        js = (JavascriptExecutor) browser;
    }
    public UtilsMethods  sliderBannerActivate() {
        action.moveByOffset(30, 80).build().perform();
        return this;
    }

    public UtilsMethods horisontalMenuAssertion(WebDriver browser, WebElement stickedHorisontalMenu) {
        js.executeScript("window.scrollBy(0,1000)");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(stickedHorisontalMenu)).isDisplayed());
        return this;
    }

    public int getActiveSlideIndex(List<WebElement> elements) {
        IntStream.range(0, elements.size() - 1).forEach(i -> {
            if (elements.get(i).getAttribute("class").contains("swiper-slide-active")) {
                activeElementCounter = i;
            }
        });
        return activeElementCounter;
    }
@Step("Проверка наличия классов Prev,Active,Next у слайдов до свайпа.")
    public UtilsMethods assertSliderBeforeActivity(List<WebElement> elements, int index) {
        Assert.assertTrue(elements.get(index - 1).getAttribute("class").contains("swiper-slide-prev"));
        Assert.assertTrue(elements.get(index).getAttribute("class").contains("swiper-slide-active"));
        Assert.assertTrue(elements.get(index).isDisplayed());
        Assert.assertTrue(elements.get(index + 1).getAttribute("class").contains("swiper-slide-next"));
        return this;
    }

    @Step("Проверка наличия классов Prev,Active,Next у слайдов после свайпа.")
    public UtilsMethods assertSliderAfterActivity(List<WebElement> elements, int index) {
        Assert.assertTrue(elements.get(index).getAttribute("class").contains("swiper-slide-prev"));
        Assert.assertTrue(elements.get(index + 1).getAttribute("class").contains("swiper-slide-active"));
        Assert.assertTrue(elements.get(index + 1).isDisplayed());
        Assert.assertTrue(elements.get(index + 2).getAttribute("class").contains("swiper-slide-next"));
        return this;
    }
    @Step("Вставка текста в элемент {element}")
    public UtilsMethods sendText(WebElement element, String text) {
        element.sendKeys(text);
        return this;
    }

    @Step("Клик элемента {element}")
    public UtilsMethods elementClick(WebElement element) {
        element.click();
        return this;
    }

    @Step("Проверка существования элемента {element}")
    public UtilsMethods existAssert(WebElement element) {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed());
        return this;
    }

    @Step("Свайп элемента {element}")
    public UtilsMethods slideSlider(WebElement element, int x, int y){
        action.dragAndDropBy(element, x - 500, y).perform();
        return this;
    }

    @Step("Клик по элементу {element}")
    public UtilsMethods actionClick(WebElement element){
        action.moveToElement(element).click().build().perform();
        return this;
    }

    @Step("Наведение мыши на выпадающий список {element}")
    public UtilsMethods triggerDropdown(WebElement element){
        action.moveToElement(element).build().perform();
        return this;
    }
    @Step("Получаем текст элемента {element}")
    public String getText(WebElement element){
        return element.getText();
    }

    public int getXCoordinate(WebElement element) {
        return element.getLocation().getX();
    }
}
