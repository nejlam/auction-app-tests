package page_objects.auction_app;


import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_objects.PageBase;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SellerPageProductInfo extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/my_account/seller\\d*";
    final static private String START_SELLING_BTN = "//*[@id='root']/div/div[3]/div/div[2]/div[2]/button";
    final static private String ITEM_TITLE_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[1]/input";
    final static private String CATEGORY_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[1]/select";
    final static private String SUBCATEGORY_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[2]/select";
    final static private String DESCRIPTION_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[3]/textarea";
    final static private String NEXT_BTN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[6]/button[2]";
    final static private String CATEGORY_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[1]/select/option";
    final static private String SUBCATEGORY_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[2]/select/option[2]";


    public SellerPageProductInfo(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    private int getRandomNumber(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        return randomNum;
    }

    @FindBy(xpath = ITEM_TITLE_INPUT_XPATH)
    private WebElement itemTitleInput;

    @FindBy(xpath = DESCRIPTION_INPUT_XPATH)
    private WebElement descriptionInput;

    @FindBy(xpath = START_SELLING_BTN)
    private WebElement startSellingBtn;

    @FindBy(xpath = CATEGORY_DROPDOWN_XPATH)
    private WebElement categoryDropdown;

    @FindBy(xpath = SUBCATEGORY_DROPDOWN_XPATH)
    private WebElement subcategoryDropdown;

    @FindBy(xpath = NEXT_BTN_XPATH)
    private WebElement nextBtn;

    @FindBy(xpath = CATEGORY_VALUES_XPATH)
    private List<WebElement> categoryValues;

    @FindBy(xpath = SUBCATEGORY_VALUES_XPATH)
    private List<WebElement> subcategoryValues;

    public List<WebElement> getSubcategoryValue(){
        return subcategoryValues;
    }

    public List<WebElement> getCategoryValue(){
        return categoryValues;
    }

    public WebElement getNextBtn(){
        return nextBtn;
    }

    public WebElement getItemTitleInput(){
        return itemTitleInput;
    }

    public WebElement getDescriptionInput(){
        return descriptionInput;
    }

    public WebElement getStartSellingBtn(){
        return startSellingBtn;
    }

    public Select getCategoryDropdown(){
        return new Select(categoryDropdown);
    }

    public Select getSubcategoryDropdown(){
        return new Select(subcategoryDropdown);
    }

    public SellerPageProductInfo clickStartSellingBtn(){
        getStartSellingBtn().click();
        return new SellerPageProductInfo(getDriver());
    }

    public SellerPageSetPrices populateForm(String title, String description) throws InterruptedException {
        getItemTitleInput().sendKeys(title);
        //issues with subcategory values not loading
        //getCategoryDropdown().selectByIndex(getRandomNumber(2, categoryValues.size()));
        //getSubcategoryDropdown().selectByIndex(getRandomNumber(2, subcategoryValues.size()));
        getCategoryDropdown().selectByVisibleText("Fashion");
        getSubcategoryDropdown().selectByVisibleText("Shirts");
        getDescriptionInput().sendKeys(description);
        getNextBtn().click();
        return new SellerPageSetPrices(getDriver());
    }
}
