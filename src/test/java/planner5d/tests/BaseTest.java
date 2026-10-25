package planner5d.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import planner5d.core.assertions.ElementAssert;
import planner5d.core.config.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import planner5d.elements.NamedLocator;
import java.time.Duration;

@Slf4j
public abstract class BaseTest {

    protected WebDriver driver;
    protected TestConfig config;

    @BeforeClass
    public void setUp() {
        log.info("[BaseTest] Initializing configuration and starting browser");
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

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            log.info("[BaseTest] Closing browser and tearing down WebDriver");
            driver.quit();
        }
    }

    protected ElementAssert assertThat(NamedLocator locator) {
        return ElementAssert.assertThat(driver, locator);
    }
}
