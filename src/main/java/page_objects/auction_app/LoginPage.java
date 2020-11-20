package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page_objects.PageBase;

public class LoginPage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/login\\d*";
    final static private String EMAIL_INPUT_XPATH = "//*[@id='root']/div/div[3]/div/form/div[1]/input";
    final static private String PASSWORD_INPUT_XPATH = "//*[@id=\"root\"]/div/div[3]/div/form/div[2]/input";
    final static private String LOGIN_BTN_XPATH = "//*[@id=\"root\"]/div/div[3]/div/form/button";

    public LoginPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    @FindBy(xpath = EMAIL_INPUT_XPATH)
    private WebElement emailInputField;

    @FindBy(xpath = PASSWORD_INPUT_XPATH)
    private WebElement passwordInputField;

    @FindBy(xpath = LOGIN_BTN_XPATH)
    private WebElement loginBtn;

    public WebElement getEmailInputField(){
        return emailInputField;
    }

    public WebElement getPasswordInputField(){
        return passwordInputField;
    }

    public WebElement getLoginBtn(){
        return loginBtn;
    }

    public void populateLoginForm(String email, String password){
        getEmailInputField().sendKeys(email);
        getPasswordInputField().sendKeys(password);
        new HomePage(getDriver()).waitForElementToBeClickable(getLoginBtn());
        getLoginBtn().click();
    }
}
