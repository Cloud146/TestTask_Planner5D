package planner5d.elements;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import planner5d.core.actions.WebActions;

@Slf4j
public class CookieBanner {

    private final WebActions actions;
    private final NamedLocator acceptButton = new NamedLocator("Cookie Accept Button", By.id("onetrust-accept-btn-handler"));

    public CookieBanner(WebDriver driver) {
        this.actions = new WebActions(driver);
    }

    @Step("Accept cookies if banner is present")
    public void acceptCookiesIfPresent() {
        try {
            actions.click(acceptButton, 5);
        } catch (TimeoutException e) {
            log.info("Cookie banner not found or already accepted.");
        }
    }
}