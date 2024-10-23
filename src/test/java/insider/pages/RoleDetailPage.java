package insider.pages;

import insider.pageobject.PageObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RoleDetailPage extends PageObject {

    public RoleDetailPage() {
        super(webDriver);
    }

    @FindBy(xpath = "(//a[contains(text(),'Apply for this job')])[1]")
    public WebElement btnApply;

    public void checkApplyButton() {
        Assert.assertTrue(btnApply.isDisplayed());
    }

}