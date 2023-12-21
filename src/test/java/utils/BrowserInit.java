package utils;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserInit {
    private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

    public static WebDriver getWebdriverSelenoid(String browserName, String browserMode) {
        switch (browserMode) {
            case "selenoid":
                if (browserName.equals("chrome") && webdriver.get() == null) {
                    setUpRemoteDriver(new ChromeOptions());
                } else if (browserName.equals("firefox") && webdriver.get() == null) {
                    setUpRemoteDriver(new FirefoxOptions());
                } else if (browserName.equals("edge") && webdriver.get() == null) {
                    setUpRemoteDriver(new EdgeOptions());
                } else {
                    throw new RuntimeException("Incorrect BrowserName");
                }
                break;
            case "local":
                if (browserName.equals("chrome") && webdriver.get() == null) {
                    System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriverLocal"));
                    webdriver.set(new ChromeDriver(
                            new ChromeOptions().addArguments("--no-sandbox", "--disable-dev-shm-usage", "window-size=1220,880").setHeadless(true)));
                } else if (browserName.equals("firefox") && webdriver.get() == null) {
                    System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("geckodriverLocal"));
                    webdriver.set(new FirefoxDriver(
                            new FirefoxOptions().addArguments("--no-sandbox", "--disable-dev-shm-usage", "window-size=1220,880").setHeadless(true)));
                } else if (browserName.equals("edge") && webdriver.get() == null) {
                    System.setProperty("webdriver.edge.driver", ConfProperties.getProperty("edgedriverLocal"));
                    webdriver.set(new EdgeDriver(
                            new EdgeOptions().addArguments("--no-sandbox", "--disable-dev-shm-usage", "window-size=1220,880").setHeadless(true)));
                } else {
                    throw new RuntimeException("Incorrect BrowserName");
                }
            default:
                throw new RuntimeException("Incorrect WebdriverMode");
        }
        return webdriver.get();
    }


    public static synchronized void closeWebdriver() {
        if (webdriver.get() != null) {
            webdriver.get().quit();
            webdriver.remove();
        }
    }

    private static void setUpRemoteDriver(MutableCapabilities options) {
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("screenResolution", ConfProperties.getProperty("selenoidWindowResolution"));
        }});
        try {
            RemoteWebDriver driver = new RemoteWebDriver(new URL(ConfProperties.getProperty("hubUrl")), options);
            webdriver.set(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}