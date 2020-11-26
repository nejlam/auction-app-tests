package regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.ShopPage;
import testUtils.TestBase;

public class SortProductsByDefault extends TestBase {

    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
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
        new ShopPage(driver).verifySortDefault();
    }
}
