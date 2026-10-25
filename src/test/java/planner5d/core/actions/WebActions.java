package planner5d.core.actions;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import planner5d.core.utils.WaitUtils;
import planner5d.elements.NamedLocator;
import java.time.Duration;

@Slf4j
public class WebActions {

    private final WebDriver driver;

    public WebActions(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on element: {element.name}")
    public void click(NamedLocator element) {
        log.info("Clicking on element: {}", element.getName());
        WaitUtils.executeSafely(driver, () -> {
            WebElement webElement = WaitUtils.waitForVisibility(driver, element.getLocator());
            webElement.click();
        });
    }

    @Step("Click on element: {element.name} with timeout {timeoutSeconds}s")
    public void click(NamedLocator element, int timeoutSeconds) {
        log.info("Clicking on element: {} with timeout {}s", element.getName(), timeoutSeconds);
        WaitUtils.executeSafely(driver, () -> {
            WebElement webElement = WaitUtils.waitForVisibility(driver, element.getLocator(), Duration.ofSeconds(timeoutSeconds));
            webElement.click();
        });
    }

    @Step("Type text '{text}' into element: {element.name}")
    public void type(NamedLocator element, String text) {
        log.info("Typing text '{}' into element: {}", text, element.getName());
        WaitUtils.executeSafely(driver, () -> {
            WebElement webElement = WaitUtils.waitForVisibility(driver, element.getLocator());
            webElement.clear();
            webElement.sendKeys(text);
        });
    }

    @Step("Wait for element to be visible: {element.name}")
    public void waitForVisibility(NamedLocator element) {
        log.info("Waiting for visibility of element: {}", element.getName());
        WaitUtils.waitForVisibility(driver, element.getLocator());
    }

    @Step("Click on element {element.name} with offset X:{xOffset}, Y:{yOffset}")
    public void clickByOffset(NamedLocator element, int xOffset, int yOffset) {
        log.info("Clicking on element: {} with offset X:{}, Y:{}", element.getName(), xOffset, yOffset);
        WaitUtils.executeSafely(driver, () -> {
            WebElement webElement = WaitUtils.waitForVisibility(driver, element.getLocator());
            new Actions(driver)
                    .moveToElement(webElement, xOffset, yOffset)
                    .click()
                    .build()
                    .perform();
        });
    }
}