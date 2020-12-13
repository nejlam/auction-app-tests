package page_objects.auction_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;
import java.util.List;

public class SellerPage extends PageBase {

    final static private String PAGE_URL_REGEX = "\\/my_account/seller\\d*";
    final static private String ADD_NEW_ITEM_BTN = "//*[@id='root']/div/div[3]/div/div[2]/button[4]";
    final static private String SELLER_TABS = "//*[@id='root']/div/div[3]/div/div[2]/button";
    final static private String TABLE_ITEMS_TITLE_XPATH = "//*[@id='root']/div/div[3]/div/div[3]/table/tbody/tr/td[2]/div[1]";

    public SellerPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForVisibilityOfElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForVisibilityAllElements(List<WebElement> list){
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    @FindBy(xpath = ADD_NEW_ITEM_BTN)
    private WebElement addNewItemBtn;

    @FindBy(xpath = SELLER_TABS)
    private List<WebElement> sellerTabs;

    @FindBy(xpath = TABLE_ITEMS_TITLE_XPATH)
    private List<WebElement> tableItemsTitles;

    //GETTERS

    public List<WebElement> getTableItemsTitles(){
        return tableItemsTitles;
    }

    public List<WebElement> getSellerTabs (){
        return sellerTabs;
    }

    public WebElement getStartSellingBtn(){
        return addNewItemBtn;
    }

    //METHODS

    public SellPageProductInfo clickStartSellingBtn(){
        waitForVisibilityOfElement(getStartSellingBtn());
        getStartSellingBtn().click();
        return new SellPageProductInfo(getDriver());
    }

    public void openSellerTab(String status){
        waitForVisibilityAllElements(getSellerTabs());
        getSellerTabs().get(getTabIndex(status)).click();
    }

    private int getTabIndex(String status){
        int index;
        if(status.equals("Active")){
            index = 1;
        } else if(status.equals("Scheduled")){
            index = 0;
        } else {
            index = 2;
        }
        return index;
    }

    public boolean verifyActiveTab(String status){
        waitForVisibilityAllElements(getSellerTabs());
        System.out.println("Active tab: " + getSellerTabs().get(getTabIndex(status)).getText());
        return getSellerTabs().get(getTabIndex(status)).getText().equals(status);
    }

    public boolean verifyItemInTable(String itemTitle) throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(TABLE_ITEMS_TITLE_XPATH),0));
        wait.until(ExpectedConditions.visibilityOfAllElements(getTableItemsTitles()));
        System.out.println("Items list size is: " + getTableItemsTitles().size());
        boolean found = false;
        for(WebElement e: getTableItemsTitles()){
            wait.until(ExpectedConditions.visibilityOf(e));
            System.out.println("Item title is: " + e.getText());
            if(e.getText().equals(itemTitle))
                found = true;
                System.out.println("Added item: " + itemTitle);
                System.out.println("Item in table: " + e.getText());
            break;
        }
        return found;
    }

}
