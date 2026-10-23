package planner5d.tests;

import org.openqa.selenium.By;
import planner5d.core.assertions.ElementAssert;
import planner5d.core.config.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected TestConfig config;

    @BeforeMethod
    public void setUp() {
        config = ConfigFactory.create(TestConfig.class);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected ElementAssert assertThat(By locator, String elementName) {
        return ElementAssert.assertThat(driver, locator, elementName);
    }
}
