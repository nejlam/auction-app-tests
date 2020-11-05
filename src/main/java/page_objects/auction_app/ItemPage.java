package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

public class ItemPage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/shop\\d*";
    final static private String FEATURED_IMG_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[1]/img[1]";
    final static private String ADD_BID_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[2]/div/input";
    final static private String PLACE_BID_BTN = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[2]/button";
    final static private String START_PRICE_MSG_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[2]/div/div";
    final static private String PRODUCT_TITLE = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[1]/h1";
    final static private String ALERT_MSG_CLASS = "alert";

    public ItemPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    @FindBy(xpath = FEATURED_IMG_XPATH)
    private WebElement featuredImg;

    @FindBy(xpath = ADD_BID_INPUT_XPATH)
    private WebElement addBidInput;

    @FindBy(xpath = PLACE_BID_BTN)
    private WebElement placeBidBtn;

    @FindBy(xpath = START_PRICE_MSG_XPATH)
    private WebElement startPriceMsg;

    @FindBy(xpath = PRODUCT_TITLE)
    private WebElement productTitle;

    @FindBy(className = ALERT_MSG_CLASS)
    private WebElement alertMsg;

    public WebElement getAlertMsg(){
        return alertMsg;
    }

    public WebElement getProductTitle(){
        return productTitle;
    }

    public WebElement getStartPriceMsg(){
        return startPriceMsg;
    }

    public String extractStartPriceValue(){
        return getStartPriceMsg().getText();
    }

    public WebElement getPlaceBidBtn(){
        return placeBidBtn;
    }

    public WebElement getAddBidInput(){
        return  addBidInput;
    }

    public WebElement getFeaturedImg(){
        return featuredImg;
    }

    public Boolean verifyFeaturedImage(){
        return getFeaturedImg().isDisplayed();
    }

    public Boolean verifyAlertMsg(){
        return getAlertMsg().isDisplayed();
    }

    public void placeBid() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(),30);
        wait.until(ExpectedConditions.elementToBeClickable(getAddBidInput()));
        getAddBidInput().sendKeys(getItemStartValue());
        wait.until(ExpectedConditions.elementToBeClickable(getPlaceBidBtn()));
        if(getPlaceBidBtn().isEnabled()) {
            getPlaceBidBtn().click();
        }
    }

    public String getItemStartValue(){
        String startPriceValue = extractStartPriceValue().replaceAll("[^0-9?!\\.]","");
        return startPriceValue;
    }
}
