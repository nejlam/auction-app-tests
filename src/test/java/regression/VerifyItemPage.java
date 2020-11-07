package regression;


import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ItemPage;
import page_objects.auction_app.LoginPage;
import testUtils.TestBase;

public class VerifyItemPage extends TestBase {
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String EMAIL = "betharmon@gmail.com";
    final static private String PASSWORD = "12345678";

    @Test(priority = 0)
    public void verifyHomePage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 1)
    public void selectItemFromFeaturedProducts(){
        new HomePage(driver).selectRandomFeaturedProduct();
    }

    @Test(priority = 2)
    public void verifyItemTitle(){

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
    public void verifyNumberOfItemsInRelatedProducts(){
        Assert.assertEquals(new ItemPage(driver).verifyNumberOfRelatedItems(), 4);
    }

    @Test(priority = 11)
    public void verifyWishlistBtn(){
        Assert.assertTrue(new ItemPage(driver).verifyWishlistBtn());
    }

    @Test(priority = 12)
    public void clickLoginButton(){
        new ItemPage(driver).clickLoginButton();
    }

    @Test(priority = 13)
    public void populateLoginForm(){
        new LoginPage(driver).populateLoginForm(EMAIL, PASSWORD);
    }

    @Test(priority = 14)
    public void verifyBidsTable(){
        Assert.assertTrue(new ItemPage(driver).verifyBidsTable());
    }

    @Test(priority = 15)
    public void addHighestBid() throws InterruptedException {
        new ItemPage(driver).placeBid(new ItemPage(driver).extractHighestBid());
    }

    @Test(priority = 16)
    public void verifyAlertMessage(){
        new ItemPage(driver).verifyAlertMsg();
    }

    @Test(priority = 17)
    public void verifyBidIsTheHighestInTable(){

    }
}
