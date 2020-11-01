package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_objects.PageBase;

public class AccountPage extends PageBase {
    final private static String PAGE_URL_REGEX = "\\/my_account\\d*";
    final private static String ACCOUNT_CREATED_SUCCESS_MESSAGE_XPATH = "//*[@id='root']/div/div[3]";
    final private static String LOGOUT_BUTTON_XPATH = "//*[@id='root']/div/div[1]/div[2]/a";

    public AccountPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    @FindBy(xpath = ACCOUNT_CREATED_SUCCESS_MESSAGE_XPATH)
    private WebElement createAccSuccessMessage;

    @FindBy(xpath = LOGOUT_BUTTON_XPATH)
    private WebElement logoutButton;

    public WebElement getSuccessMessage(){
        return createAccSuccessMessage;
    }

    public WebElement getLogoutButton(){
        return logoutButton;
    }

    public HomePage clickLogoutButton(){
        getLogoutButton().click();
        return new HomePage(getDriver());
    }

    public Boolean verifySuccessMessage(String successMessage){
        if(!successMessage.equals(getSuccessMessage().getText()))
            System.out.println("Actual message: "+getSuccessMessage().getText()+"Expected message: "+successMessage);
        return getSuccessMessage().getText().contains(successMessage);
    }
}
