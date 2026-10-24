package planner5d.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import planner5d.core.actions.WebActions;
import planner5d.core.utils.WaitUtils;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectPage {

    private final WebDriver driver;
    private final WebActions actions;

    private final By furnishCatalogButton = By.xpath("//button[@data-testid = 'catalog-interior']");
    private final By catalogContainer = By.xpath("//div[@class = 'catalog is-active']");
    private final By searchInput = By.xpath("//input[@placeholder= 'Search']");
    private final By sofaCatalogItem = By.xpath("//img[contains(@src, '220/57')]/ancestor::div[contains(@class, 'catalog-item')]");
    private final By roomObject = By.cssSelector("g[etype='BgRooms']");

    private final By mode3dButton = By.xpath("//div[contains(@class, 'sliding-tabs')]/button[text()='3D' and not(@data-testid)]");
    private final By editorCanvas = By.id("wrapCanvas");

    private final By savingIndicator = By.xpath("//span[@class = 'header-project-sync is-saving']");

    @Getter
    private final By undoButton = By.xpath("//button[@class = 'undo-button']");

    @Getter
    private final By itemIn2dProject = By.cssSelector("g[etype='Ns1']");

    @Getter
    private final By toolTipFlipX = By.xpath("//div[@data-testid = 'item-menu-action-flipX']");

    @Getter
    private final By toolTipFlipY = By.xpath("//div[@data-testid = 'item-menu-action-flipY']");

    @Getter
    private final By toolTipFavoritesAdd = By.xpath("//div[@data-testid = 'item-menu-action-favoritesAdd']");

    @Getter
    private final By toolTipDelete = By.xpath("//div[@data-testid = 'item-menu-action-remove']");

    @Getter
    private final By toolTipRotate = By.xpath("//div[@data-testid = 'item-menu-action-rotate']");

    @Getter
    private final By toolTipMoveZ = By.xpath("//div[@data-testid = 'item-menu-action-moveZ']");

    @Getter
    private final By toolTipCreateComment = By.xpath("//div[@data-testid = 'item-menu-action-createComment']");

    @Getter
    private final By toolTipMaterials = By.xpath("//div[@data-testid = 'item-menu-action-materials']");

    @Getter
    private final By toolTipClone = By.xpath("//div[@data-testid = 'item-menu-action-clone']");

    @Getter
    private final By toolTipObjectCenter = By.xpath("//div[@data-testid = 'item-menu-action-objectCenter']");

    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new WebActions(driver);
        WaitUtils.waitForVisibility(driver, roomObject);
    }

    public Map<String, By> getSofaCircularMenuButtons() {
        Map<String, By> menuButtons = new LinkedHashMap<>();
        menuButtons.put("Flip X Button", toolTipFlipX);
        menuButtons.put("Flip Y Button", toolTipFlipY);
        menuButtons.put("Add to Favorites Button", toolTipFavoritesAdd);
        menuButtons.put("Delete Button", toolTipDelete);
        menuButtons.put("Rotate Button", toolTipRotate);
        menuButtons.put("Move Z (Up/Down) Button", toolTipMoveZ);
        menuButtons.put("Create Comment Button", toolTipCreateComment);
        menuButtons.put("Materials Button", toolTipMaterials);
        menuButtons.put("Clone Button", toolTipClone);
        menuButtons.put("Center Object Button", toolTipObjectCenter);
        return menuButtons;
    }

    @Step("Open furnish catalog")
    public ProjectPage openFurnishCatalog() {
        actions.click(furnishCatalogButton, 15);
        actions.waitForVisibility(catalogContainer);
        return this;
    }

    @Step("Search for item: {itemName}")
    public ProjectPage searchForItem(String itemName) {
        actions.type(searchInput, itemName);
        return this;
    }

    @Step("Add sofa to the project by clicking")
    public ProjectPage addSofaFromCatalog() {
        actions.click(sofaCatalogItem);
        return this;
    }

    @Step("Switch to 3D mode")
    public ProjectPage switchTo3D() {
        actions.click(mode3dButton);
        return this;
    }

    @Step("Click on the 3D canvas to select the sofa")
    public ProjectPage clickCanvasToSelectSofa() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .until(d -> {
                    try {
                        actions.clickByOffset(editorCanvas, 0, 70);
                        return d.findElement(toolTipDelete).isDisplayed();
                    } catch (Exception e) {
                        return false;
                    }
                });
        return this;
    }

    @Step("Click Undo button")
    public ProjectPage clickUndo() {
        actions.click(undoButton);
        return this;
    }

    @Step("Wait for project synchronization (auto-save) to complete")
    public ProjectPage waitForSyncComplete() {
        WaitUtils.waitForInvisibility(driver, savingIndicator, Duration.ofSeconds(15));
        return this;
    }

    @Step("Get project hash from URL")
    public String getProjectHash() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.split("key=")[1].split("&")[0];
    }
}