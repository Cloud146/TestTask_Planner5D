package planner5d.core.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class ProjectApiService {

    @Step("GET project JSON data from API")
    public Response getProjectData(String projectHash, Map<String, String> cookies) {
        return RestAssured.given()
                .baseUri("https://planner5d.com")
                .basePath("/api/project/" + projectHash)
                .cookies(cookies)
                .header("Accept", "application/json")
                .header("X-Requested-With", "XMLHttpRequest")
                .get();
    }
}