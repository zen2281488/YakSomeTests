package cases;

import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.*;
import utils.BrowserInit;
import utils.ConfProperties;

public class TestUnit {
    public WebDriver browser;
    public Way2IndexPage way2IndexPage;
    public UtilsMethods utils;
    public Way2autojquery wayAutojquery;
    public wayAutorisation wayAutorisation;
    public Way2login waylogin;
    public SeleniumTutorialIndex seleniumTutorialIndex;

    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        way2IndexPage = new Way2IndexPage(browser);
        utils = new UtilsMethods(browser);
        wayAutojquery = new Way2autojquery(browser);
        wayAutorisation = new wayAutorisation(browser);
        waylogin=new Way2login(browser);
        seleniumTutorialIndex=new SeleniumTutorialIndex(browser);
    }

    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения контактов в Хэдере")
    public void testWay1() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.existAssert(way2IndexPage.headContacts);
    }
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения баннера. Проверка его работоспособности.")
    public void testWay2() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.sliderBannerActivate();
        utils.existAssert(way2IndexPage.sliderBanner);
        utils.existAssert(way2IndexPage.adBanner).elementClick(way2IndexPage.bannerClose);
        int activeSlideBannerIndex = utils.getActiveSlideIndex(way2IndexPage.banners);
        utils.assertSliderBeforeActivity(way2IndexPage.banners, activeSlideBannerIndex)
                .slideSlider(way2IndexPage.sliderBanner, utils.getXCoordinate(way2IndexPage.sliderBanner), 0)
                .assertSliderAfterActivity(way2IndexPage.banners, activeSlideBannerIndex);
    }
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения горизонтального меню. Проверка работы stick класса слайдера.")
    public void testWay3() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.sliderBannerActivate();
        utils.existAssert(way2IndexPage.horisontalMenu);
        utils.horisontalMenuAssertion(browser, way2IndexPage.stickedHorisontalMenu);
    }

    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения блока с Сертификатами.")
    public void testWay4() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.existAssert(way2IndexPage.certificatesBlock);
    }

    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения слайдера с курсами. Проверка работоспособности слайдера.")
    public void testWay5() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils
                .sliderBannerActivate()
                .existAssert(way2IndexPage.sliderCources);
        int activeSlideCoursesIndex = utils.getActiveSlideIndex(way2IndexPage.courses);
        utils
                .assertSliderBeforeActivity(way2IndexPage.courses, activeSlideCoursesIndex)
                .actionClick(way2IndexPage.coursesNextButton)
                .assertSliderAfterActivity(way2IndexPage.courses, activeSlideCoursesIndex);
    }

    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения Футера")
    public void testWay6() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.existAssert(way2IndexPage.footer);
    }

    @Test
    @Issue("UI-WAY2 №2")
    @DisplayName("Проверка перехода на страницу PracticeSite 1")
    public void testWay7() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        utils.triggerDropdown(way2IndexPage.resourcesButton)
                .elementClick(way2IndexPage.practiceSite1Button)
                .existAssert(wayAutojquery.body);
        Assert.assertEquals(ConfProperties.getProperty("practice1ExpectedUrl"), browser.getCurrentUrl());
    }

    @Test
    @Issue("UI-WAY2 №3")
    @DisplayName("Проверка авторизации на сайте Practice Site 2")
    public void testWay8() {
        browser.get(ConfProperties.getProperty("practice2login"));
        utils.sendText(wayAutorisation.usernameInput, ConfProperties.getProperty("p2username"))
                .sendText(wayAutorisation.passInput, ConfProperties.getProperty("p2pass"))
                .sendText(wayAutorisation.usernameDescriptionInput, ConfProperties.getProperty("p2username"))
                .elementClick(wayAutorisation.loginButton);
        Assert.assertEquals("You're logged in!!", utils.getText(wayAutorisation.successlogtext));
    }

    @Test
    @Issue("UI-WAY2 №4")
    @DisplayName("Проверка регистрации на сайте Way2")
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
    @DisplayName("Проверка авторизации на сайте Way2")
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