package page_objects.auction_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShopPage extends PageBase {
    final private static String PAGE_URL_REGEX = "\\/shop\\d*";
    final private static String BREADCRUMB_CATEGORY_ITEM_XPATH = "//*[@id='root']/div/nav/ol/li[2]/div";
    final static private String FIRST_ITEM_XPATH= "//*[@id='root']/div/div[3]/div/div[2]/div[2]/div[1]/div/h3";
    final static private String DISPLAYED_PRODUCTS_LIST_XPATH= "//*[@id='root']/div/div[3]/div/div[2]/div[2]/div/div/h3";
    final static private String SORT_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div/div[2]/div[1]/select";
    final static private String ITEM_PRICE_TXT_XPATH = "//*[@id='root']/div/div[3]/div/div[2]/div[2]/div/div";
    final static private String SORT_VALUES_XPATH = "//*[@id='root']/div/div[3]/div/div[2]/div[1]/select/option";
    private Object List;

    public ShopPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForListElementsNum(String list, int number){
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(list), number));
    }

    private void waitForListElementsMoreThanNum(String list, int number){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(list), number));
    }

    private void waitForPresenceOfElements(String list){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(list)));
    }

    private void waitForVisibilityOfAllElem(List<WebElement> list){
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }


    @FindBy(xpath = BREADCRUMB_CATEGORY_ITEM_XPATH)
    private WebElement breadcrumbCategoryItem;

    @FindBy(xpath = FIRST_ITEM_XPATH)
    private WebElement firstItem;

    @FindBy(xpath = DISPLAYED_PRODUCTS_LIST_XPATH)
    private List<WebElement> displayedProductsList;

    @FindBy(xpath = SORT_DROPDOWN_XPATH)
    private WebElement sortDropdown;

    @FindBy(xpath = SORT_VALUES_XPATH)
    private List<WebElement> sortValues;

    public Select getSortDropdown(){
        return new Select(sortDropdown);
    }

    public List<WebElement> getDisplayedProductsList() {
        return displayedProductsList;
    }

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

    public ItemPage selectRandomProduct(){
        // Find and click on a random product
        List<WebElement> allProducts = getDisplayedProductsList();
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
        return new ItemPage(getDriver());
    }

    public void selectSortOption(String value){
        waitForListElementsMoreThanNum(SORT_VALUES_XPATH, 2);
        getSortDropdown().selectByValue(value);

    }

    public boolean verifySortPrices(String sort){
        boolean staleElement = true;
        List<Float> prices = new ArrayList<Float>();
        List<Float> sortedPrices = (java.util.List<Float>) List;

        while(staleElement){
            try{
                List<WebElement> productPricesList = getDriver().findElements(By.xpath(ITEM_PRICE_TXT_XPATH));
                waitForListElementsNum(ITEM_PRICE_TXT_XPATH, 12);
                waitForVisibilityOfAllElem(productPricesList);
                for (WebElement e : productPricesList)
                {
                    wait.until(ExpectedConditions.visibilityOf(e));
                    prices.add(Float.parseFloat(new ItemPage(getDriver()).getNum(e.getText())));
                }
                sortedPrices = new ArrayList<Float>(prices);
                if (sort.equals("price_asc")){
                    System.out.println("Sort ascending");
                    Collections.sort(sortedPrices);
                }else {
                    System.out.println("Sort descending");
                    Collections.sort(sortedPrices, Collections.reverseOrder());
                }
                System.out.println("Expected sort:" + sortedPrices);
                System.out.println("Actual sort:" + prices);
                staleElement = false;
            } catch(org.openqa.selenium.StaleElementReferenceException ex){
                staleElement = true;
            }
        }
        return sortedPrices.equals(prices);
    }

}
