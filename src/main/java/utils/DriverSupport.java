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

import java.util.Properties;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverSupport {
    private WebDriver driver;

    private final static String SELENIUM_URL = System.getProperty("selenium.url", "http://selenium-selenium-hub:4444/wd/hub");
    private final static String SELENIUM_BROWSER = System.getProperty("selenium.browser", "chrome");
    private final static int SLEEP = Integer.parseInt(System.getProperty("sleep", "10"));
    DesiredCapabilities capabilities = new DesiredCapabilities(SELENIUM_BROWSER, "", Platform.ANY);

    //DesiredCapabilities capability = DesiredCapabilities.chrome();

    public WebDriver initDriver(String browser) throws InterruptedException {

                final ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--window-size=1920,1080");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    try {
                    driver = new RemoteWebDriver(new URL("http://23.251.148.254:4444/wd/hub"),
                            capabilities);
                    } catch (WebDriverException | MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println(String.format("Error connecting to %s: %s. Retrying", SELENIUM_URL, e));
                    }
                
        return driver;
    }
}
