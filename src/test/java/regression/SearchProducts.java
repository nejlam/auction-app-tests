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
    final static private String PRICE_DESC = "price";
    final static private String PRICE_ASC = "price_asc";

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

    @Test(priority = 7)
    public void sortResultsPriceAsc(){
        new ShopPage(driver).selectSortOption(PRICE_ASC);
    }

    @Test(priority = 8)
    public void verifySortPriceAsc(){
        Assert.assertTrue(new ShopPage(driver).verifySortPrices(PRICE_ASC));
    }

    @Test(priority = 9)
    public void sortResultsPriceDesc(){
        new ShopPage(driver).selectSortOption(PRICE_DESC);
    }

    @Test(priority = 10)
    public void verifySortPriceDesc(){
        Assert.assertTrue(new ShopPage(driver).verifySortPrices(PRICE_DESC));
    }


}
