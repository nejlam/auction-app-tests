package page_objects.auction_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.List;
import java.util.Random;

public class HomePage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/";
    final static private String LOGIN_LINK_XPATH = "//*[@id='root']/div/div[1]/div[2]/a[1]";
    final static private String CREATE_ACCOUNT_LINK_XPATH = "//*[@id='root']/div/div[1]/div[2]/a[2]";
    final static private String HOME_PAGE_LINK = "//*[@id='root']/div/div[2]/div[2]/a[1]";
    final static private String FEATURED_PRODUCT = "//*[@id='root']/div/div[3]/div[1]/div[2]/div/button";
    final static private String CATEGORIES_LIST_XPATH = "//*[@id='root']/div/div[3]/div[1]/div[1]/button";
    final static private String FEATURED_PRODUCTS_LIST_XPATH = "//*[@id='root']/div/div[3]/div[3]/div[2]/div/h3";

    public HomePage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
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

    public ShopPage clickCategory(int index){
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfAllElements(getCategoriesList()));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='root']/div/div[3]/div[1]/div[1]/button"), 7));
        getCategoriesList().get(index).click();
        return new ShopPage(getDriver());
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

    public ItemPage selectRandomFeaturedProduct(){
        // Find and click on a random product
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfAllElements(getFeaturedProductsList()));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='root']/div/div[3]/div[3]/div[2]/div/h3"), 3));

        List<WebElement> allProducts = getFeaturedProductsList();
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
        return new ItemPage(getDriver());
    }
}
