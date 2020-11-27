package regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ItemPage;
import page_objects.auction_app.LoginPage;
import testUtils.TestBase;

public class BidProductLoggedOut extends TestBase {

    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String ALERT_MSG = "You have to be logged in to place bids.";

    @Test(priority = 0)
    public void verifyHomePage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 1)
    public void selectItemFromFeaturedProducts(){
        new HomePage(driver).selectRandomFeaturedProduct();
    }

    @Test(priority = 2)
    public void verifyItemImage(){
        Assert.assertTrue(new ItemPage(driver).verifyFeaturedImage());
    }

    @Test (priority = 3)
    public void placeStartPriceBid() throws InterruptedException {
        new ItemPage(driver).checkEnterMsgAndPlaceBid();
    }

    @Test(priority = 4)
    public void verifyAlertMsg(){
        Assert.assertTrue(new ItemPage(driver).verifyAlertMsgTxt(ALERT_MSG));
    }

}
