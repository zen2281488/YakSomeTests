package pageObjects;

import org.openqa.selenium.WebElement;

public class Swiper {

    private final WebElement rootElement;

    public Swiper(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public boolean isActive() {
        return rootElement.getAttribute("class").contains("active");
    }

    public boolean isPrev() {
        return rootElement.getAttribute("class").contains("prev");
    }

    public boolean isNext() {
        return rootElement.getAttribute("class").contains("next");
    }
}
