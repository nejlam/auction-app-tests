package testUtils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.DriverSupport;

public abstract class TestBase {
    protected WebDriver driver;

    @BeforeTest(alwaysRun = true)
    @Parameters({"browser","url"})
    public void setUp(String browser, String url) throws InterruptedException {
        DriverSupport driverSupport = new DriverSupport();
        driver = driverSupport.initDriver(browser);
        driver.get(url);
    }

    @BeforeTest(alwaysRun = true)
    public void startTest(final ITestContext testContext) {
        System.out.println("Test running: " + testContext.getName());
    }

    @AfterTest(alwaysRun = true)
    public void endTest(final ITestContext testContext){
        System.out.println("Test completed: " + testContext.getName());
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}

