package cases;

import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pageObjects.*;
import utils.ConfProperties;
import utils.BrowserInit;

import java.util.stream.IntStream;

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
        Assert.assertTrue(indexPage.courses.get(6).getAttribute("class").contains("swiper-slide-active"));
        action.moveToElement(indexPage.coursesNextButton).click().build().perform();
        Assert.assertTrue(indexPage.courses.get(7).getAttribute("class").contains("swiper-slide-active"));
        action.moveToElement(indexPage.coursesPrevButton).click().build().perform();
        Assert.assertTrue(indexPage.courses.get(8).getAttribute("class").contains("swiper-slide-active"));

    }
    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}