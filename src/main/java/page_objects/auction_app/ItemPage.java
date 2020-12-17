package page_objects.auction_app;

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
    final static private String PLACE_BID_BTN = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[2]/div[2]/button";
    final static private String ENTER_PRICE_MSG_XPATH = "/html/body/div/div/div[3]/div[1]/div[2]/div[2]/div/div";
    final static private String PRODUCT_TITLE = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[1]/h1";
    final static private String ALERT_MSG_CLASS = "alert";
    final static private String START_PRICE_MSG_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[1]/div";
    final static private String WISHLIST_BTN_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[4]/button";
    final static private String RELATED_ITEMS_LIST_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/div";
    final static private String RELATED_ITEMS_SECTION_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]";
    final static private String DETAILS_SECTION = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[4]/div";
    final static private String BIDS_INFO_SECTION_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[3]";
    final static private String BIDS_TABLE_XPATH = "//*[@id='root']/div/div[3]/div[2]/table";
    final static private String HIGHEST_BID_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[2]/div[3]/span";
    final static private String HIGHEST_BID_TABLE_XPATH = "//*[@id='root']/div/div[3]/div[2]/table/tbody/tr[1]/td[3]";
    final static private String ALERT_CLOSE_BTN = "//*[@id='root']/div/div[3]/button";

    WebDriverWait wait = new WebDriverWait(getDriver(),30);

    private void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

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

    @FindBy(xpath = BIDS_TABLE_XPATH)
    private WebElement bidsTable;

    @FindBy(xpath = HIGHEST_BID_XPATH)
    private WebElement highestBid;

    @FindBy(xpath = HIGHEST_BID_TABLE_XPATH)
    private WebElement highestBidTable;

    @FindBy(xpath = ALERT_CLOSE_BTN)
    private WebElement alertCloseBtn;

    //GETTERS

    public WebElement getAlertCloseBtn(){
        return alertCloseBtn;
    }

    public WebElement getHighestBidFromTable(){
        return highestBidTable;
    }

    public WebElement getHighestBid(){
        return highestBid;
    }

    public WebElement getBidsTable(){
        return bidsTable;
    }

    public WebElement getDetailsSection(){
        return detailsSection;
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

    //METHODS

    private String getBidText(WebElement bidMsg){
        return bidMsg.getText();
    }

    public String extractEnterPriceMsg(){
        return getEnterPriceMsg().getText();
    }

    public Boolean verifySuccessfulAdd(String successMsg){
        waitForVisibility(getAlertMsg());
        System.out.println("Success message: " + getAlertMsg().getText());
        return getAlertMsg().getText().contains(successMsg);
    }

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

    public Boolean verifyAlertMsg(){
        waitForVisibility(getAlertCloseBtn());
        waitForVisibility(getAlertMsg());
        boolean alert = getAlertMsg().isDisplayed();
        if(alert)
        System.out.println("Alert message is: " + getAlertMsg().getText());
        return alert;
    }

    public Boolean verifyRelatedItemsSection(){
        waitForVisibility(getRelatedItemsSection());
        return getRelatedItemsSection().isDisplayed();
    }

    public Boolean verifyBidsTable(){
        waitForVisibility(getBidsTable());
        return getBidsTable().isDisplayed();
    }

    public Boolean verifyHighestBidInTable() {
        waitForVisibility(getHighestBidFromTable());
        String tableHighestBid = getNum(getBidText(getHighestBidFromTable()));
        String highestBid = getNum(getBidText(getHighestBid()));
        System.out.println("Highest bid in the table: " + tableHighestBid);
        System.out.println("Placed bid: " + highestBid);
        return highestBid.equals(tableHighestBid);
    }

    public Boolean verifyAlertMsgTxt(String msg){
        waitForVisibility(getAlertMsg());
        boolean alert = getAlertMsg().getText().contains(msg);
        if(alert){
            System.out.println("Alert message is present: " + getAlertMsg().getText());
        } else System.out.println("Alert is not present");
        return alert;
    }

    public void closeAlertBtn(){
        waitForVisibility(getAlertCloseBtn());
        waitElementToBeClickable(getAlertCloseBtn());
        getAlertCloseBtn().click();
    }

    public LoginPage clickLoginButton(){
        waitForVisibility(new HomePage(getDriver()).getLoginLink());
        new HomePage(getDriver()).clickLoginLink();
        return new LoginPage(getDriver());
    }

    public void placeBid(String bidValueMsg){
        waitForVisibility(getHighestBid());
        waitForVisibility(getEnterPriceMsg());
        getAddBidInput().sendKeys(getNum(bidValueMsg));
        clickBidButton();
    }

    private void clickBidButton(){
        waitForVisibility(getPlaceBidBtn());
        if(getPlaceBidBtn().isEnabled()) {
            getPlaceBidBtn().click();
        }
    }

    public void clickWishlistBtn(){
        waitElementToBeClickable(getWishlistBtn());
        getWishlistBtn().click();
    }

    public String getNewHighestBidValue() throws InterruptedException {
        if(getHighestBid().getText().equals("$0")) Thread.sleep(2000);
        double highestValue = Double.parseDouble(getNum(getBidText(getHighestBid())));
        String newHighestValue = "";
        if (highestValue == 999999.99){
            newHighestValue = String.valueOf(highestValue);
            System.out.println("The highest bid has been entered.");
        }else{
            double newHighestValueDouble = highestValue + 0.1;
            newHighestValue = String.valueOf(newHighestValueDouble);
        }
        return newHighestValue;
    }

    public String getNum(String msg) {
        String msgNum = msg.replaceAll("[^0-9?!\\.]","");
        return msgNum;
    }


    public ItemPage checkEnterMsgAndPlaceBid() throws InterruptedException {
        if(getEnterPriceMsg().getText().equals("Enter $0 or more")) {
            Thread.sleep(1000);
        } placeBid(getEnterPriceMsg().getText());
        return this;
    }

}

