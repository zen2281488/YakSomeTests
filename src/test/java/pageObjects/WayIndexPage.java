package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class WayIndexPage extends BasePage {
    @FindBy(css = "div.ast-above-header-wrap")
    private WebElement headContacts;

    @FindBy(css = ".dialog-lightbox-message")
    private WebElement adBanner;

    @FindBy(css = ".elementor-slides-wrapper.swiper-container-horizontal")
    private WebElement sliderBanner;

    @FindBy(css = "#ast-hf-menu-1:nth-of-type(1)")
    private WebElement horisontalMenu;

    @FindBy(css = ".ast-sticky-shrunk.ast-header-sticked")
    private WebElement stickedHorisontalMenu;

    @FindBy(xpath = "//h1[contains(text(), 'Best Selenium Certification Course Online')][1]")
    private WebElement certificatesBlock;

    @FindBy(css = ".swiper-container-autoheight")
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
    }

    @Step("Слайдер курсов существует.")
    public boolean sliderCourcesDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(sliderCources)).isDisplayed();
    }

    @Step("Блок с сертификатами существует.")
    public boolean certificatesBlockDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(certificatesBlock)).isDisplayed();
    }

    @Step("Блок с контактами существует.")
    public boolean contactsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(headContacts)).isDisplayed();
    }

    @Step("Горизонтальное меню существует.")
    public boolean horisontalMenuDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(horisontalMenu)).isDisplayed();
    }

    @Step("Активация слайдера баннеров")
    public WayIndexPage sliderBannerActivate() {
        action.moveByOffset(30, 80).build().perform();
        return this;
    }

    @Step("Слайдер баннеров существует.")
    public boolean sliderBannerDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(sliderBanner)).isDisplayed();
    }

    @Step("Рекламный баннер существует.")
    public WayIndexPage waitAdBanner() {
        wait.until(ExpectedConditions.visibilityOf(adBanner));
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
    public boolean footerDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(footer)).isDisplayed();
    }

    @Step("При прокрутке страницы Горизонтальное меню отображается.")
    public boolean stickHorisontalMenuDisplayed() {
        js.executeScript("window.scrollBy(0,1000)");
        return wait.until(ExpectedConditions.visibilityOf(stickedHorisontalMenu)).isDisplayed();
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

    @Step("Получение активного слайда слайдера с Баннерами.")
    public Swiper getActiveSwiper() {
        return getSwipers().stream().filter(Swiper::isActive).findFirst().orElseThrow();
    }

    @Step("Получение следующего слайда слайдера с Баннерами.")
    public Swiper getNextSwiper() {
        return getSwipers().stream().filter(Swiper::isNext).findFirst().orElseThrow();
    }

    private List<Swiper> getSwipers() {
        return banners.stream().map(Swiper::new).collect(Collectors.toList());
    }

    @Step("Получение активного слайда слайдера с курсами.")
    public Swiper getActiveCourse() {
        return getCourses().stream().filter(Swiper::isActive).findFirst().orElseThrow();
    }

    @Step("Получение следующего слайда слайдера с курсами.")
    public Swiper getNextCourse() {
        return getCourses().stream().filter(Swiper::isNext).findFirst().orElseThrow();
    }
    private List<Swiper> getCourses() {
        return courses.stream().map(Swiper::new).collect(Collectors.toList());
    }
}