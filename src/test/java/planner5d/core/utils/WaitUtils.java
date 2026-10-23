package planner5d.core.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public static WebElement waitForClickability(WebDriver driver, By locator) {
        return waitForClickability(driver, locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForClickability(WebDriver driver, By locator, Duration timeout) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return waitForVisibility(driver, locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForVisibility(WebDriver driver, By locator, Duration timeout) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void executeSafely(WebDriver driver, Runnable action) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    action.run();
                    return true;
                });
    }
}