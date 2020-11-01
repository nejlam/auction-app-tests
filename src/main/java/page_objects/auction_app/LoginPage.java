package page_objects.auction_app;

import org.openqa.selenium.WebDriver;
import page_objects.PageBase;

public class LoginPage extends PageBase {
    final static private String PAGE_URL_REGEX = "\\/login\\d*";

    public LoginPage(WebDriver driver){
        super(driver, PAGE_URL_REGEX);
        initElements();
    }
}
