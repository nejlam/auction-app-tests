package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;
import java.util.concurrent.ThreadLocalRandom;

public class ProfilePage extends PageBase {
    final private static String PAGE_URL_REGEX = "\\/my_account\\d*";
    final private static String CHANGE_PHOTO_BTN_XPATH = "//*[@id=\"root\"]/div/div[3]/div/form/div[1]/div[2]/div[1]/button[1]";
    final private static String FIRST_NAME_INPUT_NAME = "firstName";
    final private static String LAST_NAME_INPUT_NAME = "lastName";
    final private static String GENDER_DROPDOWN_NAME = "gender";
    final private static String BIRTH_MONTH_NAME = "month";
    final private static String BIRTH_DAY_NAME = "day";
    final private static String BIRTH_YEAR_NAME = "year";
    final private static String PHONE_INPUT_NAME = "phone";
    final private static String EMAIL_INPUT_NAME = "email";
    final private static String VERIFIED_BTN_XPATH = "//*[@id='root']/div/div[3]/div/form/div[1]/div[2]/div[2]/div[5]/div/div[1]";
    final private static String SAVE_BTN = "//*[@id='root']/div/div[3]/div/form/div[4]/button[2]";
    final private static String GENDER_ALERT = "//*[@id='root']/div/div[3]/div/form/div[1]/div[2]/div[2]/div[3]/div";
    final private static String MONTH_ALERT = "//*[@id='root']/div/div[3]/div/form/div[1]/div[2]/div[2]/div[4]/div[2]/div[1]/div";
    final private static String DAY_ALERT = "//*[@id='root']/div/div[3]/div/form/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/div";
    final private static String YEAR_ALERT = "//*[@id='root']/div/div[3]/div/form/div[1]/div[2]/div[2]/div[4]/div[2]/div[3]/div";
    final private static String PHONE_ALERT = "//*[@id='root']/div/div[3]/div/form/div[1]/div[2]/div[2]/div[5]/div/div[2]";
    final private static String EMAIL_ALERT = "//*[@id='root']/div/div[3]/div/form/div[1]/div[2]/div[2]/div[6]/div";
    final private static String SAVE_ALERT = "//*[@id='root']/div/div[3]/div/form/div[5]";

    public ProfilePage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    private int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForVisibilityOfElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @FindBy(xpath = CHANGE_PHOTO_BTN_XPATH)
    private WebElement changePhotoBtn;

    @FindBy(name = FIRST_NAME_INPUT_NAME)
    private WebElement firstNameInput;

    @FindBy(name = LAST_NAME_INPUT_NAME)
    private WebElement lastNameInput;

    @FindBy(name = GENDER_DROPDOWN_NAME)
    private WebElement genderDropdown;

    @FindBy(name = BIRTH_DAY_NAME)
    private WebElement birthDayDropdown;

    @FindBy(name = BIRTH_MONTH_NAME)
    private WebElement birthMonthDropdown;

    @FindBy(name = BIRTH_YEAR_NAME)
    private WebElement birthYearDropdown;

    @FindBy(name = PHONE_INPUT_NAME)
    private WebElement phoneInput;

    @FindBy(name = EMAIL_INPUT_NAME)
    private WebElement emailInput;

    @FindBy(xpath = VERIFIED_BTN_XPATH)
    private WebElement verifiedBtn;

    @FindBy(xpath = SAVE_BTN)
    private WebElement saveBtn;

    @FindBy(xpath = GENDER_ALERT)
    private WebElement genderAlert;

    @FindBy(xpath = MONTH_ALERT)
    private WebElement monthAlert;

    @FindBy(xpath = YEAR_ALERT)
    private WebElement yearAlert;

    @FindBy(xpath = DAY_ALERT)
    private WebElement dayAlert;

    @FindBy(xpath = PHONE_ALERT)
    private WebElement phoneAlert;

    @FindBy(xpath = SAVE_ALERT)
    private WebElement saveAlert;

    @FindBy(xpath = EMAIL_ALERT)
    private WebElement emailAlert;

    //GETTERS

    public WebElement getSaveAlert(){
        return saveAlert;
    }

    public WebElement getPhoneAlert() {
        return phoneAlert;
    }

    public WebElement getDayAlert(){
        return dayAlert;
    }

    public WebElement getYearAlert(){
        return yearAlert;
    }

    public WebElement getMonthAlert(){
        return monthAlert;
    }

    public WebElement getGenderAlert(){
        return genderAlert;
    }

    public WebElement getSaveBtn(){
        return saveBtn;
    }

    public WebElement getVerifiedBtn(){
        return verifiedBtn;
    }

    public WebElement getEmailInput(){
        return emailInput;
    }

    public WebElement getPhoneInput(){
        return phoneInput;
    }

    public Select getBirthYearDropdown(){
        return new Select(birthYearDropdown);
    }

    public Select getBirthMonthDropdown(){
        return new Select(birthMonthDropdown);
    }

    public Select getBirthDayDropdown(){
        return new Select(birthDayDropdown);
    }

    public Select getGenderDropdown(){
        return new Select(genderDropdown);
    }

    public WebElement getLastNameInput(){
        return lastNameInput;
    }

    public WebElement getFirstNameInput(){
        return firstNameInput;
    }

    //METHODS

    public void populateRequiredFields(String firstName, String lastName, String gender, String phone, String email){
        getFirstNameInput().sendKeys(firstName);
        getLastNameInput().sendKeys(lastName);
        getGenderDropdown().selectByValue(gender);
        getBirthMonthDropdown().selectByIndex(getRandomNumber(1,12));
        getBirthDayDropdown().selectByIndex(getRandomNumber(1,12));
        getBirthYearDropdown().selectByIndex(getRandomNumber(1,12));
        getPhoneInput().sendKeys(phone);
        getVerifiedBtn().click();
        getEmailInput().sendKeys(email);
    }

    public void clearFields(){
        getFirstNameInput().clear();
        getLastNameInput().clear();
        getEmailInput().clear();
    }

    public void clickSaveBtn(){
        waitForVisibilityOfElement(getSaveBtn());
        waitForElementToBeClickable(getSaveBtn());
        getSaveBtn().click();
    }

    public boolean verifyAlertPresence(WebElement el){
        return el.isDisplayed();
    }

    public boolean verifyAlertMsg(WebElement el, String alertElement){
        waitForVisibilityOfElement(el);
        boolean alert = el.getText().contains(alertElement + " is required");
        if(alert){
        System.out.println("Alert message: " + el.getText());
        } else System.out.println("Alert is not present");
        return alert;
    }

    public void clearEmailAndAddNew(String email){
        getEmailInput().clear();
        getEmailInput().sendKeys(email);
    }

    public boolean verifyUpdatedField(WebElement el, String updatedValue){
        boolean updated = el.getAttribute("value").equals(updatedValue);
        if(updated){
            System.out.println("Updated value: " + updatedValue);
        } else System.out.println("Value is not updated");
        return updated;
    }
}
