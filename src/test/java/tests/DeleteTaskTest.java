package tests;

import DTO.ProjectDTO;
import DTO.TasksCreateDTO;
import Utils.UtilsForTests;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class DeleteTaskTest {

    private static String baseUrl;
    private static String authToken;
    private static String projectID;
    private static String taskSummary;
    private static String taskDescription;
    private static ProjectDTO project;

    @BeforeAll
    static void beforeAll() throws IOException {
        Properties prop = UtilsForTests.loadProperties();

        baseUrl = prop.getProperty("baseUrl");
        authToken = prop.getProperty("authToken");
        projectID = prop.getProperty("projectID");
        taskSummary = prop.getProperty("taskSummary");
        taskDescription = prop.getProperty("taskDescription");
        project = new ProjectDTO(projectID);

    }

    @DisplayName("Delete task test")
    @Test
    void deleteTaskTest() throws IOException {

        TasksCreateDTO task = new TasksCreateDTO(taskSummary, project, taskDescription);

        String taskID = given()
                .auth().oauth2(authToken)
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(task)
                .when()
                .post("/issues")
                .then()
                .extract().path("id");


        given()
                .baseUri(baseUrl)
                .auth().oauth2(authToken)
                .pathParams("issueID", taskID)
                .when().delete("/issues/{issueID}")
                .then()
                .statusCode(200);
    }
}
