package planner5d.tests;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import planner5d.core.models.AuthCookieData;
import planner5d.core.utils.AuthUtils;
import planner5d.elements.CookieBanner;

public class BlankTest extends BaseTest{


    @Test
    public void test() throws InterruptedException {

        driver.get("https://planner5d.com/");

        CookieBanner cookieBanner = new CookieBanner(driver);
        cookieBanner.acceptCookiesIfPresent();

        AuthCookieData cookieData = new AuthCookieData(config.baseUrl(), config.cookieName(), config.cookieValue());

        AuthUtils.injectSessionCookie(driver, cookieData);

        driver.get("https://planner5d.com/editor?key=0a315214bf2c682d85ffc80bdff4468d&new_project=1&accessToken=eyJwcm9qZWN0SGFzaCI6IjBhMzE1MjE0YmYyYzY4MmQ4NWZmYzgwYmRmZjQ0NjhkIn0%3D.f8835ae340edf701fbc1ec22092197a8");

        Thread.sleep(9000);
        System.out.println("weilkjf ewol");

    }
}
