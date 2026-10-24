package planner5d.tests;

import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import planner5d.core.api.ProjectApiService;
import planner5d.core.models.AuthCookieData;
import planner5d.core.utils.AuthUtils;
import planner5d.core.utils.ScreenComparisonUtil;
import planner5d.pages.MySpacePage;
import planner5d.pages.ProjectPage;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import java.io.IOException;
import java.util.Map;

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

        ScreenComparisonUtil.compareScreenshots(driver, "sofa_3d_fullpage.png", 0.5);
    }

    @Test(priority = 3, description = "Task 3: Verify via API that the sofa is saved in the project JSON after 10 seconds")
    public void testSofaIsPresentInProjectApi() throws InterruptedException, IOException {

        System.out.println("Waiting 10 seconds");
        Thread.sleep(10000);

        Map<String, String> apiCookies = AuthUtils.getSeleniumCookiesAsMap(driver);
        String projectHash = projectPage.getProjectHash();

        ProjectApiService apiService = new ProjectApiService();
        Response response = apiService.getProjectData(projectHash, apiCookies);

        response.then().statusCode(200);

        response.then()
                .body("items[0].data.items[0].items.className", hasItem("Ns"))
                .body("items[0].data.items[0].items.find { it.className == 'Ns' }.id", equalTo("57"));

    }

    @AfterClass(description = "Tear down: Clean up project canvas via Undo")
    public void cleanUp() {
        if (projectPage != null) {
            projectPage.clickUndo().waitForSyncComplete();

            assertThat(projectPage.getToolTipDelete(), "Delete Button (Sofa Menu)")
                    .isNotDisplayed();
        }
    }
}
