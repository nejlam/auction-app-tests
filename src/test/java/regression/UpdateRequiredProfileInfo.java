package regression;


import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.auction_app.*;
import smoke.RegisterAccount;
import testUtils.TestBase;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateRequiredProfileInfo extends TestBase {
    final private static String FIRST_NAME = "Anny";
    final private static String LAST_NAME = "Bah";
    final private static String PHONE = "61123123";
    final private static String GENDER = "Female";
    final private static String UPDATED_FIRST_NAME = "Nanna";
    final private static String UPDATED_LAST_NAME = "Lya";
    final private static String EXPECTED_UPDATE_SUCCESS_MSG = "You have successfully updated your profile info!";
    final static private String EXPECTED_ACCOUNT_SUCCESS_MSG = "Account created successfully";
    final static private String EXISTING_EMAIL = "user_n@gmail.com";
    final static private String EXPECTED_EMAIL_ERROR_MSG = "Email already in use";
    final private String EMAIL = getEmail();

    private ProfilePage getProfilePage(){
        return new ProfilePage(driver);
    }

    private String getEmail(){
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
    public void populateRegistrationForm() {
        new RegistrationPage(driver).populateForm(FIRST_NAME, LAST_NAME, EMAIL, new RegisterAccount().getPassword());
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
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getGenderAlert(), "Gender"));
    }

    @Test(priority = 10)
    public void verifyMonthAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getMonthAlert(), "Month"));
    }

    @Test(priority = 11)
    public void verifyDayAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getDayAlert(), "Day"));
    }

    @Test(priority = 12)
    public void verifyYearAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getYearAlert(), "Year"));
    }

    @Test(priority = 13)
    public void verifyPhoneAlert(){
        Assert.assertTrue(getProfilePage().verifyAlertMsg(getProfilePage().getPhoneAlert(), "Phone number"));
    }

    @Test(priority = 14)
    public void clearFields(){
        getProfilePage().clearFields();
    }

    @Test(priority = 15)
    public void populateRequiredFields(){
        getProfilePage().populateRequiredFields(UPDATED_FIRST_NAME,UPDATED_LAST_NAME, GENDER, PHONE, EMAIL);
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
    public void verifyFirstName(){
        Assert.assertTrue(getProfilePage().verifyUpdatedField(getProfilePage().getFirstNameInput(), UPDATED_FIRST_NAME));
    }

    @Test(priority = 20)
    public void verifyLastName(){
        Assert.assertTrue(getProfilePage().verifyUpdatedField(getProfilePage().getLastNameInput(), UPDATED_LAST_NAME));
    }

    @Test(priority = 21)
    public void verifyPhoneNumber(){
        Assert.assertTrue(getProfilePage().verifyUpdatedField(getProfilePage().getPhoneInput(), PHONE));
    }

    @Test(priority = 22)
    public void verifyEmail(){
        Assert.assertTrue(getProfilePage().verifyUpdatedField(getProfilePage().getEmailInput(), EMAIL));
    }

    @Test(priority = 23)
    public void changeEmailToExistingOne(){
        getProfilePage().clearEmailAndAddNew(EXISTING_EMAIL);
    }

    @Test(priority = 24)
    public void clickSaveButton() {
        getProfilePage().clickSaveBtn();
    }

    @Test(priority = 25)
    public void verifyErrorMsg(){
        Assert.assertTrue(new AccountPage(driver).verifySuccessMessage(EXPECTED_EMAIL_ERROR_MSG));
    }

}
