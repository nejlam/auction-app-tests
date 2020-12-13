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
    final static private String DONE_BTN_XPATH = "//*[@id='btn-sell-done']";
    final static private String STEP_TITLE = "//*[@id='root']/div/div[3]/div[2]/div[1]";
    //payment info vars
    final static private String NAME_ON_CARD_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[4]/div[1]/input";
    final static private String CARD_NUMBER_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[4]/div[2]/input";
    final static private String EXP_YEAR_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[1]/select";
    final static private String EXP_YEAR_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[1]/select/option";
    final static private String EXP_MONTH_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[2]/select";
    final static private String EXP_MONTH_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[1]/div[2]/select/option";
    final static private String CVC_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[7]/div[5]/div[2]/input";

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
        getAddressInput().sendKeys(address);
        getCountryDropdown().selectByVisibleText(country);
        getCityDropdown().selectByIndex(getRandomNumber(1, getCityDropdownValues().size()));
        getZipcodeInput().sendKeys(zipCode);
        getPhoneInput().sendKeys(phone);
    }

    public void populateCardPaymentForm(String nameOnCard, String cardNumber, String cvc) {
        waitForVisibilityOfElement(getCreditCardCheckbox());
        getCreditCardCheckbox().click();
        getNameOnCardInput().sendKeys(nameOnCard);
        getCardNumberInput().sendKeys(cardNumber);
        getExpYearDropdown().selectByIndex(getRandomNumber(2, getExpYearValues().size()));
        getExpMonthDropdown().selectByIndex(getRandomNumber(2,getExpYearValues().size()));
        getCvcInput().sendKeys(cvc);
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