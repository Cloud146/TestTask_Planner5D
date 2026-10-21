package planner5d.core.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface TestConfig extends Config {

    @Key("base.url")
    @DefaultValue("https://planner5d.com")
    String baseUrl();

    @Key("cookie.name")
    String cookieName();

    @Key("cookie.value")
    String cookieValue();
}
