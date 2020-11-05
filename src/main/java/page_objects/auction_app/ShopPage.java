package page_objects.auction_app;

import org.apache.xpath.operations.Bool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_objects.PageBase;

public class ShopPage extends PageBase {
    final private static String PAGE_URL_REGEX = "\\/shop\\d*";
    final private static String BREADCRUMB_CATEGORY_ITEM_XPATH = "//*[@id='root']/div/nav/ol/li[2]/div";
    final static private String FIRST_ITEM_XPATH= "//*[@id='root']/div/div[3]/div/div[2]/div[2]/div[1]/div/h3";

    public ShopPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    @FindBy(xpath = BREADCRUMB_CATEGORY_ITEM_XPATH)
    private WebElement breadcrumbCategoryItem;

    @FindBy(xpath = FIRST_ITEM_XPATH)
    private WebElement firstItem;

    public WebElement getFirstItem(){
        return firstItem;
    }

    public WebElement getBreadcrumbCategoryItem() {
        return breadcrumbCategoryItem;
    }

    public Boolean verifyCategoryPage(String Category){
        return getBreadcrumbCategoryItem().getText().equals(Category);
    }

    public ItemPage clickFirstItem(){
        getFirstItem().click();
        return new ItemPage(getDriver());
    }
}
