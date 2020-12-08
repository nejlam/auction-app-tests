package utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import sun.security.krb5.internal.crypto.Des;

import java.util.Properties;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverSupport {
    private WebDriver driver;

    private final static String SELENIUM_URL = System.getProperty("selenium.url", "http://34.107.132.102:4444/wd/hub");
    private final static String SELENIUM_BROWSER = System.getProperty("selenium.browser", "chrome");
    private final static int SLEEP = Integer.parseInt(System.getProperty("sleep", "10000"));
    DesiredCapabilities capabilities = new DesiredCapabilities(SELENIUM_BROWSER, "", Platform.ANY);


    public WebDriver initDriver(String browser) throws InterruptedException {
        java.util.Properties p = new Properties();

        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("headless");
            options.addArguments("--start-maximized");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("acceptInsecureCerts",true);
            driver = new FirefoxDriver(capabilities);

        } else if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors",
                    "--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        } else if (browser.equals("safari")) {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setUseTechnologyPreview(true);
            driver = new SafariDriver(safariOptions);
        }

        if (browser.contains("remote")) {
            if (browser.equals("remote-firefox")) {
                //capabilities = DesiredCapabilities.firefox();
            } else if (browser.equals("remote-chrome")) {
                final ChromeOptions chromeOptions = new ChromeOptions();
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
                chromeOptions.addArguments("--headless");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                for (int i = 0; i < 10; i++){
                    try {
                    driver = new RemoteWebDriver(new URL("http://34.107.132.102:4444/wd/hub"),
                            capabilities);
                    } catch (WebDriverException | MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println(String.format("Error connecting to %s: %s. Retrying", SELENIUM_URL, e));
                    Thread.sleep(1000);
                    }
                }
            }
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
