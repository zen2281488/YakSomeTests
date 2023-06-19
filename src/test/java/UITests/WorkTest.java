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
public class WorkTest {
    private WebDriver browser;
    private WayIndexPage wayIndexPage;
    private WayAutojquery wayAutojquery;

    @Before
    @Step("Инициализация браузера")
    public void before() {
        browser = BrowserInit.getWebdriver();
        wayIndexPage = new WayIndexPage(browser);
        wayAutojquery = new WayAutojquery(browser);
    }

    @Feature("Тест существования и отображения элементов, их работоспособности.")
    @Description("Тест проверяет отображение слайдера с баннерами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №2")
    @DisplayName("Проверка наличия и отображения баннера. Проверка его работоспособности.")
    public void swiperBannerTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate().waitAdBanner().bannerCloseButtonClick();
        Assert.assertTrue("Слайдер с Баннерами не отображается, или не существует.", wayIndexPage.sliderBannerDisplayed());
        Swiper activeSwiper = wayIndexPage.getActiveSwiper();
        Swiper nextSwiper = wayIndexPage.getNextSwiper();
        wayIndexPage.slideSlider();
        Assert.assertTrue("Активный слайд не изменил свой класс на prev", activeSwiper.isPrev());
        Assert.assertTrue("Следующий слайд не изменил свой класс на active", nextSwiper.isActive());
    }

    @Feature("Тест существования и отображения элементов, их работоспособности.")
    @Description("Тест проверяет наличие Горизонтального меню. Прокручивает страницу вниз, чтобы увидеть следует ли слайдер за пользователем.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №3")
    @DisplayName("Проверка наличия и отображения горизонтального меню. Проверка работы stick класса слайдера.")
    public void horizontalMenuVisibilityTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate();
        Assert.assertTrue("Хэдер не отображается, или не существует.", wayIndexPage.horisontalMenuDisplayed());
        Assert.assertTrue("Хэдер не следует за пользователем", wayIndexPage.stickHorisontalMenuDisplayed());
    }


    @Feature("Тест существования и отображения элементов, их работоспособности.")
    @Description("Тест проверяет отображение слайдера с курсами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №5")
    @DisplayName("Проверка наличия и отображения слайдера с курсами. Проверка работоспособности слайдера.")
    public void swiperCoursesTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate();
        Assert.assertTrue("Слайдер с Курсами не отображается либо не существует.", wayIndexPage.sliderCourcesDisplayed());
        Swiper activeCourse = wayIndexPage.getActiveCourse();
        Swiper nextCourse = wayIndexPage.getNextCourse();
        wayIndexPage.nextCourseButtonClick();
        Assert.assertTrue("Активный слайд слайдера с курсами не изменил свой класс на prev", activeCourse.isPrev());
        Assert.assertTrue("Следующий слайд слайдера с курсами не изменил свой класс на active", nextCourse.isActive());
    }


    @Feature("Тест перехода на другие страницы.")
    @Description("Тест проверяет переходит ли на страницу PractiseSite1 если клинуть по соответствующей ссылке в выпадающем списке в хэдере.")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    @Issue("UI-WAY2 №7")
    @DisplayName("Проверка перехода на страницу PracticeSite 1")
    public void wayPracticeSiteEntranceTest() {
        browser.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.triggerDropdown().practiseSiteButtonClick();
        Assert.assertTrue("Содержание страницы не загрузилось.", wayAutojquery.bodyDisplayed());
        Assert.assertEquals("URL Страницы на которую был совершен переход, не совпадает с ожидаемым.", ConfProperties.getProperty("practice1ExpectedUrl"), browser.getCurrentUrl());
    }

    @After
    @Step("Очиска данных")
    public void after() {
        BrowserInit.closeWebdriver();
    }}
