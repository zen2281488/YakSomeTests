package UITests;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.Swiper;
import pageObjects.WayAutojquery;
import pageObjects.WayIndexPage;
import utils.ConfProperties;

@Epic("Тесты работоспособности элементов.")
public class WorkTest extends BaseTest {
    private WayIndexPage wayIndexPage;
    private WayAutojquery wayAutojquery;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        wayIndexPage = new WayIndexPage(driver);
        wayAutojquery = new WayAutojquery(driver);
    }

    @Feature("Тест существования и отображения элементов, их работоспособности.")
    @Description("Тест проверяет отображение слайдера с баннерами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @Issue("UI-WAY2 №2")
    @DisplayName("Проверка наличия и отображения баннера. Проверка его работоспособности.")
    public void swiperBannerTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate().waitAdBanner().bannerCloseButtonClick();
        Assertions.assertTrue(wayIndexPage.sliderBannerDisplayed(), "Слайдер с Баннерами не отображается, или не существует.");
        Swiper activeSwiper = wayIndexPage.getActiveSwiper();
        Swiper nextSwiper = wayIndexPage.getNextSwiper();
        wayIndexPage.slideSlider();
        Assertions.assertTrue(activeSwiper.isPrev(), "Активный слайд не изменил свой класс на prev");
        Assertions.assertTrue(nextSwiper.isActive(), "Следующий слайд не изменил свой класс на active");
    }

    @Feature("Тест существования и отображения элементов, их работоспособности.")
    @Description("Тест проверяет наличие Горизонтального меню. Прокручивает страницу вниз, чтобы увидеть следует ли слайдер за пользователем.")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @Issue("UI-WAY2 №3")
    @DisplayName("Проверка наличия и отображения горизонтального меню. Проверка работы stick класса слайдера.")
    public void horizontalMenuVisibilityTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate();
        Assertions.assertTrue(wayIndexPage.horisontalMenuDisplayed(), "Хэдер не отображается, или не существует.");
        Assertions.assertTrue(wayIndexPage.stickHorisontalMenuDisplayed(), "Хэдер не следует за пользователем");
    }

    @Feature("Тест существования и отображения элементов, их работоспособности.")
    @Description("Тест проверяет отображение слайдера с курсами. Свайпает слайдер и проверяет меняется ли класс слайдера на другой, для проверки смены активных слайдов")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 2)
    @Issue("UI-WAY2 №5")
    @DisplayName("Проверка наличия и отображения слайдера с курсами. Проверка работоспособности слайдера.")
    public void swiperCoursesTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.sliderBannerActivate();
        Assertions.assertTrue(wayIndexPage.sliderCourcesDisplayed(), "Слайдер с Курсами не отображается либо не существует.");
        Swiper activeCourse = wayIndexPage.getActiveCourse();
        Swiper nextCourse = wayIndexPage.getNextCourse();
        wayIndexPage.nextCourseButtonClick();
        Assertions.assertTrue(activeCourse.isPrev(), "Активный слайд слайдера с курсами не изменил свой класс на prev");
        Assertions.assertTrue(nextCourse.isActive(), "Следующий слайд слайдера с курсами не изменил свой класс на active");
    }

    @Feature("Тест работоспособности переходов на другие страницы.")
    @Description("Тест проверяет переходит ли на страницу PractiseSite1 если клинуть по соответствующей ссылке в выпадающем списке в хэдере.")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedIfExceptionsTest(repeats = 3)
    @Issue("UI-WAY2 №7")
    @DisplayName("Проверка перехода на страницу PracticeSite 1")
    public void wayPracticeSiteEntranceTest() {
        driver.get(ConfProperties.getProperty("mainTestPage"));
        wayIndexPage.triggerDropdown().practiseSiteButtonClick();
        Assertions.assertTrue(wayAutojquery.bodyDisplayed(), "Содержание страницы не загрузилось.");
        Assertions.assertEquals(ConfProperties.getProperty("practice1ExpectedUrl"), driver.getCurrentUrl(), "URL Страницы на которую был совершен переход, не совпадает с ожидаемым.");
    }
}
