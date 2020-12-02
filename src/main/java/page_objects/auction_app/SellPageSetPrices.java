package page_objects.auction_app;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.PageBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SellPageSetPrices extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/my_account/seller\\d*";
    final static private String PRICE_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[1]/div/input";
    final static private String START_DATE_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[1]/div[1]/div[1]/div/input";
    final static private String END_DATE_INPUT_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[2]/div[2]/div[1]/div[1]/div/input";
    final static private String NEXT_BTN_XPATH = "//*[@id='root']/div/div[3]/div[2]/div[2]/form/div[3]/button[2]";
    final static private String STEP_TITLE = "//*[@id='root']/div/div[3]/div[2]/div[1]";
    final static private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    final static private Date today = new Date();

    public SellPageSetPrices(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }

    WebDriverWait wait = new WebDriverWait(getDriver(),30);

    public void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private String getRandomDouble(){
        double min = 1.0;
        double max = 99999.99;
        double x = (Math.random() * ((max - min) + 1)) + min;
        double xRounded = Math.round(x * 100.0) / 100.0;
        return String.valueOf(xRounded);
    }

    private String getToday(){
        return dateFormat.format(today);
    }

    //adds 1 day to the current date
    private String addDaysToToday(int num){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, num);
        Date currentDatePlusOneDay = calendar.getTime();
        return dateFormat.format(currentDatePlusOneDay);
    }

    @FindBy(xpath = PRICE_INPUT_XPATH)
    private WebElement priceInput;

    @FindBy(xpath = START_DATE_INPUT_XPATH)
    private WebElement startDateInput;

    @FindBy(xpath = END_DATE_INPUT_XPATH)
    private WebElement endDateInput;

    @FindBy(xpath = NEXT_BTN_XPATH)
    private WebElement nextBtn;

    @FindBy(xpath = STEP_TITLE)
    private WebElement stepTitle;

    public WebElement getStepTitle(){
        return stepTitle;
    }

    public WebElement getNextBtn(){
        return nextBtn;
    }

    public WebElement getPriceInput(){
        return priceInput;
    }

    public WebElement getStartDateInput(){
        return startDateInput;
    }

    public WebElement getEndDateInput(){
        return endDateInput;
    }

    public void setDatesAndClickNext(String status){
        if(status.equals("active")){
            getStartDateInput().sendKeys(getToday(), Keys.ENTER);
            getEndDateInput().sendKeys(addDaysToToday(1), Keys.ENTER);
        } else if (status.equals("scheduled")){
            getStartDateInput().sendKeys(addDaysToToday(2), Keys.ENTER);
            getEndDateInput().sendKeys(addDaysToToday(5), Keys.ENTER);
        }
        waitElementToBeClickable(getNextBtn());
        getNextBtn().click();
    }

    public void setStartPrice(){
        getPriceInput().sendKeys(getRandomDouble());
    }

    public boolean verifyStepTitle(String stepTitle){
        waitForVisibility(getPriceInput());
        return getStepTitle().getText().equals(stepTitle);
    }


}
