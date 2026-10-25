package planner5d.core.utils;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import planner5d.core.models.AuthCookieData;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AuthUtils {

    @Step("Inject session cookie into browser")
    public static void injectSessionCookie(WebDriver driver, AuthCookieData data) {
        driver.get(data.url());
        driver.manage().deleteAllCookies();

        if (data.visitorToken() != null && !data.visitorToken().isEmpty()) {
            driver.manage().addCookie(new Cookie.Builder("p5dvisitor", data.visitorToken())
                    .domain(data.domain())
                    .path("/")
                    .build());
            log.info("Injected 'p5dvisitor' cookie");
        }

        if (data.phpSessionId() != null && !data.phpSessionId().isEmpty()) {
            driver.manage().addCookie(new Cookie.Builder("PHPSESSID", data.phpSessionId())
                    .domain(data.domain())
                    .path("/")
                    .build());
            log.info("Injected 'PHPSESSID' cookie");
        }
    }

    public static Map<String, String> getSeleniumCookiesAsMap(WebDriver driver) {
        log.info("Extracting Selenium cookies for API request");
        return driver.manage().getCookies().stream()
                .collect(Collectors.toMap(
                        org.openqa.selenium.Cookie::getName,
                        org.openqa.selenium.Cookie::getValue
                ));
    }
}