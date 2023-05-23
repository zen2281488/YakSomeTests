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
import pageObjects.*;
import utils.BrowserInit;
import utils.ConfProperties;

public class TestUnit {
    public WebDriver browser;
    public IndexPage indexPage;
    public JavascriptExecutor js;
    public UtilsMethods utils;
    public Way2autojquery practiceSite1;
    public PracticeSite2 practiceSite2;
    public Way2login waylogin;
    public SeleniumTutorialIndex seleniumTutorialIndex;

    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        indexPage = new IndexPage(browser);
        utils = new UtilsMethods(browser);
        js = (JavascriptExecutor) browser;
        practiceSite1 = new Way2autojquery(browser);
        practiceSite2 = new PracticeSite2(browser);
        waylogin=new Way2login(browser);
        seleniumTutorialIndex=new SeleniumTutorialIndex(browser);
    }

    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка работоспособности и отображения элементов страницы.")
    public void testWay1() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.existAssert(indexPage.headContacts);
    }
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка работоспособности и отображения элементов страницы.")
    public void testWay2() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.existAssert(indexPage.sliderBanner).sliderBannerActivate();
        utils.existAssert(indexPage.adBanner).elementClick(indexPage.bannerClose);
        int activeSlideBannerIndex = utils.getActiveSlideIndex(indexPage.banners);
        utils.assertSliderBeforeActivity(indexPage.banners, activeSlideBannerIndex)
                .slideSlider(indexPage.sliderBanner, indexPage.getXCoordinate(indexPage.sliderBanner), 0)
                .assertSliderAfterActivity(indexPage.banners, activeSlideBannerIndex);
    }
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка работоспособности и отображения элементов страницы.")
    public void testWay3() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.sliderBannerActivate();
        indexPage.displayedAssert(indexPage.horisontalMenu);
        utils.horisontalMenuAssertion(browser,js, indexPage.stickedHorisontalMenu);
    }
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка работоспособности и отображения элементов страницы.")
    public void testWay4() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        indexPage.displayedAssert(indexPage.certificatesBlock);
    }
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка работоспособности и отображения элементов страницы.")
    public void testWay5() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils
                .sliderBannerActivate()
                .existAssert(indexPage.sliderCources);
        int activeSlideCoursesIndex = utils.getActiveSlideIndex(indexPage.courses);
        utils
                .assertSliderBeforeActivity(indexPage.courses, activeSlideCoursesIndex)
                .actionClick(indexPage.coursesNextButton)
                .assertSliderAfterActivity(indexPage.courses, activeSlideCoursesIndex);
    }
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка работоспособности и отображения элементов страницы.")
    public void testWay6() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        indexPage.displayedAssert(indexPage.footer);
    }
    @Test
    @Issue("UI-WAY2 №2")
    @DisplayName("Проверка перехода на страницу")
    public void testWay7() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.triggerDropdown(indexPage.resourcesButton)
                .elementClick(indexPage.practiceSite1Button)
                .existAssert(practiceSite1.body);
        Assert.assertEquals(ConfProperties.getProperty("practice1ExpectedUrl"), browser.getCurrentUrl());
    }

    @Test
    @Issue("UI-WAY2 №3")
    @DisplayName("Проверка авторизации")
    public void testWay8() {
        browser.get(ConfProperties.getProperty("practice2login"));
        utils.sendText(practiceSite2.usernameInput, ConfProperties.getProperty("p2username"))
                .sendText(practiceSite2.passInput, ConfProperties.getProperty("p2pass"))
                .sendText(practiceSite2.usernameDescriptionInput, ConfProperties.getProperty("p2username"))
                .elementClick(practiceSite2.loginButton);
        Assert.assertEquals("You're logged in!!", utils.getText(practiceSite2.successlogtext));
    }
    @Test
    @Issue("UI-WAY2 №4")
    @DisplayName("Проверка регистрации")
    public void testWay9() {
        browser.get(ConfProperties.getProperty("loginWay2"));
        browser.manage().window().maximize();
        utils.elementClick(waylogin.signUpHref)
                .sendText(waylogin.fullNameInput,ConfProperties.getProperty("way2RegFullName"))
                .sendText(waylogin.emailSignInInput,ConfProperties.getProperty("way2RegEmail"))
                .sendText(waylogin.passwordInput,ConfProperties.getProperty("way2RegPassword"))
                .elementClick(waylogin.commitButton)
                .existAssert(seleniumTutorialIndex.avatarImg)
                .elementClick(seleniumTutorialIndex.avatarImg)
                .elementClick(seleniumTutorialIndex.logoutHref);
    }

    @Test
    @Issue("UI-WAY2 №5")
    @DisplayName("Проверка авторизации")
    public void testWay10() {
        browser.get(ConfProperties.getProperty("loginWay2"));
        utils.sendText(waylogin.emailLoginInput,ConfProperties.getProperty("way2LogEmail"))
                        .sendText(waylogin.passwordInput,ConfProperties.getProperty("way2LogPassword"))
                        .elementClick(waylogin.commitButton).existAssert(seleniumTutorialIndex.avatarImg);
    }

    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}