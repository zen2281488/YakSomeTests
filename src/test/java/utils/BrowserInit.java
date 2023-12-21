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
    static RemoteWebDriver driver;

    public static WebDriver getWebdriverSelenoid(String browserName, String browserMode) {
        if (webdriver.get() == null) {
            switch (browserMode) {
                case "selenoid":
                    switch (browserName) {
                        case "chrome":
                            driver = setUpRemoteDriver(new ChromeOptions());
                            break;
                        case "firefox":
                            driver = setUpRemoteDriver(new FirefoxOptions());
                            break;
                        case "edge":
                            driver = setUpRemoteDriver(new EdgeOptions());
                            break;
                        default:
                            throw new RuntimeException("Incorrect BrowserName");
                    }
                    break;

                case "local":
                    switch (browserName) {
                        case "chrome":
                            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriverLocal"));
                            driver = new ChromeDriver(new ChromeOptions().addArguments("--no-sandbox", "--disable-dev-shm-usage", "window-size=1220,880").setHeadless(true));
                            break;
                        case "firefox":
                            System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("geckodriverLocal"));
                            driver = new FirefoxDriver(new FirefoxOptions().addArguments("--no-sandbox", "--disable-dev-shm-usage", "window-size=1220,880").setHeadless(true));
                            break;
                        case "edge":
                            System.setProperty("webdriver.edge.driver", ConfProperties.getProperty("edgedriverLocal"));
                            driver = new EdgeDriver(new EdgeOptions().addArguments("--no-sandbox", "--Ыdisable-dev-shm-usage", "window-size=1220,880").setHeadless(true));
                            break;
                        default:
                            throw new RuntimeException("Incorrect BrowserName");
                    }
                    break;
                default:
                    throw new RuntimeException("Incorrect WebdriverMode");
            }

        }
        webdriver.set(driver);
        return webdriver.get();
    }

    public static synchronized void closeWebdriver() {
        if (webdriver.get() != null) {
            webdriver.get().quit();
            webdriver.remove();
        }
    }

    private static RemoteWebDriver setUpRemoteDriver(MutableCapabilities options) {
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("screenResolution", ConfProperties.getProperty("selenoidWindowResolution"));
        }});
        try {
            return new RemoteWebDriver(new URL(ConfProperties.getProperty("hubUrl")), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}