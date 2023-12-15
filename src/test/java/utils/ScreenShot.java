package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ScreenShot {
    @Attachment(type = "image/png")
    public static byte[] captureScreenshot(WebDriver driver) {
        Screenshot screenshot = new AShot()
                .takeScreenshot(driver);
        try {
            ByteArrayOutputStream screenOutput = new ByteArrayOutputStream();
            ImageIO.write(screenshot.getImage(), "png", screenOutput);
            return screenOutput.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
