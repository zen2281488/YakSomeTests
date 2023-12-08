package utils;

import org.openqa.selenium.Dimension;
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
import java.util.concurrent.TimeUnit;

public class BrowserInit {
    private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

    public static WebDriver getWebdriverSelenoid(String browserName, String browserMode) {
        switch (browserMode) {
            case "selenoid":
                switch (browserName) {
                    case "chrome":
                        if (webdriver.get() == null) {
                            ChromeOptions options = new ChromeOptions();
                            options.setCapability("browserVersion", "latest");
                            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                                put("enableVnc", true);
                                put("screenResolution", "1920x1080");
                            }});
                            try {
                                RemoteWebDriver driver = new RemoteWebDriver(new URL(ConfProperties.getProperty("hubUrl")), options);
                                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                                driver.manage().window().setSize(new Dimension(1920, 1080));
                                webdriver.set(driver);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                        return webdriver.get();


                    case "firefox":
                        if (webdriver.get() == null) {
                            FirefoxOptions options = new FirefoxOptions();
                            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                                put("enableVnc", true);
                                put("screenResolution", "1920x1080");
                            }});
                            try {
                                RemoteWebDriver driver = new RemoteWebDriver(new URL(ConfProperties.getProperty("hubUrl")), options);
                                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                                driver.manage().window().setSize(new Dimension(1920, 1080));
                                webdriver.set(driver);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                        return webdriver.get();


                    case "edge":
                        if (webdriver.get() == null) {
                            EdgeOptions options = new EdgeOptions();
                            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                                put("enableVnc", true);
                                put("screenResolution", "1920x1080");

                            }});
                            try {
                                RemoteWebDriver driver = new RemoteWebDriver(new URL(ConfProperties.getProperty("hubUrl")), options);
                                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                                driver.manage().window().setSize(new Dimension(1920, 1080));
                                webdriver.set(driver);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
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
                            ChromeOptions options = new ChromeOptions();
                            options.addArguments("--no-sandbox");
                            options.addArguments("--disable-dev-shm-usage");
                            options.addArguments("--headless");
                            options.addArguments("window-size=1220,880");
                            WebDriver driver = new ChromeDriver(options);
                            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                            webdriver.set(driver);
                        }
                        return webdriver.get();
                    case "firefox":
                        if (webdriver.get() == null) {
                            System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("geckodriverLocal"));
                            FirefoxOptions options = new FirefoxOptions();
                            options.addArguments("--no-sandbox");
                            options.addArguments("--disable-dev-shm-usage");
                            options.addArguments("--headless");
                            options.addArguments("window-size=1220,880");
                            WebDriver driver = new FirefoxDriver(options);
                            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                            webdriver.set(driver);
                        }
                        return webdriver.get();
                    case "edge":
                        if (webdriver.get() == null) {
                            System.setProperty("webdriver.edge.driver", ConfProperties.getProperty("edgedriverLocal"));
                            EdgeOptions options = new EdgeOptions();
                            options.addArguments("--no-sandbox");
                            options.addArguments("--disable-dev-shm-usage");
                            options.addArguments("--headless");
                            options.addArguments("window-size=1220,880");
                            WebDriver driver = new EdgeDriver(options);
                            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                            webdriver.set(driver);
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
}