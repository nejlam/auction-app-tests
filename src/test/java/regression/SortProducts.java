package regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ShopPage;
import testUtils.TestBase;

public class SortProducts extends TestBase {
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String PRICE_DESC = "price";
    final static private String PRICE_ASC = "price_asc";
    final static private String DEFAULT = "default";


    @Test(priority = 0)
    public void verifyHomepage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 1)
    public void openShopPage(){
        new HomePage(driver).clickShopPageLink();
    }

    @Test(priority = 2)
    public void selectSortDefault(){
        new ShopPage(driver).selectSortOption(DEFAULT);
    }

    @Test(priority = 3)
    public void verifySortDefault(){
        Assert.assertTrue(new ShopPage(driver).verifySortDefault());
    }

    @Test(priority = 4)
    public void selectSortPriceAsc(){
        new ShopPage(driver).selectSortOption(PRICE_ASC);
    }

    @Test(priority = 5)
    public void verifySortPriceAsc(){
        Assert.assertTrue(new ShopPage(driver).verifySortPrices(PRICE_ASC));
    }

    @Test(priority = 6)
    public void selectSortPriceDesc(){
        new ShopPage(driver).selectSortOption(PRICE_DESC);
    }

    @Test(priority = 7)
    public void verifySortPriceDesc(){
        Assert.assertTrue(new ShopPage(driver).verifySortPrices(PRICE_DESC));
    }
}
