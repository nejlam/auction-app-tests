package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_objects.PageBase;

import java.util.List;

public class AccountPage extends PageBase {
    final private static String PAGE_URL_REGEX = "\\/my_account\\d*";
    final private static String ACCOUNT_CREATED_SUCCESS_MESSAGE_XPATH = "//*[@id='root']/div/div[3]";
    final private static String LOGOUT_BUTTON_XPATH = "//*[@id='root']/div/div[1]/div[2]/a";
    final static private String ACCOUNT_TABS_XPATH = "//*[@id='root']/div/div[3]/div/div[1]/button";

    public AccountPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    @FindBy(xpath = ACCOUNT_CREATED_SUCCESS_MESSAGE_XPATH)
    private WebElement createAccSuccessMessage;

    @FindBy(xpath = LOGOUT_BUTTON_XPATH)
    private WebElement logoutButton;

    @FindBy(xpath = ACCOUNT_TABS_XPATH)
    private List<WebElement> accountTabs;

    public List<WebElement> getAccountTabs(){
        return accountTabs;
    }

    public WebElement getSuccessMessage(){
        return createAccSuccessMessage;
    }

    public WebElement getLogoutButton(){
        return logoutButton;
    }

    public void clickTab(int index){
        getAccountTabs().get(index).click();
    }

    public HomePage clickLogoutButton(){
        getLogoutButton().click();
        return new HomePage(getDriver());
    }

    public Boolean verifySuccessMessage(String successMessage){
        return getSuccessMessage().getText().contains(successMessage);
    }
}
