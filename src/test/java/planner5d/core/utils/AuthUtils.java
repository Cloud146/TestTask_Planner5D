package planner5d.core.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import planner5d.core.models.AuthCookieData;

public class AuthUtils {

    public static void injectSessionCookie(WebDriver driver, AuthCookieData cookieData) {
        driver.get(cookieData.url());

        if (cookieData.name() != null && !cookieData.name().isEmpty() &&
                cookieData.value() != null && !cookieData.value().isEmpty()) {

            Cookie authCookie = new Cookie(cookieData.name(), cookieData.value());
            driver.manage().addCookie(authCookie);
        }
    }
}