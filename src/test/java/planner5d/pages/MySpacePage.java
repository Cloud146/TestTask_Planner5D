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

    private final NamedLocator testProjectButton = new NamedLocator("TestProject Button", By.xpath("//button[@aria-label= 'TestProject']"));

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

    @Step("Open 'TestProject' workspace")
    public ProjectPage openTestProject() {
        actions.click(testProjectButton, 25);
        return new ProjectPage(driver);
    }
}
