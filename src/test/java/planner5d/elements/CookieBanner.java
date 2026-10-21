package planner5d.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import planner5d.core.actions.WebActions;

public class CookieBanner {

    private final WebActions actions;
    private final By acceptButton = By.id("onetrust-accept-btn-handler");

    public CookieBanner(WebDriver driver) {
        this.actions = new WebActions(driver);
    }

    @Step("Accept cookies if banner is present")
    public void acceptCookiesIfPresent() {
        try {
            actions.click(acceptButton, 5);
        } catch (TimeoutException e) {
            System.out.println("Cookie banner not found.");
        }
    }
}