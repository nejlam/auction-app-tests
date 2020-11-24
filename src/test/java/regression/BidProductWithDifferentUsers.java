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
    final static private String SEARCH_QUERY = "Product";
    final static private String PASSWORD = "aaaaaaaa";
    final static private String HIGHEST_BID_SUCCESS_MSG = "Congratulations! You are the highest bider!";
    String email = System.getProperty("email");


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
        new LoginPage(driver).populateLoginForm(email, PASSWORD);
    }

    @Test(priority = 3)
    public void verifyHomePageAfterLogin(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 4)
    public void searchProduct(){
        new HomePage(driver).searchProduct(SEARCH_QUERY);
    }

    @Test(priority = 5)
    public void verifySearchedProduct(){
        Assert.assertTrue(new ShopPage(driver).verifyFirstItem(SEARCH_QUERY));
    }

    @Test(priority = 6)
    public void clickTheFirstProduct(){
        new ShopPage(driver).clickFirstItem();
    }

    @Test(priority = 7)
    public void placeHighestBid(){
        new ItemPage(driver).placeBid(new ItemPage(driver).getNewHighestBidValue());
    }

    @Test(priority = 8)
    public void verifyAlertMsgTxt(){
       new ItemPage(driver).verifyAlertMsgTxt(HIGHEST_BID_SUCCESS_MSG);
    }

    @Test(priority = 20)
    public void verifyBidIsTheHighestInTable(){
        Assert.assertTrue(new ItemPage(driver).verifyHighestBidInTable());
    }

}
