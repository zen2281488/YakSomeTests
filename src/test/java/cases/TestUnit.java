package cases;

import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pageObjects.IndexPage;
import pageObjects.Way2autojquery;
import utils.BrowserInit;
import utils.ConfProperties;

public class TestUnit {
    public WebDriver browser;
    public IndexPage indexPage;
    public Actions action;
    public JavascriptExecutor js;
    public UtilsMethods utils;
    public Way2autojquery practiceSite1;
    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        indexPage = new IndexPage(browser);
        utils = new UtilsMethods(browser);
        action = new Actions(browser);
        js = (JavascriptExecutor) browser;
        practiceSite1= new Way2autojquery(browser);
    }

    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка работоспособности и отображения элементов страницы.")
    public void testWay1() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        UtilsMethods.sliderBannerActivate(action);
        indexPage.displayedAssert(indexPage.headContacts)
                .displayedAssert(indexPage.sliderBanner)
                .displayedAssert(indexPage.horisontalMenu)
                .displayedAssert(indexPage.certificatesBlock)
                .displayedAssert(indexPage.sliderCources)
                .displayedAssert(indexPage.footer);
        UtilsMethods.horisontalMenuAssertion(js, indexPage, indexPage.horisontalMenu);
        indexPage.displayedAssert(indexPage.adBanner).bannerClose.click();
        int activeSlideBannerIndex = utils.getActiveSlideIndex(indexPage.banners);
        utils.assertSliderBeforeActivity(indexPage.banners, activeSlideBannerIndex);
        action.dragAndDropBy(indexPage.sliderBanner, indexPage.getXCoordinate(indexPage.sliderBanner) - 500, 0).perform();
        utils.assertSliderAfterActivity(indexPage.banners, activeSlideBannerIndex);
        browser.switchTo().frame(indexPage.adBannerIframe);
        indexPage.bannerClose2.click();
        browser.switchTo().defaultContent();
        int activeSlideCoursesIndex = utils.getActiveSlideIndex(indexPage.courses);
        utils.assertSliderBeforeActivity(indexPage.courses, activeSlideCoursesIndex);
        action.moveToElement(indexPage.coursesNextButton).click().build().perform();
        utils.assertSliderAfterActivity(indexPage.courses, activeSlideCoursesIndex);

    }
    @Test
    @Issue("UI-WAY2 №2")
    @DisplayName("Проверка перехода на страницу")
    public void testWay2() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        action.moveToElement(indexPage.resourcesButton).build().perform();
        indexPage.practiceSite1Button.click();
        indexPage.displayedAssert(practiceSite1.body);
        Assert.assertEquals("https://www.way2automation.com/way2auto_jquery/index.php",browser.getCurrentUrl());
    }



    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}