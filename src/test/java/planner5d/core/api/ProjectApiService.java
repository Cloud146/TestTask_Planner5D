package planner5d.core.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Slf4j
public class ProjectApiService {

    @Step("GET project JSON data from API")
    public Response getProjectData(String projectHash, Map<String, String> cookies) {
        log.info("Sending GET request to fetch project data for hash: {}", projectHash);
        return RestAssured.given()
                .baseUri("https://planner5d.com")
                .basePath("/api/project/" + projectHash)
                .cookies(cookies)
                .header("Accept", "application/json")
                .header("X-Requested-With", "XMLHttpRequest")
                .get();
    }

    @Step("Poll project data until Sofa is saved")
    public Response pollProjectDataUntilSofaIsSaved(String projectHash, Map<String, String> cookies, int timeoutSeconds) {
        log.info("Starting API polling in a Virtual Thread...");

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            return CompletableFuture.supplyAsync(() -> {
                long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);

                while (System.currentTimeMillis() < endTime) {
                    Response response = getProjectData(projectHash, cookies);

                    try {
                        List<String> classes = response.jsonPath().getList("items[0].data.items[0].items.className");
                        if (classes != null && classes.contains("Ns")) {
                            log.info("Sofa successfully synced to backend");
                            return response;
                        }
                    } catch (Exception e) {
                        log.debug("JSON structure not ready or items array is empty yet.");
                    }

                    log.info("Sofa not yet saved. Virtual thread will sleep for 2 seconds before retry...");
                    try {
                        Thread.sleep(Duration.ofSeconds(2));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Virtual Thread was interrupted", e);
                    }
                }
                throw new AssertionError("Sofa was not saved to backend API after " + timeoutSeconds + " seconds");

            }, executor).join();
        }
    }
}