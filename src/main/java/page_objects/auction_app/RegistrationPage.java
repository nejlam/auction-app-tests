package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

public class RegistrationPage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/register\\d*";
    final static private String FIRST_NAME_INPUT_XPATH = "//*[@id='root']/div/div[3]/div/form/div[1]/input";
    final static private String LAST_NAME_INPUT_XPATH = "//*[@id='root']/div/div[3]/div/form/div[2]/input";
    final static private String EMAIL_INPUT_XPATH = "//*[@id='root']/div/div[3]/div/form/div[3]/input";
    final static private String PASSWORD_INPUT_XPATH = "//*[@id='root']/div/div[3]/div/form/div[4]/input";
    final static private String SUBMIT_BUTTON_XPATH = "//*[@id='root']/div/div[3]/div/form/button";
    final static private String ALERT_MSG_XPATH = "//*[@id='root']/div/div[3]";
    final static private String AGREE_TO_TERMS_CHECK_XPATH = "//*[@id='root']/div/div[3]/div/form/div[5]/div[1]/label";
    final static private String ERROR_AGREE_TO_TERMS_XPATH = "//*[@id='root']/div/div[3]/div/form/div[5]/div[2]";
    final private static String CLOSE_ALERT_BTN = "//*[@id='root']/div/div[3]/button";

    public RegistrationPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    WebDriverWait wait = new WebDriverWait(getDriver(),30);

    private void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    @FindBy(xpath = FIRST_NAME_INPUT_XPATH)
    private WebElement firstNameInputField;

    @FindBy(xpath = LAST_NAME_INPUT_XPATH)
    private WebElement lastNameInputField;

    @FindBy(xpath = EMAIL_INPUT_XPATH)
    private WebElement emailInputField;

    @FindBy(xpath = PASSWORD_INPUT_XPATH)
    private WebElement passwordInputField;

    @FindBy(xpath = SUBMIT_BUTTON_XPATH)
    private WebElement submitButton;

    @FindBy(xpath = ALERT_MSG_XPATH)
    private WebElement alertMessage;

    @FindBy(xpath = AGREE_TO_TERMS_CHECK_XPATH)
    private WebElement agreeToTermsCheck;

    @FindBy(xpath = ERROR_AGREE_TO_TERMS_XPATH)
    private WebElement errorAgreeToTerms;

    @FindBy(xpath = CLOSE_ALERT_BTN)
    private WebElement closeAlert;

    //GETTERS

    public WebElement getCloseAlert(){
        return closeAlert;
    }

    public WebElement getErrorAgreeToTerms(){
        return errorAgreeToTerms;
    }

    public WebElement getAgreeToTermsCheck(){
        return agreeToTermsCheck;
    }

    public WebElement getFirstNameInputField(){
        return firstNameInputField;
    }

    public WebElement getLastNameInputField(){
        return lastNameInputField;
    }

    public WebElement getEmailInputField(){
        return emailInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getSubmitButton(){
        return submitButton;
    }

    public WebElement getAlertMessage(){
        return alertMessage;
    }

    //METHODS

    public void populateForm(String firstName, String lastName, String email, String password){
        getFirstNameInputField().sendKeys(firstName);
        getLastNameInputField().sendKeys(lastName);
        getEmailInputField().sendKeys(email);
        getPasswordInputField().sendKeys(password);
        getSubmitButton().click();
    }

    public void clickAgreeToTermsCheck(){
        getAgreeToTermsCheck().click();
    }

    public boolean verifyTermsAndConditionsErrorMsg(String errorMsg){
        waitForVisibility(getErrorAgreeToTerms());
        String errorTxt = getErrorAgreeToTerms().getText();
        if(errorTxt.equals(errorMsg)){
            System.out.println("Error message: " + errorTxt);
        } else System.out.println("Error message is not present");
        return errorTxt.equals(errorMsg);
    }

    public boolean verifyAlertMsgTxt(String msg){
        waitForVisibility(getCloseAlert());
        boolean alert = getAlertMessage().getText().contains(msg);
        if(alert){
            System.out.println("Alert message: " + getAlertMessage().getText());
        } else System.out.println("Alert message is not present");
        return alert;
    }

}
