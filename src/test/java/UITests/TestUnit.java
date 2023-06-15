package UITests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.*;
import utils.BrowserInit;
import utils.ConfProperties;

@Epic("Тесты Авторизации, отображения и работоспособности элементов страницы.")
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

    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие блока Контактов в Хедере")
    @Test
    @Issue("UI-WAY2 №1")
    @DisplayName("Проверка наличия и отображения контактов в Хэдере")
    public void headerVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assert.assertTrue("Конейнер с Контактами не отображается или отсутствует.",wayIndexPage.contactsDisplayed());
    }

    @Feature("Тест существования и отображения элементов, их работоспособности.")
    @Description("Тест проверяет отображение слайдера с баннерами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Test
    @Issue("UI-WAY2 №2")
    @DisplayName("Проверка наличия и отображения баннера. Проверка его работоспособности.")
    public void swiperBannerTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate().waitAdBanner().bannerCloseButtonClick();
        Assert.assertTrue("Слайдер с Баннерами не отображается, или не существует.",wayIndexPage.sliderBannerDisplayed());
        var activeSwiper = wayIndexPage.getActiveSwiper();
        var nextSwiper = wayIndexPage.getNextSwiper();
        wayIndexPage.slideSlider();
        Assert.assertTrue("Активный слайд не изменил свой класс на prev",activeSwiper.isPrev());
        Assert.assertTrue("Следующий слайд не изменил свой класс на active",nextSwiper.isActive());
    }

    @Feature("Тест существования и отображения элементов, их работоспособности.")
    @Description("Тест проверяет наличие Горизонтального меню. Прокручивает страницу вниз, чтобы увидеть следует ли слайдер за пользователем.")
    @Test
    @Issue("UI-WAY2 №3")
    @DisplayName("Проверка наличия и отображения горизонтального меню. Проверка работы stick класса слайдера.")
    public void horizontalMenuVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate();
        Assert.assertTrue("Хэдер не отображается, или не существует.",wayIndexPage.horisontalMenuDisplayed());
        Assert.assertTrue("Хэдер не следует за пользователем",wayIndexPage.stickHorisontalMenuDisplayed());
    }

    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение блока с Сертификатами.")
    @Test
    @Issue("UI-WAY2 №4")
    @DisplayName("Проверка наличия и отображения блока с Сертификатами.")
    public void certificatesBlockVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assert.assertTrue("Блок с Сертификатами не отображается либо не существует.",wayIndexPage.certificatesBlockDisplayed());
    }

    @Feature("Тест существования и отображения элементов, их работоспособности.")
    @Description("Тест проверяет отображение слайдера с курсами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Test
    @Issue("UI-WAY2 №5")
    @DisplayName("Проверка наличия и отображения слайдера с курсами. Проверка работоспособности слайдера.")
    public void swiperCoursesTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate();
        Assert.assertTrue("Слайдер с Курсами не отображается либо не существует.",wayIndexPage.sliderCourcesDisplayed());
        Swiper activeCourse = wayIndexPage.getActiveCourse();
        Swiper nextCourse = wayIndexPage.getNextCourse();
        wayIndexPage.nextCourseButtonClick();
        Assert.assertTrue("Активный слайд слайдера с курсами не изменил свой класс на prev",activeCourse.isPrev());
        Assert.assertTrue("Следующий слайд слайдера с курсами не изменил свой класс на active",nextCourse.isActive());
    }

    @Feature("Тест существования и отображения элементов.")
    @Description("Тест проверяет наличие и отображение Футера.")
    @Test
    @Issue("UI-WAY2 №6")
    @DisplayName("Проверка наличия и отображения Футера")
    public void footerVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        Assert.assertTrue("Футер не отображается или не существует.",wayIndexPage.footerDisplayed());
    }

    @Feature("Тест перехода на другие страницы.")
    @Description("Тест проверяет переходит ли на страницу PractiseSite1 если клинуть по соответствующей ссылке в выпадающем списке в хэдере.")
    @Test
    @Issue("UI-WAY2 №7")
    @DisplayName("Проверка перехода на страницу PracticeSite 1")
    public void wayPracticeSiteEntranceTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.triggerDropdown().practiseSiteButtonClick();
        Assert.assertTrue("Содержание страницы не загрузилось.",wayAutojquery.bodyDisplayed());
        Assert.assertEquals("URL Страницы на которую был совершен переход, не совпадает с ожидаемым.",ConfProperties.getProperty("practice1ExpectedUrl"), browser.getCurrentUrl());
    }

    @Feature("Тест авторизации.")
    @Description("Тест проверяет авторизацию на сайте Practice Site 2. Появляется ли текст о успешной авторизации после ввода учетных данных.")
    @Test
    @Issue("UI-WAY2 №8")
    @DisplayName("Проверка авторизации на сайте Practice Site 2")
    public void practiceSiteAuthorizationTest() {
        browser.get(ConfProperties.getProperty("practice2login"));
        wayAutorisation.sendUsername(ConfProperties.getProperty("p2username")).sendPassword(ConfProperties.getProperty("p2pass")).sendUserDescription(ConfProperties.getProperty("p2username")).clickSubmitButton().waitSuccessLoginText();
        Assert.assertEquals("Текст уведомления о успешной авторизации не совпадает с ожидаемым.","You're logged in!!", wayAutorisation.getSuccessLogText());
    }

    @Feature("Тест авторизации.")
    @Description("Тест проверяет успешно ли проходит авторизация на сайте Way2 при вводе учетных данных пользователя.")
    @Test
    @Issue("UI-WAY2 №10")
    @DisplayName("Проверка авторизации на сайте Way2")
    public void wayAuthorizationTest() {
        browser.get(ConfProperties.getProperty("loginWay2"));
        waylogin.loginSendEmail(ConfProperties.getProperty("way2LogEmail")).loginSendPassword(ConfProperties.getProperty("way2LogPassword")).clickCommit();
        Assert.assertTrue("Аватар не был загружен, авторизация не прошла успешно.",seleniumTutorialIndex.avatarImgDisplayed());
    }

    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }
}