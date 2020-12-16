package page_objects.auction_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.List;
import java.util.Random;

public class BidsPage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/my_account/bids\\d*";
    final static private String PAY_BTN_LIST_XPATH = "//*[@class='btn btn-fill-purple-shadow btn-lg-2']";
    final static private String PAYMENT_SUCCESS_ALERT_XPATH = "//*[@id='root']/div/div[3]";
    final static private String CLOSE_ALERT_BTN_XPATH = "//*[@id='root']/div/div[3]/button";

    public BidsPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    Random rand = new Random();

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForListElementsNum(String listXpath, int number){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(listXpath), number));
    }

    private void waitForVisibilityOfElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(xpath = PAY_BTN_LIST_XPATH)
    private List<WebElement> payButtons;

    @FindBy(xpath = PAYMENT_SUCCESS_ALERT_XPATH)
    private WebElement paymentSuccessAlert;

    @FindBy(xpath = CLOSE_ALERT_BTN_XPATH)
    private WebElement closeAlertBtn;

    //GETTERS

    public WebElement getCloseAlertBtn(){
        return closeAlertBtn;
    }

    public WebElement getPaymentSuccessAlert(){
        return paymentSuccessAlert;
    }

    public List<WebElement> getPayButtons(){
        return payButtons;
    }

    //METHODS

    public PaymentPage clickPayBtn(){
        waitForListElementsNum(PAY_BTN_LIST_XPATH, 0);
        List<WebElement> allPayButtons = getPayButtons();
        int randomPayBtn = rand.nextInt(allPayButtons.size());
        allPayButtons.get(randomPayBtn).click();
        return new PaymentPage(getDriver());
    }

    public boolean verifyPaymentSuccessAlert(String alertTxt){
        waitForVisibilityOfElement(getCloseAlertBtn());
        String alertMsg = getPaymentSuccessAlert().getText();
        boolean alert = alertMsg.contains(alertTxt);
        if(alert){
            System.out.println("Alert is present: " + alertMsg);
        } else {
            System.out.println("Alert is not present");
        }
        return alert;
    }

    public void closeAlertMsg(){
        getCloseAlertBtn().click();
    }

}
