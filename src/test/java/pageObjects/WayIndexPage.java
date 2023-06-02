package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WayIndexPage extends BasePage {
    @FindBy(css = "div.ast-above-header-wrap")
    private WebElement headContacts;
    @FindBy(css = "#elementor-popup-modal-26600 > div > div.dialog-message.dialog-lightbox-message")
    private WebElement adBanner;
    @FindBy(css = ".elementor-slides-wrapper.swiper-container-horizontal")
    private WebElement sliderBanner;
    @FindBy(css = "#ast-hf-menu-1:nth-of-type(1)")
    private WebElement horisontalMenu;
    @FindBy(css = ".ast-sticky-shrunk.ast-header-sticked")
    private WebElement stickedHorisontalMenu;
    @FindBy(xpath = "//h1[contains(text(), 'Best Selenium Certification Course Online')][1]")
    private WebElement certificatesBlock;
    @FindBy(css = ".swiper-container-initialized.swiper-container-horizontal.swiper-container-autoheight")
    private WebElement sliderCources;
    @FindBy(css = "[data-elementor-type='footer']")
    private WebElement footer;
    @FindBy(css = ".dialog-lightbox-close-button")
    private WebElement bannerClose;
    @FindBy(css = ".swiper-wrapper.elementor-slides>.swiper-slide")
    private List<WebElement> banners;
    @FindBy(css = ".pp-slider-arrow.swiper-button-next")
    private WebElement coursesNextButton;
    @FindBy(css = ".swiper-container-autoheight>.swiper-wrapper>.swiper-slide")
    private List<WebElement> courses;
    @FindBy(xpath = "//span[contains(text(), 'Resources')][1]")
    private WebElement resourcesButton;
    @FindBy(xpath = "//span[contains(text(), 'Practice Site 1')][1]")
    private WebElement practiceSite1Button;


    public WayIndexPage(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    @Step("Слайдер курсов существует.")
    public WayIndexPage waitSliderCources() {
        wait.until(ExpectedConditions.visibilityOf(sliderCources)).isDisplayed();
        return this;
    }

    @Step("Блок с сертификатами существует.")
    public WayIndexPage waitCertificatesBlock() {
        wait.until(ExpectedConditions.visibilityOf(certificatesBlock)).isDisplayed();
        return this;
    }

    @Step("Блок с контактами существует.")
    public WayIndexPage waitContacts() {
        wait.until(ExpectedConditions.visibilityOf(headContacts)).isDisplayed();
        return this;
    }

    @Step("Горизонтальное меню существует.")
    public WayIndexPage waitHorisontalMenu() {
        wait.until(ExpectedConditions.visibilityOf(horisontalMenu)).isDisplayed();
        return this;
    }

    @Step("Активация слайдера баннеров")
    public WayIndexPage sliderBannerActivate() {
        action.moveByOffset(30, 80).build().perform();
        return this;
    }

    @Step("Слайдер баннеров существует.")
    public WayIndexPage waitSliderBanner() {
        wait.until(ExpectedConditions.visibilityOf(sliderBanner)).isDisplayed();
        return this;
    }

    @Step("Рекламный баннер существует.")
    public WayIndexPage waitAdBanner() {
        wait.until(ExpectedConditions.visibilityOf(adBanner)).isDisplayed();
        return this;
    }

    @Step("Нажатие на кнопку закрытия рекламного баннера.")
    public WayIndexPage bannerCloseButtonClick() {
        bannerClose.click();
        return this;
    }

    @Step("Свайп слайдера баннеров.")
    public void slideSlider() {
        action.dragAndDropBy(sliderBanner, sliderBanner.getLocation().getX() - 500, sliderBanner.getLocation().getY()).perform();
    }

    @Step("Футер существует.")
    public WayIndexPage waitFooter() {
        wait.until(ExpectedConditions.visibilityOf(footer)).isDisplayed();
        return this;
    }

    @Step("Класс Active присутствует у слайдов после свайпа.")
    public WebElement activeSlide() {
        return banners.stream()
                .filter(element -> element.getAttribute("class").contains("swiper-slide-active"))
                .findFirst()
                .orElse(null);
    }

    @Step("Класс Active присутствует у слайдов после свайпа.")
    public WebElement nextSlide() {
        return banners.stream()
                .filter(element -> element.getAttribute("class").contains("swiper-slide-next"))
                .findFirst()
                .orElse(null);
    }

    @Step("Класс слайда содержит prev")
    public boolean slideClassContainsPrev(WebElement activeSlide) {
        return activeSlide.getAttribute("class").contains("prev");
    }

    @Step("Класс слайда содержит active")
    public boolean slideClassContainsActive(WebElement nextSlide) {
        return nextSlide.getAttribute("class").contains("active");
    }

    @Step("Класс Active присутствует у слайдов блока с курсами после свайпа.")
    public WebElement coursesActiveSlide() {
        return courses.stream()
                .filter(element -> element.getAttribute("class").contains("swiper-slide-active"))
                .findFirst()
                .orElse(null);
    }

    @Step("Класс Active присутствует у слайдов блока с курсами слайдов после свайпа.")
    public WebElement coursesNextSlide() {
        return courses.stream()
                .filter(element -> element.getAttribute("class").contains("swiper-slide-next"))
                .findFirst()
                .orElse(null);
    }

    @Step("Класс у слайда блока с курсами содержит prev")
    public boolean slideCoursesClassContainsPrev(WebElement activeSlide) {
        return activeSlide.getAttribute("class").contains("prev");
    }

    @Step("Класс у слайда блока с курсами содержит active")
    public boolean slideCoursesClassContainsActive(WebElement nextSlide) {
        return nextSlide.getAttribute("class").contains("active");
    }


    @Step("При прокрутке страницы Горизонтальное меню отображается.")
    public WayIndexPage waitStickHorisontalMenu() {
        js.executeScript("window.scrollBy(0,1000)");
        wait.until(ExpectedConditions.visibilityOf(stickedHorisontalMenu)).isDisplayed();
        return this;
    }


    @Step("Клик по кнопке свайпа слайдера с курсами вправо.")
    public WayIndexPage nextCourseButtonClick() {
        action.moveToElement(coursesNextButton).click().build().perform();
        return this;
    }


    @Step("Наведение мыши на выпадающий список Resources")
    public WayIndexPage triggerDropdown() {
        action.moveToElement(resourcesButton).build().perform();
        return this;
    }

    @Step("Клик по кнопке перехода на сайт Practice Site1 ")
    public WayIndexPage practiseSiteButtonClick() {
        practiceSite1Button.click();
        return this;
    }

}