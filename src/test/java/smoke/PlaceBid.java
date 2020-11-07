package smoke;

import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.*;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ItemPage;
import page_objects.auction_app.LoginPage;
import testUtils.TestBase;

import java.io.FileReader;
import java.io.IOException;

public class PlaceBid extends TestBase {
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String PASSWORD = "12345678";


    @BeforeTest
    public String getEmail() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("data/data.csv"));
        String[] email = reader.readNext();
        return email[0];
    }

    @Test(priority = 0)
    public void verifyHomePage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 1)
    public void openLoginPage(){
        new HomePage(driver).clickLoginLink();
    }

    @Test(priority = 2)
    public void populateLoginForm() throws IOException {
        new LoginPage(driver).populateLoginForm(getEmail(), PASSWORD);
    }

    @Test(priority = 3)
    public void verifyHomePageAfterLogin(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 4)
    public void clickFeaturedProduct(){
        new HomePage(driver).clickOnFirstProduct();
    }

    @Test(priority = 5)
    public void verifyItemFeaturedImage(){
        Assert.assertTrue(new ItemPage(driver).verifyFeaturedImage());
    }

    @Test(priority = 6)
    public void placeBid() throws InterruptedException {
        new ItemPage(driver).placeBid(new ItemPage(driver).extractEnterPriceMsg());
    }

    @Test(priority = 7)
    public void verifyAlertMsg(){
        Assert.assertTrue(new ItemPage(driver).verifyAlertMsg());
    }

}
