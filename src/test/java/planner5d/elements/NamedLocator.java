package planner5d.elements;

import lombok.Getter;
import org.openqa.selenium.By;

public class NamedLocator {

    @Getter
    private final String name;

    @Getter
    private final By locator;

    public NamedLocator(String name, By locator) {
        this.name = name;
        this.locator = locator;
    }
}