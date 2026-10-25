package planner5d.core.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface TestConfig extends Config {

    @Key("base.url")
    @DefaultValue("https://planner5d.com")
    String baseUrl();

    @Key("cookie.domain")
    @DefaultValue(".planner5d.com")
    String cookieDomain();

    @Key("cookie.visitor")
    String cookieVisitor();

    @Key("cookie.session")
    String cookieSession();

    @Key("project.name")
    @DefaultValue("TestProject")
    String projectName();
}
