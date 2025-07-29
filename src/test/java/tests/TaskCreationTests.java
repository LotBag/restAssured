package tests;

import DTO.ProjectDTO;
import DTO.TasksCreateDTO;
import Utils.UtilsForTests;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.hamcrest.Matchers.*;
import Utils.UtilsForTests.*;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TaskCreationTests {

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

    @DisplayName("Creation with all arg constructor")
    @Test
    void createTaskWithAllArgConstructorTest() {

        TasksCreateDTO task = new TasksCreateDTO(taskSummary, project, taskDescription);

        String taskID = given()
                .auth().oauth2(authToken)
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(task)
                .when()
                .post("/issues")
                .then()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("$type", Matchers.containsString("Issue"))
                .extract().path("id");


        given()
                .baseUri(baseUrl)
                .auth().oauth2(authToken)
                .pathParams("issueID", taskID)
                .when().delete("/issues/{issueID}");
    }

    @DisplayName("Creation with required only constructor")
    @Test
    void createTaskWithRequiredOnlyConstructorTest() {
        TasksCreateDTO task = new TasksCreateDTO(taskSummary, project);

        String taskID = given()
                .auth().oauth2(authToken)
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(task)
                .when()
                .post("/issues")
                .then()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("$type", Matchers.containsString("Issue"))
                .extract().path("id");

        given()
                .baseUri(baseUrl)
                .auth().oauth2(authToken)
                .pathParams("issueID", taskID)
                .when().delete("/issues/{issueID}");
    }

    @DisplayName("Create task with empty summary")
    @Test
    void createTaskWithEmptyFieldsTest() {
        TasksCreateDTO task = new TasksCreateDTO("", project);

        given()
                .auth().oauth2(authToken)
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(task)
                .when()
                .post("/issues")
                .then()
                .statusCode(400)
                .body("error_children.error_developer_message", Matchers.contains("Value for summary is required"));

    }



}
