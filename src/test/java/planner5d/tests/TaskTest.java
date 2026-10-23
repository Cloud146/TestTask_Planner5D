package planner5d.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import planner5d.core.models.AuthCookieData;
import planner5d.core.utils.AuthUtils;
import planner5d.pages.MySpacePage;
import planner5d.pages.ProjectPage;

public class TaskTest extends BaseTest{

    private ProjectPage projectPage;

    @BeforeMethod
    public void setUpProject() {
        driver.get(config.baseUrl());

        AuthCookieData cookieData = new AuthCookieData(
                config.baseUrl(),
                config.cookieDomain(),
                config.cookieVisitor(),
                config.cookieSession()
        );

        AuthUtils.injectSessionCookie(driver, cookieData);
        driver.navigate().refresh();

        MySpacePage mySpacePage = new MySpacePage(driver);

        projectPage = mySpacePage
                .acceptCookie()
                .openTestProject();
    }

    @Test(description = "Task 1: Verify clicking on a sofa in catalog adds it to the project")
    public void testSofaIsAddedToProjectIn2D(){

        projectPage.openFurnishCatalog()
                .searchForItem("Sofa")
                .addSofaFromCatalog();

        assertThat(projectPage.getItemIn2dProject(), "Added Sofa Node (Ns1)")
                .isDisplayed(true);

    }
}
