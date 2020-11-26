package page_objects.auction_app;

import org.apache.xpath.operations.Bool;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.List;
import java.util.Random;

public class ShopPage extends PageBase {
    final private static String PAGE_URL_REGEX = "\\/shop\\d*";
    final static private String FIRST_ITEM_XPATH= "//*[@id='root']/div/div[3]/div/div[2]/div[2]/div[1]/div/h3";
    final static private String DISPLAYED_ITEMS_LIST_XPATH= "//*[@id='root']/div/div[3]/div/div[2]/div[2]/div/div/h3";

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public ShopPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    @FindBy(xpath = FIRST_ITEM_XPATH)
    private WebElement firstItem;

    @FindBy(xpath = DISPLAYED_ITEMS_LIST_XPATH)
    private List<WebElement> displayedItemsList;

    public List<WebElement> getDisplayedItemsList() {
        return displayedItemsList;
    }

    public WebElement getFirstItem(){
        return firstItem;
    }

    public ItemPage clickFirstItem(){
        getFirstItem().click();
        return new ItemPage(getDriver());
    }

    public ItemPage selectRandomItem(){
        // Find and click on a random item
        List<WebElement> allProducts = getDisplayedItemsList();
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
        return new ItemPage(getDriver());
    }

    public boolean verifyFirstItem(String query){
        waitForVisibility(getFirstItem());
        return getFirstItem().getText().equals(query);
    }

}
