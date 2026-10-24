package planner5d.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import planner5d.core.models.AuthCookieData;
import planner5d.core.utils.AuthUtils;
import planner5d.pages.MySpacePage;
import planner5d.pages.ProjectPage;

public class TaskTest extends BaseTest{

    private ProjectPage projectPage;

    @BeforeClass(description = "Set up project")
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

    @Test(priority = 1, description = "Task 1: Verify clicking on a sofa in catalog adds it to the project")
    public void testSofaIsAddedToProjectIn2D(){

        projectPage.openFurnishCatalog()
                .searchForItem("Sofa")
                .addSofaFromCatalog();

        assertThat(projectPage.getItemIn2dProject(), "Added Sofa Node (Ns1)")
                .isDisplayed(true);
    }

    @Test(priority = 2, description = "Task 2: Verify that the added sofa is visible and selectable in 3D mode")
    public void testSofaIsVisibleIn3DMode() {

        projectPage.switchTo3D()
                .clickCanvasToSelectSofa();

        projectPage.getSofaCircularMenuButtons().forEach((elementName, locator) ->
                assertThat(locator, elementName).isDisplayed(true)
        );
    }

    @AfterClass(description = "Tear down: Clean up project canvas via Undo")
    public void cleanUp() {
        if (projectPage != null) {
            projectPage.clickUndo();

            assertThat(projectPage.getToolTipDelete(), "Delete Button (Sofa Menu)")
                    .isNotDisplayed();
        }
    }
}
