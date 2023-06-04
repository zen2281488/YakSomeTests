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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.*;
import utils.BrowserInit;
import utils.ConfProperties;

public class TestUnit {
    private WebDriver browser;
    private WayIndexPage wayIndexPage;
    private WayAutojquery wayAutojquery;
    private WayAutorisation wayAutorisation;
    private WayLogin waylogin;
    private SeleniumTutorialIndex seleniumTutorialIndex;

    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        wayIndexPage = new WayIndexPage(browser);
        wayAutojquery = new WayAutojquery(browser);
        wayAutorisation = new WayAutorisation(browser);
        waylogin = new WayLogin(browser);
        seleniumTutorialIndex = new SeleniumTutorialIndex(browser);
    }

    @Epic("Проверка существования и отображения элементов")
    @Description("Тест проверяет наличие блока Контактов в Хедере")
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения контактов в Хэдере")
    public void headerVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.waitContacts();
    }

    @Epic("Проверка существования и отображения элементов, их работоспособности")
    @Description("Тест проверяет отображение слайдера с баннерами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Test
    @Issue("UI-WAY2 №2")
    @DisplayName("Проверка наличия и отображения баннера. Проверка его работоспособности.")
    public void swiperBannerTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate().waitAdBanner().bannerCloseButtonClick().waitSliderBanner();
        WebElement activeSlide = wayIndexPage.activeSlide();
        WebElement nextSlide = wayIndexPage.nextSlide();
        wayIndexPage.slideSlider();
        Assert.assertTrue(wayIndexPage.slideClassContainsActive(nextSlide));
        Assert.assertTrue(wayIndexPage.slideClassContainsPrev(activeSlide));

    }

    @Epic("Проверка существования и отображения элементов, их работоспособности")
    @Description("Тест проверяет наличие Горизонтального меню. Прокручивает страницу вниз, чтобы увидеть следует ли слайдер за пользователем.")
    @Test
    @Issue("UI-WAY2 №3")
    @DisplayName("Проверка наличия и отображения горизонтального меню. Проверка работы stick класса слайдера.")
    public void horizontalMenuVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate().waitHorisontalMenu().waitStickHorisontalMenu();
    }

    @Epic("Проверка существования и отображения элементов")
    @Description("Тест проверяет наличие и отображение блока с Сертификатами.")
    @Test
    @Issue("UI-WAY2 №4")
    @DisplayName("Проверка наличия и отображения блока с Сертификатами.")
    public void certificatesBlockVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.waitCertificatesBlock();
    }

    @Epic("Проверка существования и отображения элементов, их работоспособности")
    @Description("Тест проверяет отображение слайдера с курсами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Test
    @Issue("UI-WAY2 №5")
    @DisplayName("Проверка наличия и отображения слайдера с курсами. Проверка работоспособности слайдера.")
    public void swiperCoursesTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate().waitSliderCources();
        WebElement activeCoursesSlide = wayIndexPage.coursesActiveSlide();
        WebElement nextCoursesSlide = wayIndexPage.coursesNextSlide();
        wayIndexPage.nextCourseButtonClick();
        Assert.assertTrue(wayIndexPage.slideCoursesClassContainsActive(nextCoursesSlide));
        Assert.assertTrue(wayIndexPage.slideCoursesClassContainsPrev(activeCoursesSlide));
    }

    @Epic("Проверка существования и отображения элементов")
    @Description("Тест проверяет наличие и отображение Футера.")
    @Test
    @Issue("UI-WAY2 №6")
    @DisplayName("Проверка наличия и отображения Футера")
    public void footerVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.waitFooter();
    }

    @Epic("Проверка перехода на другие страницы, проверка авторизации")
    @Description("Тест проверяет переходит ли на страницу PractiseSite1 если клинуть по соответствующей ссылке в выпадающем списке в хэдере.")
    @Test
    @Issue("UI-WAY2 №7")
    @DisplayName("Проверка перехода на страницу PracticeSite 1")
    public void wayPracticeSiteEntranceTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.triggerDropdown().practiseSiteButtonClick();
        wayAutojquery.waitBody();
        Assert.assertEquals(ConfProperties.getProperty("practice1ExpectedUrl"), browser.getCurrentUrl());
    }

    @Epic("Проверка перехода на другие страницы, проверка авторизации")
    @Description("Тест проверяет авторизацию на сайте Practice Site 2. Появляется ли текст о успешной авторизации после ввода учетных данных.")
    @Test
    @Issue("UI-WAY2 №8")
    @DisplayName("Проверка авторизации на сайте Practice Site 2")
    public void practiceSiteAuthorizationTest() {
        browser.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername().sendPassword().sendUserDescription().clickSubmitButton().waitSuccessLoginText();
        Assert.assertEquals("You're logged in!!", wayAutorisation.getSuccessLogText());
    }

//    @Epic("Проверка перехода на другие страницы, проверка авторизации, регистрации")
//    @Description("Тест проверяет проходит ли регистрация на сайте Way2 успешно, при вводе уникальных данных.")
//    @Test
//    @Issue("UI-WAY2 №9")
//    @DisplayName("Проверка регистрации на сайте Way2")
//    public void wayRegistrationTest() {
//        browser.get(ConfProperties.getProperty("loginWay2"));
//        browser.manage().window().maximize();
//        waylogin.signUpHrefClick().sendFullName().sendEmail().sendPassword().clickCommit();
//        seleniumTutorialIndex.waitAvatarImg().clickAvatarImg().clickLogoutHref();
//    }
//
//    @Epic("Проверка перехода на другие страницы, проверка авторизации")
//    @Description("Тест проверяет проходит авторизация на сайте Way2 при вводе учетных данных пользователя.")
//    @Test
//    @Issue("UI-WAY2 №10")
//    @DisplayName("Проверка авторизации на сайте Way2")
//    public void wayAuthorizationTest() {
//        browser.get(ConfProperties.getProperty("loginWay2"));
//        waylogin.loginSendEmail().loginSendPassword().clickCommit();
//        seleniumTutorialIndex.waitAvatarImg();
//    }

    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}