package planner5d.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import planner5d.core.actions.WebActions;
import planner5d.elements.CookieBanner;
import planner5d.elements.NamedLocator;

public class MySpacePage {

    private final WebDriver driver;
    private final WebActions actions;

    public MySpacePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new WebActions(driver);
    }

    @Step("Accept cookie banner")
    public MySpacePage acceptCookie() {
        CookieBanner cookieBanner = new CookieBanner(driver);
        cookieBanner.acceptCookiesIfPresent();
        return this;
    }

    @Step("Open '{projectName}' workspace")
    public ProjectPage openProject(String projectName) {
        NamedLocator dynamicProjectButton = new NamedLocator(
                projectName + " Project Button",
                By.xpath(String.format("//button[@aria-label= '%s']", projectName))
        );

        actions.click(dynamicProjectButton, 35);
        return new ProjectPage(driver);
    }
}
