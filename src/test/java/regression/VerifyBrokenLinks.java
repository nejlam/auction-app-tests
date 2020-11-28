package regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import testUtils.TestBase;

public class VerifyBrokenLinks extends TestBase {
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";

    @Test(priority = 0)
    public void verifyHomepage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
    }

    @Test(priority = 1)
    public void verifyBrokenLinks(){
        Assert.assertFalse(new HomePage(driver).checkBrokenLinks());
    }

}
