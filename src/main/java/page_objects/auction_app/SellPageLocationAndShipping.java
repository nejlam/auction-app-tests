package page_objects.auction_app;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SellPageLocationAndShipping extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/my_account\\d*";
    final static private String ADDRESS_INPUT_NAME = "street";
    final static private String COUNTRY_DROPDOWN_NAME = "country";
    final static private String CITY_DROPDOWN_NAME = "city";
    final static private String CITY_DROPDOWN_VALUES = "//*[@name='city']/option";
    final static private String ZIPCODE_INPUT_NAME = "zip";
    final static private String PHONE_INPUT_NAME = "phone";
    final static private String SHIPPING_CHECKBOX_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[5]/div/label";
    final static private String FEATURE_CHECKBOX_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[6]/div/label";
    final static private String PAYPAL_CHECKBOX_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[2]/label";
    final static private String CREDIT_CARD_CHECKBOX_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[3]/label";
    final static private String DONE_BTN_XPATH = "//*[@type='submit']";
    final static private String STEP_TITLE = "//*[@id='root']/div/div[3]/div[2]/div[1]";
    //payment info vars
    final static private String NAME_ON_CARD_INPUT_NAME = "card.name";
    final static private String CARD_NUMBER_INPUT_NAME = "card.cardNumber";
    final static private String EXP_YEAR_DROPDOWN_NAME = "card.expirationYear";
    final static private String EXP_YEAR_VALUES_XPATH = "//*[@name='card.expirationYear']/option";
    final static private String EXP_MONTH_DROPDOWN_NAME = "card.expirationMonth";
    final static private String EXP_MONTH_VALUES_XPATH = "//*[@name='card.expirationMonth']/option";
    final static private String CVC_INPUT_NAME = "card.cvc";

    public SellPageLocationAndShipping(WebDriver driver) {
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    private int getRandomNumber(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        return randomNum;
    }

    WebDriverWait wait = new WebDriverWait(getDriver(),30);

    public void waitForVisibilityOfElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    @FindBy(name = ADDRESS_INPUT_NAME)
    private WebElement addressInput;

    @FindBy(name = COUNTRY_DROPDOWN_NAME)
    private WebElement countryDropdown;

    @FindBy(name = CITY_DROPDOWN_NAME)
    private WebElement cityDropdown;

    @FindBy(name = ZIPCODE_INPUT_NAME)
    private WebElement zipcodeInput;

    @FindBy(name = PHONE_INPUT_NAME)
    private WebElement phoneInput;

    @FindBy(xpath = SHIPPING_CHECKBOX_XPATH)
    private WebElement shippingCheckbox;

    @FindBy(xpath = FEATURE_CHECKBOX_XPATH)
    private WebElement featureCheckbox;

    @FindBy(xpath = PAYPAL_CHECKBOX_XPATH)
    private WebElement payPalCheckbox;

    @FindBy(xpath = CREDIT_CARD_CHECKBOX_XPATH)
    private WebElement creditCardCheckbox;

    @FindBy(name = NAME_ON_CARD_INPUT_NAME)
    private WebElement nameOnCardInput;

    @FindBy(name = CARD_NUMBER_INPUT_NAME)
    private WebElement cardNumberInput;

    @FindBy(name = EXP_YEAR_DROPDOWN_NAME)
    private WebElement expYearDropdown;

    @FindBy(name = EXP_MONTH_DROPDOWN_NAME)
    private WebElement expMonthDropdown;

    @FindBy(name = CVC_INPUT_NAME)
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

    //GETTERS

    public WebElement getStepTitle() {
        return stepTitle;
    }

    public List<WebElement> getExpYearValues() {
        return expYearValues;
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
        waitForVisibilityOfElement(getAddressInput());
        getAddressInput().sendKeys(address);
        getCountryDropdown().selectByVisibleText(country);
        getCityDropdown().selectByIndex(getRandomNumber(1, getCityDropdownValues().size()));
        getZipcodeInput().sendKeys(zipCode);
        getPhoneInput().sendKeys(phone);
    }

    public void populateCardPaymentForm(String nameOnCard, String cardNumber, String cvc) {
        waitForVisibilityOfElement(getNameOnCardInput());
        getNameOnCardInput().sendKeys(nameOnCard);
        getCardNumberInput().sendKeys(cardNumber);
        getExpYearDropdown().selectByIndex(getRandomNumber(2, getExpYearValues().size()));
        getExpMonthDropdown().selectByIndex(getRandomNumber(2,getExpYearValues().size()));
        getCvcInput().sendKeys(cvc);
    }

    public void clickCreditCardCheckbox(){
        waitForVisibilityOfElement(getCreditCardCheckbox());
        getCreditCardCheckbox().click();
    }

    public ItemPage clickDoneBtn() throws InterruptedException {
        waitForVisibilityOfElement(getDoneBtn());
        getDoneBtn().click();
        System.out.println("Done button is clicked");
        Thread.sleep(2000);
        return new ItemPage(getDriver());
    }

    public void clickFeatureProductPayment() {
        waitForVisibilityOfElement(getFeatureCheckbox());
        getFeatureCheckbox().click();
    }

    public void clickShippingPayment() {
        waitForVisibilityOfElement(getShippingCheckbox());
        getShippingCheckbox().click();
    }

    public boolean verifyStepTitle(String stepTitle) {
        waitForVisibilityOfElement(getAddressInput());
        boolean step = getStepTitle().getText().equals(stepTitle);
        System.out.println("Step title is: " + getStepTitle().getText());
        return step;
    }

}