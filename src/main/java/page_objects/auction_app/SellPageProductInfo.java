package page_objects.auction_app;


import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_objects.PageBase;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SellPageProductInfo extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/my_account/seller\\d*";
    final static private String ITEM_TITLE_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[1]/input";
    final static private String CATEGORY_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[1]/select";
    final static private String SUBCATEGORY_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[2]/select";
    final static private String DESCRIPTION_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[3]/textarea";
    final static private String NEXT_BTN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[6]/button[2]";
    final static private String CATEGORY_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[1]/select/option";
    final static private String SUBCATEGORY_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[2]/select/option";
    final static private String COLOR_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[4]/div[1]/select";
    final static private String SIZE_DROPDOWN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[4]/div[2]/select";
    final static private String COLOR_VALUES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[4]/div[1]/select/option";
    final static private String SIZE_VALUES = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[4]/div[2]/select/option";
    final static private String UPLOAD_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[5]/input";
    final static private String UPLOADED_FILES_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[5]/div/div[1]/div";
    final static private String STEP_TITLE = "//*[@id='root']/div/div[3]/div[2]/div[1]";

    public SellPageProductInfo(WebDriver driver){
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

    @FindBy(xpath = COLOR_DROPDOWN_XPATH)
    private WebElement colorDropdown;

    @FindBy(xpath = COLOR_VALUES_XPATH)
    private List<WebElement> colorValues;

    @FindBy(xpath = SIZE_DROPDOWN_XPATH)
    private WebElement sizeDropdown;

    @FindBy(xpath = SIZE_VALUES)
    private List<WebElement> sizeValues;

    @FindBy(xpath = UPLOAD_INPUT_XPATH)
    private WebElement uploadInput;

    @FindBy(xpath = UPLOADED_FILES_XPATH)
    private List<WebElement> uploadedFilesList;

    @FindBy(xpath = STEP_TITLE)
    private WebElement stepTitle;

    public WebElement getStepTitle(){
        return stepTitle;
    }

    public List<WebElement> getUploadedFilesList(){
        return uploadedFilesList;
    }

    public WebElement getUploadInput(){
        return uploadInput;
    }

    public List<WebElement> getSizeValues(){
        return sizeValues;
    }

    public Select getSizeDropdown(){
        return new Select(sizeDropdown);
    }

    public List<WebElement> getColorValues(){
        return colorValues;
    }

    public Select getColorDropdown(){
        return new Select(colorDropdown);
    }

    public List<WebElement> getSubcategoryValues(){
        return subcategoryValues;
    }

    public List<WebElement> getCategoryValues(){
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

    public Select getCategoryDropdown(){
        return new Select(categoryDropdown);
    }

    public Select getSubcategoryDropdown(){
        return new Select(subcategoryDropdown);
    }


    public SellPageSetPrices populateForm(String title, String description, String filePath, String extension, int quantity) throws InterruptedException {
        getItemTitleInput().sendKeys(title);
        getCategoryDropdown().selectByIndex(getRandomNumber(2, getCategoryValues().size()));
        getSubcategoryDropdown().selectByIndex(getRandomNumber(2, getSubcategoryValues().size()));
        getDescriptionInput().sendKeys(description);
        getColorDropdown().selectByIndex(getRandomNumber(2, getColorValues().size()));
        getSizeDropdown().selectByIndex(getRandomNumber(2, getSizeValues().size()));
        uploadPhotos(filePath,extension, quantity);
        getNextBtn().click();
        return new SellPageSetPrices(getDriver());
    }

    private void uploadPhotos(String filePath, String fileExtension, int quantity){
        int num = quantity + 1;
        for(int i = 1; i<num; i++){
            String photoPath = filePath + i + fileExtension;
            getUploadInput().sendKeys(photoPath);
        }
    }

    public boolean verifyNumberOfAddedPhotos(int quantity){
        int uploadedPhotosSize = getUploadedFilesList().size();
        return uploadedPhotosSize == quantity;
    }

    public boolean verifyStepTitle(String stepTitle){
        return getStepTitle().getText().equals(stepTitle);
    }
}
