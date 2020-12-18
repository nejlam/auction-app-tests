package smoke;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.*;
import testUtils.TestBase;

public class PayForProduct extends TestBase {
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String PASSWORD = "aaaaaaaa";
    final static private String EMAIL = "t1@gmail.com";
    final static private String NAME_ON_CARD = "Beth";
    final static private String CARD_NUMBER = "4242424242424242";
    final static private String CVC = "123";
    final static private String COUNTRY = "Bosnia and Herzegovina";
    final static private String ZIPCODE = "71000";
    final static private String PHONE = "";
    final static private String ADDRESS = "address";
    final static private String SUCCESS_ALERT_PARTIAL = "You have successfully made a payment for";

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
    public void openAccountPage(){
        new HomePage(driver).clickNavbarTab(2);
    }

    @Test(priority = 5)
    public void openBidsPage(){
        new AccountPage(driver).clickTab(2);
    }

    @Test(priority = 6)
    public void clickPayButton(){
        new BidsPage(driver).clickPayBtn();
    }

    @Test(priority = 7)
    public void populateLocationInfo(){
        new PaymentPage(driver).populateLocationInfo(ADDRESS, COUNTRY, ZIPCODE, PHONE);
    }

    @Test(priority = 8)
    public void clickCardPaymentCheck(){
        new PaymentPage(driver).clickCreditCardCheckbox();
    }

    @Test(priority = 9)
    public void populateCreditCardInfo(){
        new PaymentPage(driver).populateCreditCardInfo(NAME_ON_CARD, CARD_NUMBER, CVC);
    }

    @Test(priority = 10)
    public void clickDoneBtn(){
        new PaymentPage(driver).clickDoneBtn();
    }

    @Test(priority = 11)
    public void clickSkipRatingBtn(){
        new PaymentPage(driver).clickSkipRatingBtn();
    }

    @Test(priority = 12)
    public void verifySuccessMsg(){
        Assert.assertTrue(new BidsPage(driver).verifyPaymentSuccessAlert(SUCCESS_ALERT_PARTIAL));
    }

    @Test(priority = 13)
    public void closeAlertBtn(){
        new BidsPage(driver).closeAlertMsg();
    }

}
