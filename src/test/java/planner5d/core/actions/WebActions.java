package planner5d.core.actions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import planner5d.core.utils.WaitUtils;
import java.time.Duration;

public class WebActions {

    private final WebDriver driver;

    public WebActions(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on element: {locator}")
    public void click(By locator) {
        WaitUtils.executeSafely(driver, () -> {
            WebElement element = WaitUtils.waitForVisibility(driver, locator);
            element.click();
        });
    }

    @Step("Click on element: {locator} with timeout {timeoutSeconds}s")
    public void click(By locator, int timeoutSeconds) {
        WaitUtils.executeSafely(driver, () -> {
            WebElement element = WaitUtils.waitForVisibility(driver, locator, Duration.ofSeconds(timeoutSeconds));
            element.click();
        });
    }

    @Step("Type text '{text}' into element: {locator}")
    public void type(By locator, String text) {
        WaitUtils.executeSafely(driver, () -> {
            WebElement element = WaitUtils.waitForVisibility(driver, locator);
            element.clear();
            element.sendKeys(text);
        });
    }

    @Step("Wait for element to be visible: {locator}")
    public void waitForVisibility(By locator) {
        WaitUtils.waitForVisibility(driver, locator);
    }

    @Step("Click on element {locator} with offset X:{xOffset}, Y:{yOffset}")
    public void clickByOffset(By locator, int xOffset, int yOffset) {
        WaitUtils.executeSafely(driver, () -> {
            WebElement element = WaitUtils.waitForVisibility(driver, locator);
            new Actions(driver)
                    .moveToElement(element, xOffset, yOffset)
                    .click()
                    .build()
                    .perform();
        });
    }
}