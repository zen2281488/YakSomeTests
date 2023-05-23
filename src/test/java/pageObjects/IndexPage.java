package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class IndexPage {
    private final WebDriver browser;
    @FindBy(css = "div.ast-above-header-wrap")
    public WebElement headContacts;
    @FindBy(css = "iframe[data-test-id='ChatWidgetWindow-iframe']")
    public WebElement adBanner;
    @FindBy(css = "#post-17 > div > div > section.elementor-section.elementor-top-section.elementor-element.elementor-element-53d02b5a.elementor-section-boxed.elementor-section-height-default.elementor-section-height-default.lazyloaded > div.elementor-container.elementor-column-gap-default > div > div > div > div > div > div")
    public WebElement sliderBanner;
    @FindBy(css = "#ast-hf-menu-1:nth-of-type(1)")
    public WebElement horisontalMenu;
    @FindBy(css = ".ast-sticky-shrunk.ast-header-sticked")
    public WebElement stickedHorisontalMenu;
    @FindBy(css = ".elementor-element-5b4952c1")
    public WebElement certificatesBlock;
    @FindBy(css = ".swiper-container-autoheight>div")
    public WebElement sliderCources;
    @FindBy(css = "[data-elementor-type='footer']")
    public WebElement footer;
    @FindBy(css = ".dialog-lightbox-close-button")
    public WebElement bannerClose;
    @FindBy(css = ".swiper-wrapper.elementor-slides>div.swiper-slide")
    public List<WebElement> banners;
    @FindBy(css = ".pp-slider-arrow.swiper-button-next")
    public WebElement coursesNextButton;
    @FindBy(css = ".swiper-container-autoheight>div>div")
    public List<WebElement> courses;

    @FindBy(css = "#menu-item-27617")
    public WebElement resourcesButton;
    @FindBy(css = "#menu-item-27618")
    public WebElement practiceSite1Button;

    public IndexPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public IndexPage displayedAssert(WebElement element) {
        WebDriverWait wait = new WebDriverWait(browser, 15);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed());
        return this;
    }

    public int getXCoordinate(WebElement element) {
        return element.getLocation().getX();
    }
}
