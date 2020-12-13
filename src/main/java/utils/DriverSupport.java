package utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSupport {
    private WebDriver driver;

    private final static String SELENIUM_URL = System.getProperty("selenium.url", "http://selenium-selenium-hub:4444/wd/hub");
    private final static String SELENIUM_BROWSER = System.getProperty("selenium.browser", "chrome");
    DesiredCapabilities capabilities = new DesiredCapabilities(SELENIUM_BROWSER, "", Platform.ANY);

    public WebDriver initDriver(String browser){

        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1200x600");
            driver = new ChromeDriver();
        } else if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("headless");
            options.addArguments("--start-maximized");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("acceptInsecureCerts",true);
            driver = new FirefoxDriver();
        } else if(browser.contains("remote")){
            if(browser.equals("remote-chrome")){
                final ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                            capabilities);
                } catch (WebDriverException | MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println(String.format("Error connecting to %s: %s. Retrying", SELENIUM_URL, e));
                }
            }
        }


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        return driver;
    }
}