package regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ItemPage;
import page_objects.auction_app.LoginPage;
import page_objects.auction_app.ShopPage;
import testUtils.TestBase;

import java.io.IOException;

public class BidProductFromFashionCategory extends TestBase {
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String PASSWORD = "11111111";
    final static private String EMAIL = "bethharmon@gmail.com";
    int fashionCategoryIndex = 0;
    final static private String FASHION = "FASHION";


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
    public void selectCategory(){
        new HomePage(driver).clickCategory(fashionCategoryIndex);
    }

    @Test(priority = 5)
    public void verifyCategory(){
        Assert.assertTrue(new ShopPage(driver).verifyCategoryPage(FASHION));
    }

    @Test(priority = 6)
    public void selectFirstItem(){
        new ShopPage(driver).clickFirstItem();
    }

    //Verify first item

    @Test(priority = 8)
    public void placeBid() throws InterruptedException {
        new ItemPage(driver).placeBid();
    }

    @Test(priority = 9)
    public void verifyAlertMsg(){
        Assert.assertTrue(new ItemPage(driver).verifyAlertMsg());
    }
}
