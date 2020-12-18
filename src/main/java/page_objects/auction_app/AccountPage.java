package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.util.List;

public class AccountPage extends PageBase {
    final private static String PAGE_URL_REGEX = "\\/my_account\\d*";
    final private static String SUCCESS_MESSAGE_XPATH = "//*[@id='root']/div/div[3]";
    final static private String ACCOUNT_TABS_XPATH = "//*[@id='root']/div/div[3]/div/div[1]/button";
    final private static String CLOSE_ALERT_BTN = "//*[@id='root']/div/div[3]/button";

    public AccountPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 20);

    private void waitForVisibilityOfElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @FindBy(xpath = ACCOUNT_TABS_XPATH)
    private List<WebElement> accountTabs;

    @FindBy(xpath = CLOSE_ALERT_BTN)
    private WebElement closeAlertBtn;

    @FindBy(xpath = SUCCESS_MESSAGE_XPATH)
    private WebElement createAccSuccessMessage;

    //GETTERS

    public WebElement getCloseAlertBtn(){
        return closeAlertBtn;
    }

    public List<WebElement> getAccountTabs(){
        return accountTabs;
    }

    public WebElement getSuccessMessage(){
        return createAccSuccessMessage;
    }

    //METHODS

    public void clickTab(int index){
        getAccountTabs().get(index).click();
    }

    public void closeAlertBtn(){
        waitForVisibilityOfElement(getCloseAlertBtn());
        waitForElementToBeClickable(getCloseAlertBtn());
        getCloseAlertBtn().click();
    }

    public Boolean verifySuccessMessage(String successMessage){
        waitForVisibilityOfElement(getCloseAlertBtn());
        System.out.println("Displayed message:" + getSuccessMessage().getText());
        return getSuccessMessage().getText().contains(successMessage);
    }

    public boolean verifyActiveTab(String tab, int index){
        return getAccountTabs().get(index).getText().equals(tab);
    }

}
