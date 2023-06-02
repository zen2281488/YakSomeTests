package UITests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import utils.BrowserInit;
import utils.ConfProperties;
import utils.UtilsMethods;

public class TestUnit {
    private WebDriver browser;
    private WayIndexPage wayIndexPage;
    private UtilsMethods utils;
    private WayAutojquery wayAutojquery;
    private wayAutorisation wayAutorisation;
    private WayLogin waylogin;
    private SeleniumTutorialIndex seleniumTutorialIndex;
    private Actions action;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        action = new Actions(browser);
        wait = new WebDriverWait(browser, 15);
        js = (JavascriptExecutor) browser;
        wayIndexPage = new WayIndexPage(browser,action,wait,js);
        utils = new UtilsMethods();
        wayAutojquery = new WayAutojquery(browser,wait);
        wayAutorisation = new wayAutorisation(browser,wait);
        waylogin=new WayLogin(browser);
        seleniumTutorialIndex=new SeleniumTutorialIndex(browser,wait);
    }

    @Epic("Проверка существования и отображения элементов")
    @Description("Тест проверяет наличие блока Контактов в Хедере")
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения контактов в Хэдере")
    public void testWay1() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assert.assertTrue(wayIndexPage.contactsExist());
    }

    @Epic("Проверка существования и отображения элементов, их работоспособности")
    @Description("Тест проверяет отображение слайдера с баннерами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения баннера. Проверка его работоспособности.")
    public void testWay2() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate();
        Assert.assertTrue(wayIndexPage.adBannerExist());
        wayIndexPage.bannerCloseButtonClick();
        Assert.assertTrue(wayIndexPage.sliderBannerExist());
        int activeSlideBannerIndex = utils.getActiveSlideIndex(wayIndexPage.banners);
        Assert.assertTrue(wayIndexPage.slideExist(activeSlideBannerIndex));
        Assert.assertTrue(wayIndexPage.slidePrevClassExist(activeSlideBannerIndex));
        Assert.assertTrue(wayIndexPage.slideActiveClassExist(activeSlideBannerIndex));
        Assert.assertTrue(wayIndexPage.slideNextClassExist(activeSlideBannerIndex));
        wayIndexPage.slideSlider();
        Assert.assertTrue(wayIndexPage.afterActivitySlideExist(activeSlideBannerIndex));
        Assert.assertTrue(wayIndexPage.afterActivitySlidePrevClassExist(activeSlideBannerIndex));
        Assert.assertTrue(wayIndexPage.afterActivitySlideNextClassExist(activeSlideBannerIndex));
        Assert.assertTrue(wayIndexPage.afterActivitySlideActiveClassExist(activeSlideBannerIndex));
    }

    @Epic("Проверка существования и отображения элементов, их работоспособности")
    @Description("Тест проверяет наличие Горизонтального меню. Прокручивает страницу вниз, чтобы увидеть следует ли слайдер за пользователем.")
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения горизонтального меню. Проверка работы stick класса слайдера.")
    public void testWay3() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate();
        Assert.assertTrue(wayIndexPage.horisontalMenuExist());
        Assert.assertTrue(wayIndexPage.stickHorisontalMenu());
    }

    @Epic("Проверка существования и отображения элементов")
    @Description("Тест проверяет наличие и отображение блока с Сертификатами.")
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения блока с Сертификатами.")
    public void testWay4() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assert.assertTrue(wayIndexPage.certificatesBlockExist());
    }

    @Epic("Проверка существования и отображения элементов, их работоспособности")
    @Description("Тест проверяет отображение слайдера с курсами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения слайдера с курсами. Проверка работоспособности слайдера.")
    public void testWay5() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate();
        Assert.assertTrue(wayIndexPage.sliderCourcesExist());
        int activeSlideCoursesIndex = utils.getActiveSlideIndex(wayIndexPage.courses);
        Assert.assertTrue(wayIndexPage.coursesPrevClassExist(activeSlideCoursesIndex));
        Assert.assertTrue(wayIndexPage.coursesActiveClassExist(activeSlideCoursesIndex));
        Assert.assertTrue(wayIndexPage.coursesNextClassExist(activeSlideCoursesIndex));
        wayIndexPage.nextCourseButtonClick();
        Assert.assertTrue(wayIndexPage.afterActivityCoursesExist(activeSlideCoursesIndex));
        Assert.assertTrue(wayIndexPage.afterActivityCoursesPrevClassExist(activeSlideCoursesIndex));
        Assert.assertTrue(wayIndexPage.afterActivityCoursesNextClassExist(activeSlideCoursesIndex));
        Assert.assertTrue(wayIndexPage.afterActivityCoursesActiveClassExist(activeSlideCoursesIndex));
    }

    @Epic("Проверка существования и отображения элементов")
    @Description("Тест проверяет наличие и отображение Футера.")
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения Футера")
    public void testWay6() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assert.assertTrue(wayIndexPage.footerExist());
    }

    @Epic("Проверка перехода на другие страницы, проверка авторизации")
    @Description("Тест проверяет переходит ли на страницу PractiseSite1 если клинуть по соответствующей ссылке в выпадающем списке в хэдере.")
    @Test
    @Issue("UI-WAY2 №2")
    @DisplayName("Проверка перехода на страницу PracticeSite 1")
    public void testWay7() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.triggerDropdown().practiseSiteButtonClick();
        Assert.assertTrue(wayAutojquery.bodyExistAssert());
        Assert.assertEquals(ConfProperties.getProperty("practice1ExpectedUrl"), browser.getCurrentUrl());
    }

    @Epic("Проверка перехода на другие страницы, проверка авторизации")
    @Description("Тест проверяет авторизацию на сайте Practice Site 2. Появляется ли текст о успешной авторизации после ввода учетных данных.")
    @Test
    @Issue("UI-WAY2 №3")
    @DisplayName("Проверка авторизации на сайте Practice Site 2")
    public void testWay8() {
        browser.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername().sendPassword().sendUserDescription().clickSubmitButton();
        Assert.assertTrue(wayAutorisation.successLogTextExist());
        Assert.assertEquals("You're logged in!!", wayAutorisation.getSuccessLogText());
    }

    @Epic("Проверка перехода на другие страницы, проверка авторизации, регистрации")
    @Description("Тест проверяет проходит ли регистрация на сайте Way2 успешно, при вводе уникальных данных.")
    @Test
    @Issue("UI-WAY2 №4")
    @DisplayName("Проверка регистрации на сайте Way2")
    public void testWay9() {
        browser.get(ConfProperties.getProperty("loginWay2"));
        browser.manage().window().maximize();
        waylogin.signUpHrefClick().sendFullName().sendEmail().sendPassword().clickCommit();
        Assert.assertTrue(seleniumTutorialIndex.avatarImgExist());
        seleniumTutorialIndex.clickAvatarImg().clickLogoutHref();
    }

    @Epic("Проверка перехода на другие страницы, проверка авторизации")
    @Description("Тест проверяет проходит авторизация на сайте Way2 при вводе учетных данных пользователя.")
    @Test
    @Issue("UI-WAY2 №5")
    @DisplayName("Проверка авторизации на сайте Way2")
    public void testWay10() {
        browser.get(ConfProperties.getProperty("loginWay2"));
        waylogin.loginSendEmail().loginSendPassword().clickCommit();
        Assert.assertTrue(seleniumTutorialIndex.avatarImgExist());
    }

    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}