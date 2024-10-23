package insider.test;

import insider.handlers.ScreenshotUtil;
import insider.handlers.SeleniumHandler;
import insider.pages.CareersPage;
import insider.pages.MainPage;
import insider.pages.QualityAssurancePage;
import insider.pages.RoleDetailPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class RunTest extends SeleniumHandler {

    @Before
    public void setUp() {
        runWebDriver();
        navigateToMainPage();
    }

    @Test
    public void insiderTest() {
        MainPage mainPage = new MainPage();
        CareersPage careersPage = new CareersPage();
        QualityAssurancePage qualityAssurancePage = new QualityAssurancePage();
        RoleDetailPage roleDetailPage = new RoleDetailPage();

        Assert.assertTrue(mainPage.checkOpenMainPage());
        mainPage.clickCompany();
        mainPage.clickCareers();

        Assert.assertTrue(careersPage.checkOurStory());
        Assert.assertTrue(careersPage.checkMainOurLocations());
        Assert.assertTrue(careersPage.checkLifeAtInsider());
        Assert.assertTrue(careersPage.checkTeams());

        navigateToQAPage();
        qualityAssurancePage.clickSeeAllQAJobs();
        qualityAssurancePage.selectFilterLocation();
        qualityAssurancePage.selectFilterDepartment();
        qualityAssurancePage.checkFirstPositionVisibility();
        qualityAssurancePage.checkAllTitleControl("Quality Assurance");
        qualityAssurancePage.checkAllLocationControl("Istanbul, Turkey");
        qualityAssurancePage.clickFirstViewRole();
        switchChild();

        roleDetailPage.checkApplyButton();
    }

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        public void failed(Throwable e, Description description) {
            ScreenshotUtil.getScreenShot("/Users/sevgialkandemir/Desktop/screenshots/" + randomNumber(5) + ".png");
        }
    };
}
