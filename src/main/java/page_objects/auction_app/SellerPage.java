package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_objects.PageBase;

public class SellerPage extends PageBase {

    final static private String PAGE_URL_REGEX = "\\/my_account/seller\\d*";
    final static private String START_SELLING_BTN = "//*[@id='root']/div/div[3]/div/div[2]/div[2]/button";
    final static private String ACTIVE_TAB = "//*[@id='root']/div/nav/ol/li[2]/div";

    public SellerPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    @FindBy(xpath = START_SELLING_BTN)
    private WebElement startSellingBtn;

    @FindBy(xpath = ACTIVE_TAB)
    private WebElement activeTab;

    public WebElement getActiveTab(){
        return activeTab;
    }

    public WebElement getStartSellingBtn(){
        return startSellingBtn;
    }

    public SellPageProductInfo clickStartSellingBtn(){
        getStartSellingBtn().click();
        return new SellPageProductInfo(getDriver());
    }

    public boolean verifyActiveTab(String tab){
        return getActiveTab().getText().equals(tab);
    }

}
