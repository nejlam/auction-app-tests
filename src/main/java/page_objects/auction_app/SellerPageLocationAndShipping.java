package page_objects.auction_app;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_objects.PageBase;

import java.nio.file.WatchEvent;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SellerPageLocationAndShipping extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/my_account/seller\\d*";
    final static private String ADDRESS_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[1]/input";
    final static private String COUNTRY_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[1]/select";
    final static private String CITY_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[2]/select";
    final static private String CITY_DROPDOWN_VALUES = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[2]/select/option[2]";
    final static private String ZIPCODE_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[3]/input";
    final static private String PHONE_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[4]/div/input";
    final static private String SHIPPING_CHECKBOX_XPATH = "//*[@id='custom-shipping-checkbox']";
    final static private String FEATURE_CHECKBOX_XPATH = "//*[@id='custom-featured-checkbox']";
    final static private String PAYPAL_CHECKBOX_XPATH = "//*[@id='custom-paypal-checkbox']";
    final static private String CREDIT_CARD_CHECKBOX_XPATH = "//*[@id='custom-credit-card-checkbox']";
    //credit card info
    final static private String NAME_ON_CARD_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[4]/div[1]/input";
    final static private String CARD_NUMBER_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[4]/div[2]/input";
    final static private String EXP_YEAR_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[1]/select";
    final static private String EXP_YEAR_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[1]/select/option[2]";
    final static private String EXP_MONTH_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[2]/select";
    final static private String EXP_MONTH_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[2]/select/option[4]";
    final static private String CVC_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[2]/input";


    final static private String DONE_BTN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/button[2]";


    public SellerPageLocationAndShipping(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    private int getRandomNumber(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        return randomNum;
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

    //GETTERS

    public List<WebElement> getExpYearValues(){
        return expYearValues;
    }

    public List<WebElement> getExpMonthValues(){
        return expMonthValues;
    }

    public List<WebElement> getCityDropdownValues(){
        return cityDropdownValues;
    }

    public WebElement getDoneBtn(){
        return doneBtn;
    }

    public WebElement getCvcInput(){
        return cvcInput;
    }

    public Select getExpMonthDropdown(){
        return new Select(expMonthDropdown);
    }

    public Select getExpYearDropdown(){
        return new Select(expYearDropdown);
    }

    public WebElement getCardNumberInput(){
        return cardNumberInput;
    }

    public WebElement getNameOnCardInput(){
        return nameOnCardInput;
    }

    public WebElement getCreditCardCheckbox(){
        return creditCardCheckbox;
    }

    public WebElement getPayPalCheckbox(){
        return payPalCheckbox;
    }

    public WebElement getFeatureCheckbox(){
        return featureCheckbox;
    }

    public WebElement getShippingCheckbox(){
        return shippingCheckbox;
    }

    public WebElement getPhoneInput(){
        return phoneInput;
    }

    public WebElement getZipcodeInput(){
        return zipcodeInput;
    }

    public Select getCityDropdown(){
        return new Select(cityDropdown);
    }

    public WebElement getAddressInput(){
        return addressInput;
    }

    public Select getCountryDropdown(){
        return new Select(countryDropdown);
    }

    //METHODS

    public ItemPage populateRequiredForm(String address, String country, String city, String zipCode, String phone){
        getAddressInput().sendKeys(address);
        getCountryDropdown().selectByVisibleText(country);
        //issues with dropdown values not loading
        //getCityDropdown().selectByIndex(getRandomNumber(1, getCityDropdownValues().size()));
        getCityDropdown().selectByVisibleText(city);
        getZipcodeInput().sendKeys(zipCode);
        getPhoneInput().sendKeys(phone);
        getDoneBtn().click();
        return new ItemPage(getDriver());
    }

    public void populatePaymentForm(String nameOnCard, String cardNumber, String cvc){
     getCreditCardCheckbox().click();
     getNameOnCardInput().sendKeys(nameOnCard);
     getCardNumberInput().sendKeys(cardNumber);
     getExpYearDropdown().selectByIndex(getRandomNumber(2, getExpYearValues().size()));
     getExpMonthDropdown().selectByIndex(getRandomNumber(2,getExpYearValues().size()));
     getCvcInput().sendKeys(cvc);
    }

}
