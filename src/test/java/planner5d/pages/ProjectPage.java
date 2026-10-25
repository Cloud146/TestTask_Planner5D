package planner5d.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import planner5d.core.actions.WebActions;
import planner5d.core.utils.WaitUtils;
import planner5d.elements.NamedLocator;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ProjectPage {

    private final WebDriver driver;
    private final WebActions actions;

    private final NamedLocator furnishCatalogButton = new NamedLocator("Furnish Catalog Button", By.xpath("//button[@data-testid = 'catalog-interior']"));
    private final NamedLocator catalogContainer = new NamedLocator("Catalog Container", By.xpath("//div[@class = 'catalog is-active']"));
    private final NamedLocator searchInput = new NamedLocator("Search Input", By.xpath("//input[@placeholder= 'Search']"));
    private final NamedLocator sofaCatalogItem = new NamedLocator("Sofa Catalog Item", By.xpath("//img[contains(@src, '220/57')]/ancestor::div[contains(@class, 'catalog-item')]"));
    private final NamedLocator roomObject = new NamedLocator("Room Object", By.cssSelector("g[etype='BgRooms']"));

    private final NamedLocator mode3dButton = new NamedLocator("3D Mode Button", By.xpath("//div[contains(@class, 'sliding-tabs')]/button[text()='3D' and not(@data-testid)]"));
    private final NamedLocator editorCanvas = new NamedLocator("Editor Canvas", By.id("wrapCanvas"));

    private final NamedLocator savingIndicator = new NamedLocator("Saving Indicator", By.xpath("//span[@class = 'header-project-sync is-saving']"));

    @Getter
    private final NamedLocator undoButton = new NamedLocator("Undo Button", By.xpath("//button[@class = 'undo-button']"));

    @Getter
    private final NamedLocator itemIn2dProject = new NamedLocator("Added Sofa Node (Ns1)", By.cssSelector("g[etype='Ns1']"));

    @Getter private final NamedLocator toolTipFlipX = new NamedLocator("Flip X Button", By.xpath("//div[@data-testid = 'item-menu-action-flipX']"));
    @Getter private final NamedLocator toolTipFlipY = new NamedLocator("Flip Y Button", By.xpath("//div[@data-testid = 'item-menu-action-flipY']"));
    @Getter private final NamedLocator toolTipFavoritesAdd = new NamedLocator("Add to Favorites Button", By.xpath("//div[@data-testid = 'item-menu-action-favoritesAdd']"));
    @Getter private final NamedLocator toolTipDelete = new NamedLocator("Delete Button", By.xpath("//div[@data-testid = 'item-menu-action-remove']"));
    @Getter private final NamedLocator toolTipRotate = new NamedLocator("Rotate Button", By.xpath("//div[@data-testid = 'item-menu-action-rotate']"));
    @Getter private final NamedLocator toolTipMoveZ = new NamedLocator("Move Z (Up/Down) Button", By.xpath("//div[@data-testid = 'item-menu-action-moveZ']"));
    @Getter private final NamedLocator toolTipCreateComment = new NamedLocator("Create Comment Button", By.xpath("//div[@data-testid = 'item-menu-action-createComment']"));
    @Getter private final NamedLocator toolTipMaterials = new NamedLocator("Materials Button", By.xpath("//div[@data-testid = 'item-menu-action-materials']"));
    @Getter private final NamedLocator toolTipClone = new NamedLocator("Clone Button", By.xpath("//div[@data-testid = 'item-menu-action-clone']"));
    @Getter private final NamedLocator toolTipObjectCenter = new NamedLocator("Center Object Button", By.xpath("//div[@data-testid = 'item-menu-action-objectCenter']"));

    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new WebActions(driver);
        WaitUtils.waitForVisibility(driver, roomObject.getLocator());
    }

    public List<NamedLocator> getSofaCircularMenuButtons() {
        return Arrays.asList(
                toolTipFlipX, toolTipFlipY, toolTipFavoritesAdd, toolTipDelete,
                toolTipRotate, toolTipMoveZ, toolTipCreateComment, toolTipMaterials,
                toolTipClone, toolTipObjectCenter
        );
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
                        return d.findElement(toolTipDelete.getLocator() ).isDisplayed();
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
        WaitUtils.waitForInvisibility(driver, savingIndicator.getLocator(), Duration.ofSeconds(15));
        return this;
    }

    @Step("Get project hash from URL")
    public String getProjectHash() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.split("key=")[1].split("&")[0];
    }
}