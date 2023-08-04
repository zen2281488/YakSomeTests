package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserInit {
    private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

    public static WebDriver getWebdriver() {
        if (webdriver.get() == null) {
            String hubUrl = "http://localhost:4444/wd/hub";
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            options.addArguments("window-size=1220,880");
            try {
                WebDriver driver = new RemoteWebDriver(new URL(hubUrl), options);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                webdriver.set(driver);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return webdriver.get();
    }

    public static synchronized void closeWebdriver() {
        if (webdriver.get() != null) {
            webdriver.get().quit();
            webdriver.remove();
        }
    }
}