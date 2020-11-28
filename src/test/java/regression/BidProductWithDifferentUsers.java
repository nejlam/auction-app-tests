package regression;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ItemPage;
import page_objects.auction_app.LoginPage;
import page_objects.auction_app.ShopPage;
import testUtils.TestBase;

public class BidProductWithDifferentUsers extends TestBase {

    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String SEARCH_QUERY = "Ketchup";
    final static private String PASSWORD = "aaaaaaaa";
    final static private String HIGHEST_BID_SUCCESS_MSG = "Congratulations! You are the highest bidder!";

    @Test(priority = 0)
    public void verifyHomePage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 1)
    public void openLoginPage(){
        new HomePage(driver).clickLoginLink();
    }

    @Test(priority = 2)
    @Parameters("email")
    public void populateLoginForm(String email) {
        new LoginPage(driver).populateLoginForm(email, PASSWORD);
    }

    @Test(priority = 3)
    public void verifyHomePageAfterLogin(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 5)
    public void searchItem() throws InterruptedException {
        new HomePage(driver).searchItem(SEARCH_QUERY);
    }

    @Test(priority = 6)
    public void verifySearchInputValue(){
        Assert.assertTrue(new HomePage(driver).verifySearchInput(SEARCH_QUERY));
    }

    @Test(priority = 7)
    public void verifySearchedItem(){
        Assert.assertTrue(new ShopPage(driver).verifyFirstItem(SEARCH_QUERY));
    }

    @Test(priority = 8)
    public void clickTheFirstItem(){
        new ShopPage(driver).clickFirstItem();
    }

    @Test(priority = 9)
    public void placeHighestBid() throws InterruptedException {
        new ItemPage(driver).placeBid(new ItemPage(driver).getNewHighestBidValue());
    }

    @Test(priority = 10)
    public void verifyAlertMsgTxt(){
       Assert.assertTrue(new ItemPage(driver).verifyAlertMsgTxt(HIGHEST_BID_SUCCESS_MSG));
       new ItemPage(driver).closeAlertBtn();
    }

    @Test(priority = 11)
    public void verifyBidIsTheHighestInTable(){
        Assert.assertTrue(new ItemPage(driver).verifyHighestBidInTable());
    }

}
