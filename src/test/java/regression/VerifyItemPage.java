package regression;


import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ItemPage;
import page_objects.auction_app.LoginPage;
import testUtils.TestBase;

public class VerifyItemPage extends TestBase {
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String EMAIL = "t1@gmail.com";
    final static private String PASSWORD = "aaaaaaaa";

    @Test(priority = 0)
    public void verifyHomePage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 1)
    public void selectItemFromFeaturedProducts(){
        new HomePage(driver).selectRandomFeaturedProduct();
    }

    @Test(priority = 3)
    public void verifyItemImage(){
        Assert.assertTrue(new ItemPage(driver).verifyFeaturedImage());
    }

    @Test(priority = 4)
    public void verifyBidButton(){
        Assert.assertTrue(new ItemPage(driver).verifyBidButton());
    }

    @Test(priority = 5)
    public void verifyItemStartPrice(){
        Assert.assertTrue(new ItemPage(driver).verifyStartPriceMsg());
    }

    @Test(priority = 6)
    public void verifyBidInputField(){
        Assert.assertTrue(new ItemPage(driver).verifyBidInputField());
    }

    @Test(priority = 7)
    public void verifyDetailsSection(){
        Assert.assertTrue(new ItemPage(driver).verifyDetailsSection());
    }

    @Test(priority = 8)
    public void verifyBidsInfo(){
        Assert.assertTrue(new ItemPage(driver).verifyBidsInfoSection());
    }

    @Test(priority = 9)
    public void verifyRelatedProductsSection(){
        Assert.assertTrue(new ItemPage(driver).verifyRelatedItemsSection());
    }

    @Test(priority = 10)
    public void verifyWishlistBtn(){
        Assert.assertTrue(new ItemPage(driver).verifyWishlistBtn());
    }

    @Test(priority = 11)
    public void clickLoginButton(){
        new ItemPage(driver).clickLoginButton();
    }

    @Test(priority = 12)
    public void populateLoginForm(){
        new LoginPage(driver).populateLoginForm(EMAIL, PASSWORD);
    }

    @Test(priority = 13)
    public void addToWishlist(){
        new ItemPage(driver).clickWishlistBtn();
    }

    @Test(priority = 14)
    public void verifyAlertBtn(){
        Assert.assertTrue(new ItemPage(driver).verifyAlertMsg());
        new ItemPage(driver).closeAlertBtn();
    }

    @Test (priority = 15)
    public void placeStartPriceBid(){
        new ItemPage(driver).placeBid(new ItemPage(driver).extractEnterPriceMsg());
    }

    @Test(priority = 16)
    public void verifyAlertButton(){
        new ItemPage(driver).verifyAlertMsg();
        new ItemPage(driver).closeAlertBtn();
    }

    @Test(priority = 17)
    public void verifyBidsTable(){
        Assert.assertTrue(new ItemPage(driver).verifyBidsTable());
    }

    @Test(priority = 18)
    public void placeHighestBid() throws InterruptedException {
        new ItemPage(driver).placeBid(new ItemPage(driver).getNewHighestBidValue());
    }

    @Test(priority = 19)
    public void verifyAlertMsg(){
        new ItemPage(driver).verifyAlertMsg();
        new ItemPage(driver).closeAlertBtn();
    }

    @Test(priority = 20)
    public void verifyBidIsTheHighestInTable(){
        Assert.assertTrue(new ItemPage(driver).verifyHighestBidInTable());
    }
}
