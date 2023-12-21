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
                switch (browserName) {
                    case "chrome":
                        if (webdriver.get() == null) {
                            ChromeOptions options = new ChromeOptions();
                            setWebdriverOptionsSelenoid(options);
                            trySetUpRemoteDriver(options);
                        }
                        return webdriver.get();


                    case "firefox":
                        if (webdriver.get() == null) {
                            FirefoxOptions options = new FirefoxOptions();
                            setWebdriverOptionsSelenoid(options);
                            trySetUpRemoteDriver(options);
                        }
                        return webdriver.get();


                    case "edge":
                        if (webdriver.get() == null) {
                            EdgeOptions options = new EdgeOptions();
                            setWebdriverOptionsSelenoid(options);
                            trySetUpRemoteDriver(options);
                        }
                        return webdriver.get();

                    default:
                        throw new RuntimeException("Incorrect BrowserName");
                }

            case "local":
                switch (browserName) {
                    case "chrome":
                        if (webdriver.get() == null) {
                            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriverLocal"));
                            ChromeOptions options = new ChromeOptions()
                                    .addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless", "window-size=1220,880");
                            webdriver.set(new ChromeDriver(options));
                        }
                        return webdriver.get();
                    case "firefox":
                        if (webdriver.get() == null) {
                            System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("geckodriverLocal"));
                            FirefoxOptions options = new FirefoxOptions()
                                    .addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless", "window-size=1220,880");
                            webdriver.set(new FirefoxDriver(options));
                        }
                        return webdriver.get();
                    case "edge":
                        if (webdriver.get() == null) {
                            System.setProperty("webdriver.edge.driver", ConfProperties.getProperty("edgedriverLocal"));
                            EdgeOptions options = new EdgeOptions()
                                    .addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless", "window-size=1220,880");
                            webdriver.set(new EdgeDriver(options));
                        }
                        return webdriver.get();
                    default:
                        throw new RuntimeException("Incorrect BrowserName");
                }
            default:
                throw new RuntimeException("Incorrect WebdriverMode");
        }
    }

    public static synchronized void closeWebdriver() {
        if (webdriver.get() != null) {
            webdriver.get().quit();
            webdriver.remove();
        }
    }

    private static void setWebdriverOptionsSelenoid(MutableCapabilities options) {
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("screenResolution", ConfProperties.getProperty("selenoidWindowResolution"));
        }});
    }

    private static void trySetUpRemoteDriver(MutableCapabilities options) {
        try {
            RemoteWebDriver driver = new RemoteWebDriver(new URL(ConfProperties.getProperty("hubUrl")), options);
            webdriver.set(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}