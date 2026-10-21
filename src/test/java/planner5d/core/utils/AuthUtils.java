package planner5d.core.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import planner5d.core.models.AuthCookieData;

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
        }

        if (data.phpSessionId() != null && !data.phpSessionId().isEmpty()) {
            driver.manage().addCookie(new Cookie.Builder("PHPSESSID", data.phpSessionId())
                    .domain(data.domain())
                    .path("/")
                    .build());
        }
    }
}