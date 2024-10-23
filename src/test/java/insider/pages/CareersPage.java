package insider.pages;

import insider.pageobject.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersPage extends PageObject {

    public CareersPage() {
        super(webDriver);
    }

    @FindBy(xpath = "//*[@data-id='0696480']")
    public WebElement txtOurStory;

    @FindBy(xpath = "//*[@data-id='38b8000']")
    public WebElement txtOurLocations;

    @FindBy(xpath = "//*[@data-id='a8e7b90']")
    public WebElement txtLifeAtInsider;

    @FindBy(xpath = "//*[@data-id='4a40266']")
    public WebElement txtTeams;


    public boolean checkOurStory() {
        return txtOurStory.isDisplayed();
    }

    public boolean checkMainOurLocations() {
        return txtOurLocations.isDisplayed();
    }

    public boolean checkLifeAtInsider() {
        return txtLifeAtInsider.isDisplayed();
    }

    public boolean checkTeams() {
        return txtTeams.isDisplayed();
    }


}