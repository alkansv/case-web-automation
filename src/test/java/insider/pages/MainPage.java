package insider.pages;

import insider.pageobject.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageObject {

    public MainPage() {
        super(webDriver);
    }

    @FindBy(xpath = "(//*[@id='navbarDropdownMenuLink'])[5]")
    public WebElement btnMainPageCompany;

    @FindBy(xpath = "//a[@class='dropdown-sub' and contains(text(),'Careers')]")
    public WebElement btnMainPageCareers;


    public boolean checkOpenMainPage() {
        return btnMainPageCompany.isDisplayed();
    }

    public void clickCompany() {
        clickWebElement(btnMainPageCompany,5);
    }

    public void clickCareers() {
        clickWebElement(btnMainPageCareers,5);
    }

}