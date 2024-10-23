package insider.pageobject;

import insider.handlers.SeleniumHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageObject extends SeleniumHandler {
    public PageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
