package tests.ui;


import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Step;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import pageObjects.WayAutoDroppable;
import utils.ConfProperties;

@Epic("U10. Drag n Drop (IFrame)")
public class DragAndDropTest extends BaseTest {
    private WayAutoDroppable wayAutoDroppable;

    @BeforeEach
    @Step("Инициализация страниц")
    public void before() {
        wayAutoDroppable = new WayAutoDroppable(driver);
    }

    @RepeatedIfExceptionsTest(repeats = 2)
    @DisplayName("Тест Drag and Drop элемента на изменение состояния после совершения действия")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест изменения состояния текста div при drag and drop элемента в другой элемент")
    public void testDragAndDrop() {
        driver.get(ConfProperties.getProperty("mainTestPage") + ConfProperties.getProperty("wayAutomationDroppable"));
        Assertions.assertEquals("Drop here", wayAutoDroppable.getDroppableBoxText());
        wayAutoDroppable.dragAndDropBox();
        Assertions.assertEquals("Dropped!", wayAutoDroppable.getDroppableBoxText());
    }
}