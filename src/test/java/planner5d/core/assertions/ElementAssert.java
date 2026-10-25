package planner5d.core.assertions;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import planner5d.core.utils.WaitUtils;
import planner5d.elements.NamedLocator;
import java.time.Duration;

@Slf4j
public class ElementAssert extends AbstractAssert<ElementAssert, NamedLocator> {

    private final WebDriver driver;

    public ElementAssert(WebDriver driver, NamedLocator locator) {
        super(locator, ElementAssert.class);
        this.driver = driver;
    }

    public static ElementAssert assertThat(WebDriver driver, NamedLocator locator) {
        return new ElementAssert(driver, locator);
    }

    @Step("Verify that visibility of '{this.actual.name}' is {expected}")
    public ElementAssert isDisplayed(boolean expected) {
        isNotNull();
        String elementName = actual.getName();
        log.info("Verifying that visibility of '{}' is {}", elementName, expected);

        boolean actualState;
        try {
            WebElement element = WaitUtils.waitForVisibility(driver, actual.getLocator());
            actualState = element.isDisplayed();
        } catch (Exception e) {
            actualState = false;
        }

        Assertions.assertThat(actualState)
                .withFailMessage("Expected visibility of <%s> to be <%s>, but was <%s>",
                        elementName, expected, actualState)
                .isEqualTo(expected);
        return this;
    }

    @Step("Verify that '{this.actual.name}' is NOT displayed")
    public ElementAssert isNotDisplayed() {
        isNotNull();
        String elementName = actual.getName();
        log.info("Verifying that '{}' is NOT displayed", elementName);

        boolean isInvisible;
        try {
            isInvisible = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(actual.getLocator()));
        } catch (Exception e) {
            isInvisible = false;
        }

        Assertions.assertThat(isInvisible)
                .withFailMessage("Expected <%s> to disappear, but it is still visible in the DOM", elementName)
                .isTrue();

        return this;
    }
}