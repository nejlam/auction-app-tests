package page_objects.auction_app;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class SellPageLocationAndShipping extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/my_account/seller\\d*";
    final static private String ADDRESS_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[1]/input";
    final static private String COUNTRY_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[1]/select";
    final static private String CITY_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[2]/select";
    final static private String CITY_DROPDOWN_VALUES = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[2]/select/option";
    final static private String ZIPCODE_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[3]/input";
    final static private String PHONE_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[4]/div/input";
    final static private String SHIPPING_CHECKBOX_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[5]/div/label";
    final static private String FEATURE_CHECKBOX_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[6]/div/label";
    final static private String PAYPAL_CHECKBOX_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[2]/label";
    final static private String CREDIT_CARD_CHECKBOX_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[3]/label";
    final static private String DONE_BTN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[8]/button[2]";
    final static private String STEP_TITLE = "//*[@id='root']/div/div[3]/div[2]/div[1]";
    //payment info vars
    final static private String NAME_ON_CARD_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[4]/div[1]/input";
    final static private String CARD_NUMBER_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[4]/div[2]/input";
    final static private String EXP_YEAR_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[1]/select";
    final static private String EXP_YEAR_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[1]/select/option";
    final static private String EXP_MONTH_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[2]/select";
    final static private String EXP_MONTH_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[2]/select/option";
    final static private String CVC_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[2]/input";
    final static private String PAYPAL_BTN_XPATH = "//*[@id='buttons-container']/div/div[1]/div";
    final static private String PAYPAL_EMAIL_INPUT_ID = "email";
    final static private String PAYPAL_PSWD_INPUT_ID = "password";
    final static private String PAYPAL_LOGIN_BTN_ID = "btnLogin";
    final static private String PAYMENT_SUBMIT_BTN = "payment-submit-btn";
    final static private String PAYPAL_IFRAME_ID = "jsx-iframe-a3650b2174";
    final static private String PAYPAL_IFRAME_XPATH = "///*[@id='zoid-paypal-buttons-1ce1ca0cea_mtc6mdk6mdq']";

    public SellPageLocationAndShipping(WebDriver driver) {
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    private int getRandomNumber(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        return randomNum;
    }

    WebDriverWait wait = new WebDriverWait(getDriver(),30);

    public void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    @FindBy(xpath = ADDRESS_INPUT_XPATH)
    private WebElement addressInput;

    @FindBy(xpath = COUNTRY_DROPDOWN_XPATH)
    private WebElement countryDropdown;

    @FindBy(xpath = CITY_DROPDOWN_XPATH)
    private WebElement cityDropdown;

    @FindBy(xpath = ZIPCODE_INPUT_XPATH)
    private WebElement zipcodeInput;

    @FindBy(xpath = PHONE_INPUT_XPATH)
    private WebElement phoneInput;

    @FindBy(xpath = SHIPPING_CHECKBOX_XPATH)
    private WebElement shippingCheckbox;

    @FindBy(xpath = FEATURE_CHECKBOX_XPATH)
    private WebElement featureCheckbox;

    @FindBy(xpath = PAYPAL_CHECKBOX_XPATH)
    private WebElement payPalCheckbox;

    @FindBy(xpath = CREDIT_CARD_CHECKBOX_XPATH)
    private WebElement creditCardCheckbox;

    @FindBy(xpath = NAME_ON_CARD_INPUT_XPATH)
    private WebElement nameOnCardInput;

    @FindBy(xpath = CARD_NUMBER_INPUT_XPATH)
    private WebElement cardNumberInput;

    @FindBy(xpath = EXP_YEAR_DROPDOWN_XPATH)
    private WebElement expYearDropdown;

    @FindBy(xpath = EXP_MONTH_DROPDOWN_XPATH)
    private WebElement expMonthDropdown;

    @FindBy(xpath = CVC_INPUT_XPATH)
    private WebElement cvcInput;

    @FindBy(xpath = DONE_BTN_XPATH)
    private WebElement doneBtn;

    @FindBy(xpath = CITY_DROPDOWN_VALUES)
    private List<WebElement> cityDropdownValues;

    @FindBy(xpath = EXP_MONTH_VALUES_XPATH)
    private List<WebElement> expMonthValues;

    @FindBy(xpath = EXP_YEAR_VALUES_XPATH)
    private List<WebElement> expYearValues;

    @FindBy(xpath = STEP_TITLE)
    private WebElement stepTitle;

    @FindBy(xpath = PAYPAL_BTN_XPATH)
    private WebElement paypalBtn;

    @FindBy(id = PAYPAL_EMAIL_INPUT_ID)
    private WebElement paypalEmailInput;

    @FindBy(id = PAYPAL_PSWD_INPUT_ID)
    private WebElement paypalPswdInput;

    @FindBy(id = PAYPAL_LOGIN_BTN_ID)
    private WebElement paypalLoginBtn;

    @FindBy(id = PAYMENT_SUBMIT_BTN)
    private WebElement paymentSubmitBtn;

    @FindBy(xpath = PAYPAL_IFRAME_XPATH)
    private WebElement paypalIFrame;

    //GETTERS

    public WebElement getPaypalIFrame(){
        return paypalIFrame;
    }

    public WebElement getPaymentSubmitBtn(){
        return paymentSubmitBtn;
    }

    public WebElement getPaypalLoginBtn(){
        return paypalLoginBtn;
    }

    public WebElement getPaypalPswdInput(){
        return paypalPswdInput;
    }

    public WebElement getPaypalEmailInput(){
        return paypalEmailInput;
    }

    public WebElement getPaypalBtn(){
        return paypalBtn;
    }

    public WebElement getStepTitle() {
        return stepTitle;
    }

    public List<WebElement> getExpYearValues() {
        return expYearValues;
    }

    public List<WebElement> getExpMonthValues() {
        return expMonthValues;
    }

    public List<WebElement> getCityDropdownValues() {
        return cityDropdownValues;
    }

    public WebElement getDoneBtn() {
        return doneBtn;
    }

    public WebElement getCvcInput() {
        return cvcInput;
    }

    public Select getExpMonthDropdown() {
        return new Select(expMonthDropdown);
    }

    public Select getExpYearDropdown() {
        return new Select(expYearDropdown);
    }

    public WebElement getCardNumberInput() {
        return cardNumberInput;
    }

    public WebElement getNameOnCardInput() {
        return nameOnCardInput;
    }

    public WebElement getCreditCardCheckbox() {
        return creditCardCheckbox;
    }

    public WebElement getPayPalCheckbox() {
        return payPalCheckbox;
    }

    public WebElement getFeatureCheckbox() {
        return featureCheckbox;
    }

    public WebElement getShippingCheckbox() {
        return shippingCheckbox;
    }

    public WebElement getPhoneInput() {
        return phoneInput;
    }

    public WebElement getZipcodeInput() {
        return zipcodeInput;
    }

    public Select getCityDropdown() {
        return new Select(cityDropdown);
    }

    public WebElement getAddressInput() {
        return addressInput;
    }

    public Select getCountryDropdown() {
        return new Select(countryDropdown);
    }

    //METHODS

    public void populateLocationForm(String address, String country, String zipCode, String phone) {
        getAddressInput().sendKeys(address);
        getCountryDropdown().selectByVisibleText(country);
        getCityDropdown().selectByIndex(getRandomNumber(1, getCityDropdownValues().size()));
        getZipcodeInput().sendKeys(zipCode);
        getPhoneInput().sendKeys(phone);
    }

    public void populateCardPaymentForm(String nameOnCard, String cardNumber, String cvc) {
        getCreditCardCheckbox().click();
        getNameOnCardInput().sendKeys(nameOnCard);
        getCardNumberInput().sendKeys(cardNumber);
        getExpYearDropdown().selectByIndex(getRandomNumber(2, getExpYearValues().size()));
        getExpMonthDropdown().selectByIndex(getRandomNumber(2,getExpYearValues().size()));
        getCvcInput().sendKeys(cvc);
    }

    public ItemPage clickDoneBtn() throws InterruptedException {
        getDoneBtn().click();
        System.out.println("Done button is clicked");
        Thread.sleep(2000);
        return new ItemPage(getDriver());
    }

    public void clickFeatureProductPayment() {
        getFeatureCheckbox().click();
    }

    public void clickShippingPayment() {
        getShippingCheckbox().click();
    }

    public boolean verifyStepTitle(String stepTitle) {
        waitForVisibility(getAddressInput());
        return getStepTitle().getText().equals(stepTitle);
    }

}