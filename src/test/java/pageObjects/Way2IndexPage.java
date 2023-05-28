package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Way2IndexPage {
    private final WebDriver browser;

    @FindBy(css = "div.ast-above-header-wrap")
    public WebElement headContacts;

    @FindBy(css = "iframe[data-test-id='ChatWidgetWindow-iframe']")
    public WebElement adBanner;

    @FindBy(css = ".elementor-slides-wrapper.swiper-container-horizontal")
    public WebElement sliderBanner;

    @FindBy(css = "#ast-hf-menu-1:nth-of-type(1)")
    public WebElement horisontalMenu;

    @FindBy(css = ".ast-sticky-shrunk.ast-header-sticked")
    public WebElement stickedHorisontalMenu;

    @FindBy(xpath = "//h1[contains(text(), 'Best Selenium Certification Course Online')][1]")
    public WebElement certificatesBlock;

    @FindBy(css = ".swiper-container-initialized.swiper-container-horizontal.swiper-container-autoheight")
    public WebElement sliderCources;

    @FindBy(css = "[data-elementor-type='footer']")
    public WebElement footer;

    @FindBy(css = ".dialog-lightbox-close-button")
    public WebElement bannerClose;

    @FindBy(css = ".swiper-wrapper.elementor-slides>.swiper-slide")
    public List<WebElement> banners;

    @FindBy(css = ".pp-slider-arrow.swiper-button-next")
    public WebElement coursesNextButton;

    @FindBy(css = ".swiper-container-autoheight>.swiper-wrapper>.swiper-slide")
    public List<WebElement> courses;
    @FindBy(xpath = "//span[contains(text(), 'Resources')][1]")
    public WebElement resourcesButton;

    @FindBy(xpath = "//span[contains(text(), 'Practice Site 1')][1]")
    public WebElement practiceSite1Button;


    public Way2IndexPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

}
