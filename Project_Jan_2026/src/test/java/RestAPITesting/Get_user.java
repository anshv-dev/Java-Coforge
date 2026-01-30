package RestAPITesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Get_user {
  @Test
  public void getuser() {
	  RestAssured.baseURI="https://api.restful-api.dev/objects/7";
	  RestAssured.given()
	  .when()
	  .get("/objects")
	  .then()
	  .statusCode(404)
	  .log().all();
	}
}
