package regression;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_objects.auction_app.*;
import smoke.RegisterAccount;
import testUtils.TestBase;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateRequiredProfileInfo extends TestBase {
    final private static String FIRST_NAME = "Anny";
    final private static String LAST_NAME = "Bah";
    final private static String PHONE = "61123123";
    final private static String GENDER = "Female";
    final private static String EXPECTED_UPDATE_SUCCESS_MSG = "You have successfully updated your profile info!";
    final static private String EXPECTED_ACCOUNT_SUCCESS_MSG = "Account created successfully";
    final static private String EXISTING_EMAIL = "user_n@gmail.com";
    final static private String EXPECTED_EMAIL_ERROR_MSG = "Email already in use";

    private ProfilePage getProfilePage(){
        return new ProfilePage(driver);
    }

    @BeforeTest
    public String getEmail() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmm");
        Date date = new Date();
        String emailAddress = formatter.format(date)+"nana@gmail.com";
        return emailAddress;
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
    public void populateRegistrationForm() throws IOException {
        new RegistrationPage(driver).populateForm(FIRST_NAME, LAST_NAME, getEmail(), new RegisterAccount().getPassword());
    }

    @Test(priority = 4)
    public void verifyAccountIsRegistered(){
        Assert.assertTrue(new AccountPage(driver).verifySuccessMessage(EXPECTED_ACCOUNT_SUCCESS_MSG));
    }

    @Test(priority = 5)
    public void closeAlertBtn(){
        new AccountPage(driver).closeAlertBtn();
    }

    @Test(priority = 7)
    public void clickSaveInfo(){
        getProfilePage().clickSaveBtn();
    }

    @Test(priority = 8)
    public void verifySaveAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertPresence(getProfilePage().getSaveAlert()));
    }

    @Test(priority = 9)
    public void verifyGenderAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getGenderAlert().getText(), "Gender"));
    }

    @Test(priority = 10)
    public void verifyMonthAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getMonthAlert().getText(), "Month"));
    }

    @Test(priority = 11)
    public void verifyDayAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getDayAlert().getText(), "Day"));
    }

    @Test(priority = 12)
    public void verifyYearAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getYearAlert().getText(), "Year"));
    }

    @Test(priority = 13)
    public void verifyPhoneAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getPhoneAlert().getText(), "Phone number"));
    }

    @Test(priority = 14)
    public void clearFieldsAgain(){
        getProfilePage().clearFields();
    }

    @Test(priority = 15)
    public void populateRequiredFields() throws IOException {
        getProfilePage().populateRequiredFields(FIRST_NAME,LAST_NAME, GENDER, PHONE, getEmail());
    }

    @Test(priority = 16)
    public void clickSaveBtn(){
        new ProfilePage(driver).clickSaveBtn();
    }

    @Test(priority = 17)
    public void verifySuccessMsg(){
       Assert.assertTrue(new AccountPage(driver).verifySuccessMessage(EXPECTED_UPDATE_SUCCESS_MSG));
    }

    @Test(priority = 18)
    public void closeAlert(){
        new AccountPage(driver).closeAlertBtn();
    }

    @Test(priority = 19)
    public void changeEmailToExistingOne(){
        getProfilePage().clearEmailAndAddNew(EXISTING_EMAIL);
    }

    @Test(priority = 20)
    public void clickSaveButton(){
        getProfilePage().clickSaveBtn();
    }

    @Test(priority = 21)
    public void verifyErrorMsg(){
        Assert.assertTrue(new AccountPage(driver).verifySuccessMessage(EXPECTED_EMAIL_ERROR_MSG));
    }

}
