package regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ShopPage;
import testUtils.TestBase;

public class SearchProducts extends TestBase {

    final private static String UNIQUE_QUERY = "shoes";
    final private static String PARTIAL_QUERY = "sho";
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";

    @Test(priority = 0)
    public void verifyHomePage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 1)
    public void searchItemFull() throws InterruptedException {
        new HomePage(driver).searchItem(UNIQUE_QUERY);
    }

    @Test(priority = 2)
    public void verifySearchInputValueFull(){
        Assert.assertTrue(new HomePage(driver).verifySearchInput(UNIQUE_QUERY));
    }

    @Test(priority = 3)
    public void verifySearchResultFull(){
        Assert.assertTrue(new ShopPage(driver).verifySearchResults(UNIQUE_QUERY, "unique"));
    }

    @Test(priority = 4)
    public void searchItemPartial() throws InterruptedException {
        new HomePage(driver).searchItem(PARTIAL_QUERY);
    }

    @Test(priority = 5)
    public void verifySearchInputValuePartial(){
        Assert.assertTrue(new HomePage(driver).verifySearchInput(PARTIAL_QUERY));
    }

    @Test(priority = 6)
    public void verifySearchResultPartial(){
        Assert.assertTrue(new ShopPage(driver).verifySearchResults(PARTIAL_QUERY, "partial"));
    }
}
