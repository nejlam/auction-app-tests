package smoke;


import com.opencsv.CSVReader;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_objects.auction_app.*;
import testUtils.TestBase;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddNewProduct extends TestBase {

    final static private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    final static private Date today = new Date();
    final static private String COUNTRY = "Bosnia and Herzegovina";
    final static private String ZIPCODE = "71000";
    final static private String PHONE = "61123123";
    final static private String SUCCESS_ALERT = "You have successfully added a new product!";
    final static private String PHOTO_FILE_PATH = System.getProperty("user.dir")+"\\data\\product_photos\\img";
    final static private String PHOTO_EXTENSION = ".jpg";
    final static private int PHOTOS_QUANTITY = 10;
    final static private String NAME_ON_CARD = "Beth";
    final static private String CARD_NUMBER = "1111111111111";
    final static private String CVC = "1234";
    final static private String SELLER_TAB = "SELLER";
    final static private String PRODUCT_INFO_TEXT = "DETAIL INFORMATION ABOUT PRODUCT";
    final static private String PRICES_INFO_TEXT = "SET PRICES";
    final static private String LOCATION_SHIPPING_INFO_TEXT = "LOCATION & SHIPPING";


    @BeforeTest
    public String getEmail() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("data/data.csv"));
        String[] email = reader.readNext();
        return email[0];
    }

    public Lorem getLorem(){
        return LoremIpsum.getInstance();
    }

    private String getToday(){
        return dateFormat.format(today);
    }

    //adds 1 day to the current date
    private String getEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 1);
        Date currentDatePlusOneDay = calendar.getTime();
        return dateFormat.format(currentDatePlusOneDay);
    }

    @Test(priority=0)
    public void verifyHomepage(){
        new HomePage(driver).verifyHomepageLink(new RegisterAccount().getHomeActiveLinkValue());
    }

    @Test(priority = 1)
    public void openLoginPage(){
        new HomePage(driver).clickLoginLink();
    }

    @Test(priority = 2)
    public void populateLoginForm() throws IOException {
        new LoginPage(driver).populateLoginForm(getEmail(), new RegisterAccount().getPassword());
    }

    @Test(priority = 3)
    public void verifyHomePageAfterLogin(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(new RegisterAccount().getHomeActiveLinkValue()));
    }

    @Test(priority = 4)
    public void openAccountPage(){
        new HomePage(driver).clickAccountPageLink();
    }

    @Test(priority = 5)
    public void openSellerPage(){
        new AccountPage(driver).clickTab(1);
    }

    @Test(priority = 6)
    public void verifySellerPage(){
        Assert.assertTrue(new SellerPage(driver).verifyActiveTab(SELLER_TAB));
    }

    @Test(priority = 7)
    public void openSellWizard(){
        new SellerPage(driver).clickStartSellingBtn();
    }

    @Test(priority = 8)
    public void verifyProductInfoStep(){
        Assert.assertTrue(new SellPageProductInfo(driver).verifyStepTitle(PRODUCT_INFO_TEXT));
    }

    @Test(priority = 9)
    public void populateProductInfo() throws InterruptedException {
        new SellPageProductInfo(driver).populateForm(getLorem().getTitle(2,5), getLorem().getWords(5,10),
                PHOTO_FILE_PATH, PHOTO_EXTENSION, PHOTOS_QUANTITY);
    }

    @Test(priority = 10)
    public void verifyAddedPhotos(){
        Assert.assertTrue(new SellPageProductInfo(driver).verifyNumberOfAddedPhotos(PHOTOS_QUANTITY));
    }

    @Test(priority = 11)
    public void verifyPriceInfoStep(){
        Assert.assertTrue(new SellPageSetPrices(driver).verifyStepTitle(PRICES_INFO_TEXT));
    }

    @Test(priority = 12)
    public void populatePriceInfo(){
        new SellPageSetPrices(driver).populateForm(getToday(), getEndDate());
    }

    @Test(priority = 13)
    public void verifyLocationAndShippingStep(){
        Assert.assertTrue(new SellPageLocationAndShipping(driver).verifyStepTitle(LOCATION_SHIPPING_INFO_TEXT));
    }

    @Test(priority = 14)
    public void populateLocationInfo(){
        new SellPageLocationAndShipping(driver).populateLocationForm(getLorem().getWords(1), COUNTRY, ZIPCODE, PHONE);
    }

    @Test(priority = 15)
    public void chooseShippingCostBear(){
        new SellPageLocationAndShipping(driver).clickShippingPayment();
    }

    @Test(priority = 16)
    public void chooseFeatureProduct(){
        new SellPageLocationAndShipping(driver).clickFeatureProductPayment();
    }

    @Test(priority = 17)
    public void populatePaymentInfo(){
        new SellPageLocationAndShipping(driver).populateCardPaymentForm(NAME_ON_CARD, CARD_NUMBER, CVC);
    }

    @Test(priority = 18)
    public void finishAddingItem(){
        new SellPageLocationAndShipping(driver).clickDoneBtn();
    }

    @Test(priority = 19)
    public void verifyProductIsAdded(){
       Assert.assertTrue(new ItemPage(driver).verifySuccessfulAdd(SUCCESS_ALERT));
    }

}
