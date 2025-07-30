package specification;

import DTO.TasksCreateDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;

public class TaskCreationSpecification {
    public static String correctTaskCreationWithIDExtract(String baseUrl, String authToken, TasksCreateDTO taskCreateDTO) {
        return RestAssured.given()
                .auth().oauth2(authToken)
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(taskCreateDTO)
                .when()
                .post("/issues")
                .then()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("$type", Matchers.containsString("Issue"))
                .extract().path("id");
    }

    public static void inCorrectTaskCreationWithIDExtract(String baseUrl, String authToken, TasksCreateDTO taskCreateDTO) {
        RestAssured.given()
                .auth().oauth2(authToken)
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(taskCreateDTO)
                .when()
                .post("/issues")
                .then()
                .statusCode(400)
                .body("error_children.error_developer_message", Matchers.contains("Value for summary is required"));
    }

}
