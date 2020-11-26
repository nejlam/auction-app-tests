package page_objects.auction_app;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.List;
import java.util.Random;

public class HomePage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/d*";
    final static private String LOGIN_LINK_XPATH = "//*[@id='root']/div/div[1]/div[2]/a[1]";
    final static private String CREATE_ACCOUNT_LINK_XPATH = "//*[@id='root']/div/div[1]/div[2]/a[2]";
    final static private String HOME_PAGE_LINK = "//*[@id='root']/div/div[2]/div[2]/a[1]";
    final static private String FEATURED_PRODUCT = "//*[@id='root']/div/div[3]/div[1]/div[2]/div/button";
    final static private String CATEGORIES_LIST_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[1]/button";
    final static private String FEATURED_PRODUCTS_LIST_XPATH = "//*[@id='root']/div/div[3]/div[3]/div[2]/div/h3";
    final static private String ACCOUNT_PAGE_LINK_XPATH = "//*[@id='root']/div/div[2]/div[2]/a[3]";
    final static private String SEARCH_INPUT_XPATH = "//*[@id='root']/div/div[2]/div[1]/input";

    public HomePage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    Random rand = new Random();

    private void waitForVisibilityAllElements(List<WebElement> list){
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    private void waitForElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitForListElementsNum(String listXpath, int number){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(listXpath), number));
    }

    @FindBy(xpath = LOGIN_LINK_XPATH)
    private WebElement loginLink;

    @FindBy(xpath = CREATE_ACCOUNT_LINK_XPATH)
    private WebElement createAccountLink;

    @FindBy(xpath = HOME_PAGE_LINK)
    private WebElement homepageLink;

    @FindBy(xpath = FEATURED_PRODUCT)
    private WebElement featuredProduct;

    @FindBy(xpath = CATEGORIES_LIST_XPATH)
    private List<WebElement> categoriesList;

    @FindBy(xpath = FEATURED_PRODUCTS_LIST_XPATH)
    private List<WebElement> featuredProductsList;

    @FindBy(xpath = ACCOUNT_PAGE_LINK_XPATH)
    private WebElement accountPage;

    @FindBy(xpath = SEARCH_INPUT_XPATH)
    private WebElement searchInput;

    public WebElement getSearchInput(){
        return searchInput;
    }

    public WebElement getAccountPage(){
        return accountPage;
    }

    public List<WebElement> getFeaturedProductsList() {
        return featuredProductsList;
    }

    public List<WebElement> getCategoriesList() {
        return categoriesList;
    }

    public WebElement getHomepageLink(){
        return homepageLink;
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

    public AccountPage clickAccountPageLink(){
        waitForElementToBeClickable(getAccountPage());
        getAccountPage().click();
        return new AccountPage(getDriver());
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
        String classValue = getHomepageLink().getAttribute("class");
        return classValue.contains(attributeValue);
    }

    public ItemPage clickOnFirstProduct(){
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
        // Find and click on a random product
        waitForVisibilityAllElements(getFeaturedProductsList());
        waitForListElementsNum(FEATURED_PRODUCTS_LIST_XPATH, 3);
        List<WebElement> allProducts = getFeaturedProductsList();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
        return new ItemPage(getDriver());
    }


    public ShopPage searchItem(String query) throws InterruptedException {
        Thread.sleep(1000);
        getSearchInput().sendKeys(query, Keys.ENTER);
        return new ShopPage(getDriver());
    }

    public boolean verifySearchInput(String query){
        System.out.println(getSearchInput().getAttribute("value"));
        return getSearchInput().getAttribute("value").equals(query);
    }
}
