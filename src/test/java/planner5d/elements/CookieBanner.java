package planner5d.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CookieBanner {

    private final WebDriver driver;

    private final By acceptButton = By.id("onetrust-accept-btn-handler");

    public CookieBanner(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(acceptButton));
            button.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie banner not found.");
        }
    }
}