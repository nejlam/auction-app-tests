package page_objects.auction_app;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class HomePage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/d*";
    final static private String LOGIN_LINK_XPATH = "//*[@id='root']/div/div[1]/div[2]/a[1]";
    final static private String CREATE_ACCOUNT_LINK_XPATH = "//*[@id='root']/div/div[1]/div[2]/a[2]";
    final static private String FEATURED_PRODUCT = "//*[@id='root']/div/div[3]/div[1]/div[2]/div/button";
    final static private String CATEGORIES_LIST_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[1]/button";
    final static private String FEATURED_PRODUCTS_LIST_XPATH = "//*[@id='root']/div/div[3]/div[3]/div[2]/div/h3";
    final static private String SEARCH_INPUT_XPATH = "//*[@id='root']/div/div[2]/div[1]/input";
    final static private String NAVBAR_TABS_XPATH = "//*[@id='root']/div/div[2]/div[2]/a";

    public HomePage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    Random rand = new Random();

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForVisibilityAllElements(List<WebElement> list){
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    private void waitForElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitForListElementsNum(String listXpath, int number){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(listXpath), number));
    }

    private void waitForVisibilityOfElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(xpath = LOGIN_LINK_XPATH)
    private WebElement loginLink;

    @FindBy(xpath = CREATE_ACCOUNT_LINK_XPATH)
    private WebElement createAccountLink;

    @FindBy(xpath = FEATURED_PRODUCT)
    private WebElement featuredProduct;

    @FindBy(xpath = CATEGORIES_LIST_XPATH)
    private List<WebElement> categoriesList;

    @FindBy(xpath = FEATURED_PRODUCTS_LIST_XPATH)
    private List<WebElement> featuredProductsList;

    @FindBy(xpath = SEARCH_INPUT_XPATH)
    private WebElement searchInput;

    @FindBy(xpath = NAVBAR_TABS_XPATH)
    private List<WebElement> navbarTabs;

    //GETTERS

    public List<WebElement> getNavbarTabs(){
        return navbarTabs;
    }

    public WebElement getSearchInput(){
        return searchInput;
    }

    public List<WebElement> getFeaturedProductsList() {
        return featuredProductsList;
    }

    public List<WebElement> getCategoriesList() {
        return categoriesList;
    }

    public WebElement getLoginLink(){
        return loginLink;
    }

    public WebElement getCreateAccountLink(){
        return createAccountLink;
    }

    public WebElement getFeaturedProduct(){
        return featuredProduct;
    }

    //METHODS

    public void clickNavbarTab(int index){
        waitForVisibilityAllElements(getNavbarTabs());
        waitForVisibilityOfElement(getNavbarTabs().get(index));
        getNavbarTabs().get(index).click();
    }

    public RegistrationPage clickCreateAccountLink(){
        getCreateAccountLink().click();
        return new RegistrationPage(getDriver());
    }

    public LoginPage clickLoginLink(){
        getLoginLink().click();
        return new LoginPage(getDriver());
    }

    public Boolean verifyHomepageLink(String attributeValue){
        waitForVisibilityOfElement(getNavbarTabs().get(0));
        String classValue = getNavbarTabs().get(0).getAttribute("class");
        boolean homeAttribute = classValue.contains(attributeValue);
        if(homeAttribute){
            System.out.println("Home page is opened.");
        } else System.out.println("Home page is not opened.");
        return homeAttribute;
    }

    public ItemPage clickOnFirstProduct(){
        waitForVisibilityOfElement(getFeaturedProduct());
        waitForElementToBeClickable(getFeaturedProduct());
        getFeaturedProduct().click();
        return new ItemPage(getDriver());
    }

    public ShopPage clickRandomCategory(){
        waitForVisibilityAllElements(getFeaturedProductsList());
        waitForListElementsNum(CATEGORIES_LIST_XPATH, 5);
        List<WebElement> allCategories = getCategoriesList();
        int randomProduct = rand.nextInt(allCategories.size()-1);
        allCategories.get(randomProduct).click();
        return new ShopPage(getDriver());
    }

    public ItemPage selectRandomFeaturedProduct(){
        waitForVisibilityAllElements(getFeaturedProductsList());
        waitForListElementsNum(FEATURED_PRODUCTS_LIST_XPATH, 3);
        List<WebElement> allProducts = getFeaturedProductsList();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
        return new ItemPage(getDriver());
    }

    public ShopPage searchItem(String query) throws InterruptedException {
        waitForVisibilityOfElement(getSearchInput());
        Thread.sleep(1000);
        getSearchInput().clear();
        getSearchInput().sendKeys(query, Keys.ENTER);
        return new ShopPage(getDriver());
    }

    public boolean verifySearchInput(String query){
        System.out.println("Search input: " + query);
        System.out.println("Search input entered: " + getSearchInput().getAttribute("value"));
        return getSearchInput().getAttribute("value").equals(query);
    }

    public boolean checkBrokenLinks(){
        boolean brokenLinks = false;
        List<WebElement> links = getDriver().findElements(By.tagName("a"));
        Iterator<WebElement> it = links.iterator();
        String homePage = getDriver().getCurrentUrl();
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;

        while(it.hasNext()){
            url = it.next().getAttribute("href");
            System.out.println(url);

            if(url == null || url.isEmpty()){
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if(!url.startsWith(homePage)){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }

            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                if(respCode >= 400){
                    brokenLinks = true;
                    System.out.println(url+" is a broken link");
                }
                else{
                    System.out.println(url+" is a valid link");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return brokenLinks;
    }

}
