package specification;

import DTO.UpdateTaskDTO;

import static io.restassured.RestAssured.given;

public class TaskUpdateSpecification {
    public static void updateTask(String baseUrl, String authToken ,UpdateTaskDTO updateTaskDTO, String issueID) {
        given()
                .auth().oauth2(authToken)
                .baseUri(baseUrl)
                .pathParam("issueID", issueID)
                .queryParam("summary", updateTaskDTO)
                .contentType("application/json")
                .body(updateTaskDTO)
                .when()
                .post("/issues/{issueID}/")
                .then()
                .statusCode(200);

    }
}
