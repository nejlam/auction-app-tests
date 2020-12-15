package regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ItemPage;
import page_objects.auction_app.LoginPage;
import page_objects.auction_app.ShopPage;
import testUtils.TestBase;

public class BidProductFromEveryCategory extends TestBase {
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String PASSWORD = "aaaaaaaa";
    final static private String EMAIL = "c@gmail.com";


    @Test(priority = 0)
    public void verifyHomePage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 1)
    public void openLoginPage(){
        new HomePage(driver).clickLoginLink();
    }

    @Test(priority = 2)
    public void populateLoginForm() {
        new LoginPage(driver).populateLoginForm(EMAIL, PASSWORD);
    }

    @Test(priority = 3)
    public void verifyHomePageAfterLogin(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 4)
    public void selectRandomCategory(){
        new HomePage(driver).clickRandomCategory();
    }


    @Test(priority = 6)
    public void selectRandomItem(){
        new ShopPage(driver).selectRandomItem();
    }

    @Test(priority = 9)
    public void placeBid() throws InterruptedException {
        new ItemPage(driver).checkEnterMsgAndPlaceBid();
    }

    @Test(priority = 10)
    public void verifyAlertMsg(){
        Assert.assertTrue(new ItemPage(driver).verifyAlertMsg());
    }

}
