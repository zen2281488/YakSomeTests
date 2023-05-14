package cases;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.IntStream;

public class UtilsMethods {

    private final WebDriver browser;
    private int activeElementCounter;
    public UtilsMethods(WebDriver browser) {
        this.browser = browser;
    }

public int getActiveSlideIndex(List<WebElement> elements) {
    IntStream.range(0, elements.size()-1).forEach(i -> {
        if (elements.get(i).getAttribute("class").contains("swiper-slide-active")) {
            activeElementCounter = i;
        }
    });
    return activeElementCounter;
}

    public UtilsMethods assertSliderBeforeActivity(List<WebElement> elements,int index) {
        Assert.assertTrue(elements.get(index-1).getAttribute("class").contains("swiper-slide-prev"));
        Assert.assertTrue(elements.get(index).getAttribute("class").contains("swiper-slide-active"));
        Assert.assertTrue(elements.get(index).isDisplayed());
        Assert.assertTrue(elements.get(index+1).getAttribute("class").contains("swiper-slide-next"));
        return this;
    }

    public UtilsMethods assertSliderAfterActivity(List<WebElement> elements, int index) {
        Assert.assertTrue(elements.get(index).getAttribute("class").contains("swiper-slide-prev"));
        Assert.assertTrue(elements.get(index+1).getAttribute("class").contains("swiper-slide-active"));
        Assert.assertTrue(elements.get(index+1).isDisplayed());
        Assert.assertTrue(elements.get(index+2).getAttribute("class").contains("swiper-slide-next"));
        return this;
    }
}
