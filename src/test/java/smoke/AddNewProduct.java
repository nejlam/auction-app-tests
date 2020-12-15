package smoke;


import com.opencsv.CSVReader;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page_objects.auction_app.*;
import testUtils.TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddNewProduct extends TestBase {

    final private static String FIRST_NAME = "Anny";
    final private static String LAST_NAME = "Bah";
    final private static String PASSWORD = "aaaaaaaa";
    final static private String COUNTRY = "Bosnia and Herzegovina";
    final static private String ZIPCODE = "71000";
    final static private String PHONE = "61123123";
    final static private String SUCCESS_ALERT = "You have successfully added a new product!";
    final static private String PHOTO_FILE_PATH = System.getProperty("user.dir")+ File.separator + "data" + File.separator + "product_photos" + File.separator +"img";
    final static private String PHOTO_EXTENSION = ".jpg";
    final static private int PHOTOS_QUANTITY = 3;
    final static private String NAME_ON_CARD = "Beth";
    final static private String CARD_NUMBER = "4242424242424242";
    final static private String CVC = "123";
    final static private String SELLER_TAB = "Seller";
    final static private String PRODUCT_INFO_TEXT = "DETAIL INFORMATION ABOUT PRODUCT";
    final static private String PRICES_INFO_TEXT = "SET PRICES";
    final static private String LOCATION_SHIPPING_INFO_TEXT = "LOCATION & SHIPPING";
    final private String NEW_ITEM_TITLE = getLorem().getWords(2,4);
    final static private String EXPECTED_ACCOUNT_SUCCESS_MSG = "Account created successfully";


    @BeforeTest
    private String getEmail(){
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmm");
        Date date = new Date();
        return formatter.format(date)+"nana@gmail.com";
    }

    public Lorem getLorem(){
        return LoremIpsum.getInstance();
    }

    @Test(priority=0)
    public void verifyHomepage(){
        new HomePage(driver).verifyHomepageLink(new RegisterAccount().getHomeActiveLinkValue());
    }

    @Test(priority = 1)
    public void openRegistrationPage(){
        new HomePage(driver).clickCreateAccountLink();
    }


    @Test(priority = 2)
    public void checkAgreeToTerms(){
        new RegistrationPage(driver).clickAgreeToTermsCheck();
    }

    @Test(priority = 3)
    public void populateRegistrationForm() {
        new RegistrationPage(driver).populateForm(FIRST_NAME, LAST_NAME, getEmail(), PASSWORD);
    }

    @Test(priority = 4)
    public void verifyAccountIsRegistered(){
        Assert.assertTrue(new AccountPage(driver).verifySuccessMessage(EXPECTED_ACCOUNT_SUCCESS_MSG));
    }

    @Test(priority = 5)
    public void closeAlertBtn(){
        new AccountPage(driver).closeAlertBtn();
    }

    @Test(priority = 6)
    public void openSellerPage(){
        new AccountPage(driver).clickTab(1);
    }

    @Test(priority = 7)
    public void verifySellerPage(){
        Assert.assertTrue(new AccountPage(driver).verifyActiveTab(SELLER_TAB, 1));
    }

    @Test(priority = 8)
    public void openSellWizard(){
        new SellerPage(driver).clickStartSellingBtn();
    }

    @Test(priority = 9)
    public void verifyProductInfoStep(){
        Assert.assertTrue(new SellPageProductInfo(driver).verifyStepTitle(PRODUCT_INFO_TEXT));
    }

    @Test(priority = 10)
    public void populateProductInfo(){
        new SellPageProductInfo(driver).populateForm(NEW_ITEM_TITLE, getLorem().getWords(5,10),
                PHOTO_FILE_PATH, PHOTO_EXTENSION, PHOTOS_QUANTITY);
    }
/* EXECUTE FILES UPLOAD ONLY LOCALLY
    @Test(priority = 10)
    public void verifyAddedPhotos(){
        Assert.assertTrue(new SellPageProductInfo(driver).verifyNumberOfAddedPhotos(PHOTOS_QUANTITY));
    }

    @Test(priority = 11)
    public void verifyMsgAfterPhotosUpload(){
        Assert.assertTrue(new SellPageProductInfo(driver).verifyMsgForPhotoUploads(PHOTOS_QUANTITY));
    }
*/
    @Test(priority = 12)
    public void verifyPriceInfoStep(){
        Assert.assertTrue(new SellPageSetPrices(driver).verifyStepTitle(PRICES_INFO_TEXT));
    }

    @Test(priority = 13)
    public void setPriceInfo(){
        new SellPageSetPrices(driver).setStartPrice();
    }

    @Test(priority = 14)
    @Parameters("status")
    public void setDatesInfo(String status){
        new SellPageSetPrices(driver).setDatesAndClickNext(status);
    }

    @Test(priority = 15)
    public void verifyLocationAndShippingStep(){
        Assert.assertTrue(new SellPageLocationAndShipping(driver).verifyStepTitle(LOCATION_SHIPPING_INFO_TEXT));
    }

    @Test(priority = 16)
    public void populateLocationInfo(){
        new SellPageLocationAndShipping(driver).populateLocationForm(getLorem().getWords(1), COUNTRY, ZIPCODE, PHONE);
    }

    @Test(priority = 17)
    public void chooseShippingCostBear(){
        new SellPageLocationAndShipping(driver).clickShippingPayment();
    }

    @Test(priority = 18)
    public void chooseFeatureProduct(){
        new SellPageLocationAndShipping(driver).clickFeatureProductPayment();
    }

    @Test(priority = 19)
    public void populatePaymentInfo(){
        new SellPageLocationAndShipping(driver).populateCardPaymentForm(NAME_ON_CARD, CARD_NUMBER, CVC);
    }

    @Test(priority = 20)
    public void finishAddingItem() throws InterruptedException {
        new SellPageLocationAndShipping(driver).clickDoneBtn();
    }

    @Test(priority = 21)
    public void verifyProductIsAdded(){
       Assert.assertTrue(new ItemPage(driver).verifySuccessfulAdd(SUCCESS_ALERT));
    }

    @Test(priority = 22)
    public void openAccountPageForVerification(){
        new HomePage(driver).clickNavbarTab(2);
    }

    @Test(priority = 23)
    public void openSellerPageForVerification(){
        new AccountPage(driver).clickTab(1);
    }

    @Test(priority = 24)
    @Parameters("status")
    public void openSellerTab(String status){
        new SellerPage(driver).openSellerTab(status);
    }

    @Test(priority = 25)
    @Parameters("status")
    public void verifySellerTab(String status){
       Assert.assertTrue(new SellerPage(driver).verifyActiveTab(status));
    }

    @Test(priority = 26)
    public void verifyNewItemInTable() throws InterruptedException {
        Assert.assertTrue(new SellerPage(driver).verifyItemInTable(NEW_ITEM_TITLE));
    }
}
