package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WayIndexPage {
    private final WebDriver browser;
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
    private Actions action;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public WayIndexPage(WebDriver browser, Actions action, WebDriverWait wait, JavascriptExecutor js) {
        this.browser = browser;
        this.action = action;
        this.wait = wait;
        this.js = js;
        PageFactory.initElements(browser, this);

    }

    @Step("Слайдер курсов существует.")
    public boolean sliderCourcesExist() {
        return wait.until(ExpectedConditions.visibilityOf(sliderCources)).isDisplayed();
    }

    @Step("Блок с сертификатами существует.")
    public boolean certificatesBlockExist() {
        return wait.until(ExpectedConditions.visibilityOf(certificatesBlock)).isDisplayed();
    }

    @Step("Блок с контактами существует.")
    public boolean contactsExist() {
        return wait.until(ExpectedConditions.visibilityOf(headContacts)).isDisplayed();
    }

    @Step("Горизонтальное меню существует.")
    public boolean horisontalMenuExist() {
        return wait.until(ExpectedConditions.visibilityOf(horisontalMenu)).isDisplayed();
    }

    @Step("Активация слайдера баннеров")
    public WayIndexPage sliderBannerActivate() {
        action.moveByOffset(30, 80).build().perform();
        return this;
    }

    @Step("Слайдер баннеров существует.")
    public boolean sliderBannerExist() {
        return wait.until(ExpectedConditions.visibilityOf(sliderBanner)).isDisplayed();
    }

    @Step("Рекламный баннер существует.")
    public boolean adBannerExist() {
        return wait.until(ExpectedConditions.visibilityOf(adBanner)).isDisplayed();
    }

    @Step("Нажатие на кнопку закрытия рекламного баннера.")
    public void bannerCloseButtonClick() {
        bannerClose.click();
    }

    @Step("Класс Prev слайдера Баннеров существует.")
    public boolean slidePrevClassExist(int activeSlideBannerIndex) {
        return banners.get(activeSlideBannerIndex - 1).getAttribute("class").contains("swiper-slide-prev");

    }

    @Step("Класс Active слайдера Баннеров существует.")
    public boolean slideActiveClassExist(int activeSlideBannerIndex) {
        return banners.get(activeSlideBannerIndex).getAttribute("class").contains("swiper-slide-active");
    }

    @Step("Класс Next слайдера Баннеров существует.")
    public boolean slideNextClassExist(int activeSlideBannerIndex) {
        return banners.get(activeSlideBannerIndex + 1).getAttribute("class").contains("swiper-slide-next");
    }

    @Step("Активный слайд видим.")
    public boolean slideExist(int activeSlideBannerIndex) {
        return banners.get(activeSlideBannerIndex).isDisplayed();
    }

    @Step("Свайп слайдера баннеров.")
    public void slideSlider() {
        action.dragAndDropBy(sliderBanner, sliderBanner.getLocation().getX() - 500, sliderBanner.getLocation().getY()).perform();
    }

    @Step("Футер существует.")
    public boolean footerExist() {
        return wait.until(ExpectedConditions.visibilityOf(footer)).isDisplayed();
    }

    @Step("Класс Prev присутствует у слайдов после свайпа.")
    public boolean afterActivitySlidePrevClassExist(int index) {
        return banners.get(index).getAttribute("class").contains("swiper-slide-prev");
    }

    @Step("Класс Active присутствует у слайдов после свайпа.")
    public boolean afterActivitySlideActiveClassExist(int index) {
        return banners.get(index + 1).getAttribute("class").contains("swiper-slide-active");
    }

    @Step("Активный слайд после свайпа виден.")
    public boolean afterActivitySlideExist(int index) {
        return banners.get(index + 1).isDisplayed();
    }

    @Step("Класс Next присутствует у слайдов после свайпа.")
    public boolean afterActivitySlideNextClassExist(int index) {
        return banners.get(index + 2).getAttribute("class").contains("swiper-slide-next");
    }
    @Step("При прокрутке страницы Горизонтальное меню отображается.")
    public boolean stickHorisontalMenu() {
        js.executeScript("window.scrollBy(0,1000)");
        return wait.until(ExpectedConditions.visibilityOf(stickedHorisontalMenu)).isDisplayed();
    }

    @Step("Класс Prev присутствует у слайдов до свайпа.")
    public boolean coursesPrevClassExist(int activeSlideBannerIndex) {
        return courses.get(activeSlideBannerIndex - 1).getAttribute("class").contains("swiper-slide-prev");

    }

    @Step("Класс Active присутствует у слайдов до свайпа.")
    public boolean coursesActiveClassExist(int activeSlideBannerIndex) {
        return courses.get(activeSlideBannerIndex).getAttribute("class").contains("swiper-slide-active");
    }

    @Step("Класс Next присутствует у слайдов до свайпа.")
    public boolean coursesNextClassExist(int activeSlideBannerIndex) {
        return courses.get(activeSlideBannerIndex + 1).getAttribute("class").contains("swiper-slide-next");
    }

    @Step("Клик по кнопке свайпа слайдера с курсами вправо.")
    public WayIndexPage nextCourseButtonClick() {
        action.moveToElement(coursesNextButton).click().build().perform();
        return this;
    }

    @Step("Класс Prev присутствует у слайдов после свайпа.")
    public boolean afterActivityCoursesPrevClassExist(int index) {
        return courses.get(index).getAttribute("class").contains("swiper-slide-prev");
    }
    @Step("Класс Active присутствует у слайдов после свайпа.")
    public boolean afterActivityCoursesActiveClassExist(int index) {
        return courses.get(index + 1).getAttribute("class").contains("swiper-slide-active");
    }

    @Step("Активный слайд в слайдере с Курсами отображается.")
    public boolean afterActivityCoursesExist(int index) {
        return banners.get(index + 1).isDisplayed();
    }

    @Step("Класс Next присутствует у слайдов после свайпа.")
    public boolean afterActivityCoursesNextClassExist(int index) {
        return courses.get(index + 2).getAttribute("class").contains("swiper-slide-next");
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