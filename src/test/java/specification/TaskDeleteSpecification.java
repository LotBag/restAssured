package specification;

import static io.restassured.RestAssured.given;

public class TaskDeleteSpecification {
    public static void deleteTask(String baseURL, String authToken,String taskID){
        given()
                .auth().oauth2(authToken)
                .baseUri(baseURL)
                .pathParams("issueID", taskID)
                .when().delete("/issues/{issueID}")
                .then()
                .statusCode(200);;
    }
}
