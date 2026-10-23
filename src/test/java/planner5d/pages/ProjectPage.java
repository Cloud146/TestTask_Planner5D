package planner5d.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import planner5d.core.actions.WebActions;
import planner5d.core.utils.WaitUtils;

public class ProjectPage {

    private final WebDriver driver;
    private final WebActions actions;

    private final By furnishCatalogButton = By.xpath("//button[@data-testid = 'catalog-interior']");
    private final By catalogContainer = By.xpath("//div[@class = 'catalog is-active']");
    private final By searchInput = By.xpath("//input[@placeholder= 'Search']");
    private final By sofaCatalogItem = By.xpath("//img[contains(@src, '220/57')]/ancestor::div[contains(@class, 'catalog-item')]");
    private final By roomObject = By.cssSelector("g[etype='BgRooms']");

    @Getter
    private final By itemIn2dProject = By.cssSelector("g[etype='Ns1']");
    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new WebActions(driver);
        WaitUtils.waitForVisibility(driver, roomObject);
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
}