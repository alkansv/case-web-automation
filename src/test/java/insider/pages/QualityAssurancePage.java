package insider.pages;

import insider.pageobject.PageObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class QualityAssurancePage extends PageObject {

    public QualityAssurancePage() {
        super(webDriver);
    }

    private final Actions actions = new Actions(webDriver);

    @FindBy(xpath = "//*[contains(text(),'See all')]")
    public WebElement btnSeeAllQAJobs;

    @FindBy(xpath = "//*[@id='select2-filter-by-location-container']")
    public WebElement filterLocation;

    @FindBy(xpath = "//*[@id='select2-filter-by-department-container']")
    public WebElement filterDepartment;

    @FindBy(xpath = "//li[contains(text(),'Istanbul, Turkey')]")
    public WebElement filterLocationIstanbul;

    @FindBy(xpath = "//li[contains(text(),'Quality Assurance')]")
    public WebElement filterDepartmentQA;

    @FindBy(xpath = "//div[contains(@class,'position-list-item-wrapper')]")
    public List<WebElement> listPosition;

    @FindBy(xpath = "(//p[contains(@class,'position-title font-weight-bold')])")
    public List<WebElement> listPositionTitle;

    @FindBy(xpath = "(//div[contains(@class,'position-location')])")
    public List<WebElement> listPositionLocation;

    @FindBy(xpath = "(//a[contains(text(),'View Role')])[1]")
    public WebElement btnFirstViewRole;

    @FindBy(xpath = "(//i[contains(@class,'icon-arrow-right')])[2]")
    public WebElement btnNext;


    public void clickSeeAllQAJobs() {
        clickWebElement(btnSeeAllQAJobs, 5);
        waitBySeconds(5);
    }

    public void selectFilterLocation() {
        clickWebElement(filterLocation, 5);
        clickWebElement(filterLocationIstanbul, 5);
    }

    public void selectFilterDepartment() {
        clickWebElement(filterDepartment, 5);
        clickWebElement(filterDepartmentQA, 5);
        waitBySeconds(5);
    }

    public void checkFirstPositionVisibility() {
        Assert.assertTrue(listPosition.get(0).isDisplayed());
    }

    public void checkAllTitleControl(String title) {
        int size = listPositionTitle.size();
        for (int i = 0; i < size; i++) {
            Assert.assertTrue(listPositionTitle.get(i).getText() + " not  same", listPositionTitle.get(i).getText().contains(title));
        }
    }

    public void checkAllLocationControl(String location) {
        int size = listPositionLocation.size();
        for (int i = 0; i < size; i++) {
            Assert.assertEquals(listPositionLocation.get(i).getText() + " not same", location, listPositionLocation.get(i).getText());
        }
    }

    public void clickFirstViewRole() {
        scrollToElement(btnNext, actions);
        clickAndHold(listPositionTitle.get(0), actions);
        clickWebElement(btnFirstViewRole, 5);
        waitBySeconds(5);
    }
}