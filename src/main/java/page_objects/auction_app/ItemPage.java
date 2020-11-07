package page_objects.auction_app;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.List;

public class ItemPage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/shop\\d*";
    final static private String FEATURED_IMG_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[1]/img[1]";
    final static private String ADD_BID_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[2]/div/input";
    final static private String PLACE_BID_BTN = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[2]/button";
    final static private String ENTER_PRICE_MSG_XPATH = "/html/body/div/div/div[3]/div[1]/div[2]/div[2]/div/div";
    final static private String PRODUCT_TITLE = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[1]/h1";
    final static private String ALERT_MSG_CLASS = "alert";
    final static private String START_PRICE_MSG_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[1]/div";
    final static private String WISHLIST_BTN_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[4]/button";
    final static private String RELATED_ITEMS_LIST_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/div";
    final static private String RELATED_ITEMS_SECTION_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]";
    final static private String DETAILS_SECTION = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[4]/div";
    final static private String BIDS_INFO_SECTION_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[3]";
    final static private String LOGIN_BTN_XPATH = "//*[@id='root']/div/div[1]/div[2]/a[1]";
    final static private String BIDS_TABLE_XPATH = "//*[@id='root']/div/div[3]/div[2]/table";
    final static private String HIGHEST_BID_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[3]/span";

    WebDriverWait wait = new WebDriverWait(getDriver(),30);

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

    @FindBy(xpath = ENTER_PRICE_MSG_XPATH)
    private WebElement enterPriceMsg;

    @FindBy(xpath = PRODUCT_TITLE)
    private WebElement productTitle;

    @FindBy(className = ALERT_MSG_CLASS)
    private WebElement alertMsg;

    @FindBy(xpath = START_PRICE_MSG_XPATH)
    private WebElement startPriceMsg;

    @FindBy(xpath = WISHLIST_BTN_XPATH)
    private WebElement wishlistBtn;

    @FindBy(xpath = RELATED_ITEMS_LIST_XPATH)
    private List<WebElement> relatedItemsList;

    @FindBy(xpath = RELATED_ITEMS_SECTION_XPATH)
    private WebElement relatedItemsSection;

    @FindBy(xpath = DETAILS_SECTION)
    private WebElement detailsSection;

    @FindBy(xpath = BIDS_INFO_SECTION_XPATH)
    private WebElement bidsInfo;

    @FindBy(xpath = LOGIN_BTN_XPATH)
    private WebElement loginBtn;

    @FindBy(xpath = BIDS_TABLE_XPATH)
    private WebElement bidsTable;

    @FindBy(xpath = HIGHEST_BID_XPATH)
    private WebElement highestBid;

    public WebElement getHighestBid(){
        return highestBid;
    }

    public WebElement getBidsTable(){
        return bidsTable;
    }

    public WebElement getLoginBtn(){
        return loginBtn;
    }

    public WebElement getDetailsSection(){
        return detailsSection;
    }

    public List<WebElement> getRelatedItemsList(){
        return relatedItemsList;
    }

    public WebElement getWishlistBtn(){
        return wishlistBtn;
    }

    public WebElement getRelatedItemsSection(){
        return relatedItemsSection;
    }

    public WebElement getAlertMsg(){
        return alertMsg;
    }

    public WebElement getProductTitle(){
        return productTitle;
    }

    public WebElement getEnterPriceMsg(){
        return enterPriceMsg;
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

    public WebElement getStartPriceMsg(){
        return startPriceMsg;
    }

    public WebElement getBidsInfo(){
        return bidsInfo;
    }


    //EXTRACT BID VALUES

    public String extractEnterPriceMsg(){
        return getEnterPriceMsg().getText();
    }

    public String extractHighestBid(){
        return getHighestBid().getText();
    }

    //VERIFICATIONS

    public Boolean verifyBidsInfoSection(){
        return getBidsInfo().isDisplayed();
    }

    public Boolean verifyDetailsSection(){
        return getDetailsSection().isDisplayed();
    }

    public Boolean verifyStartPriceMsg(){
        return getStartPriceMsg().isDisplayed();
    }

    public Boolean verifyBidButton(){
        return getAddBidInput().isDisplayed();
    }

    public Boolean verifyBidInputField(){
        return getAddBidInput().isDisplayed();
    }

    public Boolean verifyWishlistBtn(){
        return getWishlistBtn().isDisplayed();
    }

    public Boolean verifyFeaturedImage(){
        return getFeaturedImg().isDisplayed();
    }

    public int verifyNumberOfRelatedItems(){
        return getRelatedItemsList().size();
    }

    public Boolean verifyAlertMsg(){
        return getAlertMsg().isDisplayed();
    }

    public Boolean verifyRelatedItemsSection(){
        return getRelatedItemsSection().isDisplayed();
    }

    public Boolean verifyBidsTable(){
        return getBidsTable().isDisplayed();
    }

    public LoginPage clickLoginButton(){
        getLoginBtn().click();
        return new LoginPage(getDriver());
    }

    public void placeBid(String bidValue) throws InterruptedException {
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(getAddBidInput()));
        getAddBidInput().sendKeys(getBidValue(bidValue));
        //logs the product's name
        System.out.println(getProductTitle().getText());
        clickBidButton();
    }

    private void clickBidButton(){
        wait.until(ExpectedConditions.elementToBeClickable(getPlaceBidBtn()));
        if(getPlaceBidBtn().isEnabled()) {
            getPlaceBidBtn().click();
        }
    }

    private String getBidValue(String bidMsg) {
        wait.until(ExpectedConditions.visibilityOf(getHighestBid()));
        wait.until(ExpectedConditions.visibilityOf(getEnterPriceMsg()));
        String bidValue = bidMsg.replaceAll("[^0-9?!\\.]","");
        System.out.println(bidValue);
        System.out.println(bidMsg);
        return bidValue;
    }
}

