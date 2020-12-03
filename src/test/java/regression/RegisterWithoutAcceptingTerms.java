package regression;

import com.opencsv.CSVWriter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_objects.auction_app.AccountPage;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.RegistrationPage;
import testUtils.TestBase;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterWithoutAcceptingTerms extends TestBase {
    final static private String FIRST_NAME = "Beth";
    final static private String LAST_NAME = "Harmon";
    final static private String EMAIL = "betharmon@gmail.com";
    final static private String PASSWORD = "12345678";
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String ERROR_MSG = "*Please accept our terms";


    @BeforeTest
    public String getEmail() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmm");
        Date date = new Date();
        String emailAddress = formatter.format(date)+EMAIL;
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
    public void populateRegistrationForm() throws IOException {
        new RegistrationPage(driver).populateForm(FIRST_NAME, LAST_NAME, getEmail(), PASSWORD);
    }

    @Test(priority = 3)
    public void verifyErrorMsg(){
        Assert.assertTrue(new RegistrationPage(driver).verifyTermsAndConditionsErrorMsg(ERROR_MSG));
    }
}
