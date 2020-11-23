package smoke;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import junit.framework.TestCase;
import org.testng.Assert;
import org.testng.annotations.*;
import page_objects.auction_app.AccountPage;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.RegistrationPage;
import testUtils.TestBase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterAccount extends TestBase {
    final static private String FIRST_NAME = "Beth";
    final static private String LAST_NAME = "Harmon";
    final static private String EMAIL = "betharmon@gmail.com";
    final static private String PASSWORD = "12345678";
    final static private String EXPECTED_ACCOUNT_SUCCESS_MSG = "Account created successfully";
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";


    @BeforeTest
    public String getEmail() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmm");
        Date date = new Date();
        String emailAddress = formatter.format(date)+EMAIL;
        //write data to the csv file
        CSVWriter writer = new CSVWriter(new FileWriter("data/data.csv"));
        String[] email = {emailAddress};
        writer.writeNext(email);
        writer.flush();
        return emailAddress;
    }

    @Test(priority = 0)
    public void verifyHomepage(){
        Assert.assertTrue((new HomePage(driver)).verifyHomepageLink(ACTIVE_LINK_ATTRIBUTE_VALUE));
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
    public void populateRegistrationForm() throws IOException {
        new RegistrationPage(driver).populateForm(FIRST_NAME, LAST_NAME, getEmail(), PASSWORD);
    }

    @Test(priority = 4)
    public void verifyAccountIsRegistered(){
        Assert.assertTrue(new AccountPage(driver).verifySuccessMessage(EXPECTED_ACCOUNT_SUCCESS_MSG));
    }
}
