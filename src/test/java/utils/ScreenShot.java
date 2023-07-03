package utils;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class ScreenShot {

    public static void captureScreenshot(WebDriver driver) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver);

        try {
            File screenshotFile = new File("screenshots", "screenshot.png");
            ImageIO.write(screenshot.getImage(), "png", screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
