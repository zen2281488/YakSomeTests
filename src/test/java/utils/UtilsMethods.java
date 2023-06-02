package utils;

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

    private int activeElementCounter;


    public int getActiveSlideIndex(List<WebElement> elements) {
        IntStream.range(0, elements.size() - 1).forEach(i -> {
            if (elements.get(i).getAttribute("class").contains("swiper-slide-active")) {
                activeElementCounter = i;
            }
        });
        return activeElementCounter;
    }

}
