package planner5d.core.models;

public record AuthCookieData(
        String url,
        String domain,
        String userId,
        String visitorToken,
        String phpSessionId
) {}