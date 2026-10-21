package planner5d.core.actions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebActions {

    private final WebDriver driver;
    private final WebDriverWait defaultWait;

    public WebActions(WebDriver driver) {
        this.driver = driver;
        this.defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Click on element: {locator}")
    public void click(By locator) {
        WebElement element = defaultWait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    @Step("Click on element: {locator} with timeout {timeoutSeconds}s")
    public void click(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
}