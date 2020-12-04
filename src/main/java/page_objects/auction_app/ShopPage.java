package page_objects.auction_app;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
    final static private String FIRST_ITEM_XPATH= "//*[@id='root']/div/div[3]/div/div[2]/div[2]/div[1]/div/h3";
    final static private String SORT_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div/div[2]/div[1]/select";
    final static private String ITEM_PRICE_TXT_XPATH = "//*[@id='root']/div/div[3]/div/div[2]/div[2]/div/div";
    final static private String SORT_VALUES_XPATH = "//*[@id='root']/div/div[3]/div/div[2]/div[1]/select/option";
    final static private String ITEM_TITLE_LIST_XPATH = "//*[@id='root']/div/div[3]/div/div[2]/div[2]/div/div/h3";
    private Object List;

    public ShopPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForListElementsNum(String list, int number){
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(list), number));
    }

    private void waitForListElementsMoreThanNum(String list, int number){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(list), number));
    }

    private void waitForVisibilityOfAllElem(List<WebElement> list){
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    @FindBy(xpath = FIRST_ITEM_XPATH)
    private WebElement firstItem;

    @FindBy(xpath = SORT_DROPDOWN_XPATH)
    private WebElement sortDropdown;

    @FindBy(xpath = SORT_VALUES_XPATH)
    private List<WebElement> sortValues;

    @FindBy(xpath = ITEM_TITLE_LIST_XPATH)
    private List<WebElement> itemTitleList;

    public List<WebElement> getItemTitleList(){
        return itemTitleList;
    }

    public Select getSortDropdown(){
        return new Select(sortDropdown);
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
        List<WebElement> allProducts = getItemTitleList();
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
                Thread.sleep(1000);
                List<WebElement> productPricesList = getDriver().findElements(By.xpath(ITEM_PRICE_TXT_XPATH));
                waitForListElementsNum(ITEM_PRICE_TXT_XPATH, productPricesList.size());
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
            } catch(StaleElementReferenceException | InterruptedException ex){
                staleElement = true;
            }
        }

        return sortedPrices.equals(prices);
    }

    public boolean verifySortDefault() {
        List<String> titles = new ArrayList<String>();
        List<String> sortedTitles = (java.util.List<String>) List;
        for (WebElement e : getItemTitleList()) {
            wait.until(ExpectedConditions.visibilityOf(e));
            String cap = e.getText().substring(0, 1).toUpperCase() + e.getText().substring(1);
            titles.add(cap);
        }
        sortedTitles = new ArrayList<String>(titles);
        Collections.sort(sortedTitles);
        System.out.println("Expected sort:" + sortedTitles);
        System.out.println("Actual sort:" + titles);
        return sortedTitles.equals(titles);
    }

    public boolean verifyFirstItem(String query){
        waitForVisibility(getFirstItem());
        return getFirstItem().getText().equals(query);
    }

    public boolean verifySearchResults(String query, String search){
        System.out.println("Query: " + query.toLowerCase());
        boolean found = false;

        for(WebElement e : getItemTitleList()){
            String q = query.toLowerCase();
            String r = e.getText().toLowerCase();
            wait.until(ExpectedConditions.visibilityOf(e));

            if(search.equals("unique")){
                if(r.equals(q)){
                    System.out.println("Match: " + r);
                    found = true;
                    break;
                }
            } else if(search.equals("partial")){
                if(r.contains(q)) {
                    System.out.println("Match: " + r);
                    found = true;
                } else{
                    System.out.println("Doesn't match: " + r);
                    found = false;
                    break;
                }
            }

        }
        return found;
    }
}
