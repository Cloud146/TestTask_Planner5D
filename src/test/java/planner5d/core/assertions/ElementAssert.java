package planner5d.core.assertions;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import planner5d.core.utils.WaitUtils;

public class ElementAssert extends AbstractAssert<ElementAssert, By> {

    private final WebDriver driver;
    private final String elementName;

    public ElementAssert(WebDriver driver, By locator, String elementName) {
        super(locator, ElementAssert.class);
        this.driver = driver;
        this.elementName = elementName;
    }

    public static ElementAssert assertThat(WebDriver driver, By locator, String elementName) {
        return new ElementAssert(driver, locator, elementName);
    }

    @Step("Verify that visibility of '{this.elementName}' is {expected}")
    public ElementAssert isDisplayed(boolean expected) {
        isNotNull();

        boolean actualState;
        try {
            WebElement element = WaitUtils.waitForVisibility(driver, actual);
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
}