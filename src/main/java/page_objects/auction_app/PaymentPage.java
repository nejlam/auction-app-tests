package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;


public class PaymentPage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/my_account\\/d*";
    final static private String CREDIT_CARD_CHECKBOX_XPATH = "//*[@id='root']/div/div[3]/div/div[2]/form/div[6]/div[3]/label";
    final static private String DONE_BTN_XPATH = "//*[@type='submit']";
    final static private String SKIP_BTN_XPATH = "/html/body/div[3]/div/div/div[2]/div[2]/button[1]";

    public PaymentPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForVisibilityOfElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(xpath = CREDIT_CARD_CHECKBOX_XPATH)
    private WebElement creditCardCheck;

    @FindBy(xpath = DONE_BTN_XPATH)
    private WebElement doneBtn;

    @FindBy(xpath = SKIP_BTN_XPATH)
    private WebElement skipBtn;

    //GETTERS

    public WebElement getSkipBtn(){
        return skipBtn;
    }

    public WebElement getCreditCardCheck(){
        return creditCardCheck;
    }

    public WebElement getDoneBtn(){
        return doneBtn;
    }

    //METHODS

    public void populateLocationInfo(String address, String country, String zip, String phone){
        new SellPageLocationAndShipping(getDriver()).populateLocationForm(address, country, zip, phone);
    }

    public void clickCreditCardCheckbox(){
        waitForVisibilityOfElement(getCreditCardCheck());
        getCreditCardCheck().click();
    }

    public void populateCreditCardInfo(String nameOnCard, String cardNumber, String cvc){
        new SellPageLocationAndShipping(getDriver()).populateCardPaymentForm(nameOnCard, cardNumber, cvc);
    }

    public void clickDoneBtn(){
        getDoneBtn().click();
    }

    public void clickSkipRatingBtn(){
        waitForVisibilityOfElement(getSkipBtn());
        getSkipBtn().click();
    }

}
