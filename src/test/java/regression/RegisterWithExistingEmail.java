package regression;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.HomePage;
import page_objects.auction_app.RegistrationPage;
import testUtils.TestBase;

import java.io.IOException;

public class RegisterWithExistingEmail extends TestBase {
    final static private String FIRST_NAME = "Beth";
    final static private String LAST_NAME = "Harmon";
    final static private String EXISTING_EMAIL = "user_n@gmail.com";
    final static private String PASSWORD = "12345678";
    final static private String ACTIVE_LINK_ATTRIBUTE_VALUE = "black-active-nav-link";
    final static private String EXPECTED_EMAIL_ERROR_MSG = "Email already in use";


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
        new RegistrationPage(driver).populateForm(FIRST_NAME, LAST_NAME, EXISTING_EMAIL, PASSWORD);
    }

    @Test(priority = 4)
    public void verifyErrorMsg(){
        Assert.assertTrue(new RegistrationPage(driver).verifyAlertMsgTxt(EXPECTED_EMAIL_ERROR_MSG));
    }

}
