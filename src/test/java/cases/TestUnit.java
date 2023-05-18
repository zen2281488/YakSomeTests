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
import pageObjects.PracticeSite2;
import utils.BrowserInit;
import utils.ConfProperties;

public class TestUnit {
    public WebDriver browser;
    public IndexPage indexPage;
    public Actions action;
    public JavascriptExecutor js;
    public UtilsMethods utils;
    public Way2autojquery practiceSite1;
    public PracticeSite2 practiceSite2;
    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        indexPage = new IndexPage(browser);
        utils = new UtilsMethods(browser);
        action = new Actions(browser);
        js = (JavascriptExecutor) browser;
        practiceSite1= new Way2autojquery(browser);
        practiceSite2= new PracticeSite2(browser);
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
    @Test
    @Issue("UI-WAY2 №3")
    @DisplayName("Проверка авторизации")
    public void testWay3() {
        browser.get("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
        practiceSite2.sendText(practiceSite2.usernameInput,ConfProperties.getProperty("p2username"))
                .sendText(practiceSite2.passInput,ConfProperties.getProperty("p2pass"))
                .sendText(practiceSite2.usernameDescriptionInput,ConfProperties.getProperty("p2username"))
                .buttonClick(practiceSite2.loginButton);
        Assert.assertEquals("You're logged in!!",practiceSite2.successlogtext.getText());
    }


    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}