package cases;

import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import utils.ConfProperties;
import utils.BrowserInit;

import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class TestUnit {
    public WebDriver browser;

    public static ConfProperties pr;
    public IndexPage indexPage;
    public Actions action;
    public JavascriptExecutor js;


    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        indexPage = new IndexPage(browser);
        action = new Actions(browser);
        js = (JavascriptExecutor) browser;
    }

    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка существования элементов")
    public void testWay1() {
        browser.get(pr.getProperty("mainTestPage"));
        action.moveByOffset(30, 80).build().perform();
        indexPage.displayedAssert(indexPage.headContacts)
                .displayedAssert(indexPage.sliderBanner)
                .displayedAssert(indexPage.horisontalMenu)
                .displayedAssert(indexPage.certificatesBlock)
                .displayedAssert(indexPage.sliderCources)
                .displayedAssert(indexPage.footer);
        int oldYMenu = indexPage.getYCoordinate(indexPage.horisontalMenu);
        js.executeScript("window.scrollBy(0,1000)");
        Assert.assertTrue(indexPage.banners.get(2).getAttribute("class").contains("swiper-slide-prev"));
        Assert.assertTrue(indexPage.banners.get(3).getAttribute("class").contains("swiper-slide-active"));
        Assert.assertTrue(indexPage.banners.get(4).getAttribute("class").contains("swiper-slide-next"));
        int newYMenu = indexPage.getYCoordinate(indexPage.horisontalMenu);
        Assert.assertEquals(-890,(oldYMenu - newYMenu));
        indexPage.displayedAssert(indexPage.adBanner).bannerClose.click();
        int xNext = indexPage.getXCoordinate(indexPage.sliderBanner)-50;
        IntStream.range(3, 5).forEach(i -> {
            Assert.assertTrue(indexPage.banners.get(i).getAttribute("class").contains("swiper-slide-prev"));
            Assert.assertTrue(indexPage.banners.get(i+1).getAttribute("class").contains("swiper-slide-active"));
            Assert.assertTrue(indexPage.banners.get(i+1).isDisplayed());
            Assert.assertTrue(indexPage.banners.get(i+2).getAttribute("class").contains("swiper-slide-next"));
            action.dragAndDropBy(indexPage.sliderBanner, xNext, 0).perform();
        });

        browser.switchTo().frame(indexPage.adBannerIframe);
        indexPage.bannerClose2.click();
        browser.switchTo().defaultContent();

        int indexOfActiveSlide = parseInt(indexPage.courseActiveSlide.getAttribute("data-swiper-slide-index"))+3;
        Assert.assertTrue(indexPage.courses.get(indexOfActiveSlide).getAttribute("class").contains("swiper-slide-active"));
        js.executeScript("window.scrollBy(0,-4000)");
        js.executeScript("document.getElementsByClassName('.pp-slider-arrow.swiper-button-next').click();");
        action.moveToElement(indexPage.coursesNextButton).click().build().perform();
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.attributeContains(indexPage.courses.get(indexOfActiveSlide+1),"class","swiper-slide-active"));
        Assert.assertTrue(indexPage.courses.get(indexOfActiveSlide+1).getAttribute("class").contains("swiper-slide-active"));
        js.executeScript("document.getElementsByClassName('.pp-slider-arrow.swiper-button-prev').click();");
        action.moveToElement(indexPage.coursesPrevButton).click().build().perform();
        wait.until(ExpectedConditions.attributeContains(indexPage.courses.get(indexOfActiveSlide),"class","swiper-slide-active"));
        Assert.assertTrue(indexPage.courses.get(indexOfActiveSlide).getAttribute("class").contains("swiper-slide-active"));

    }
    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}