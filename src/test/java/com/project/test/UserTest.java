package com.project.test;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.BeforeClass;
import org.junit.Test;

import com.project.util.JwtUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public class UserTest {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 */
	@Test
	public void userToggleTest() {
		JsonObject jsonObj = Json.createObjectBuilder()
				.add("username", "clippiatt0")
				.build();

		RestAssured.given()
				.contentType(ContentType.JSON)
				.body(jsonObj.toString())
			.when()
				.post("/testprj/api/v1/users/toggle")
			.then()
				.assertThat()
				.statusCode(200);
	}


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 */
	@Test
	public void userToggleFailTest() {
		JsonObject jsonObj = Json.createObjectBuilder()
				.add("username", "fakeuser")
				.build();

		RestAssured.given()
				.contentType(ContentType.JSON)
				.body(jsonObj.toString())
			.when()
				.post("/testprj/api/v1/users/toggle")
			.then()
				.assertThat()
				.statusCode(404);
	}


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @throws Exception
	 */
	@Test
	public void authTokenVerifyTest() throws Exception {
		JsonObject jsonObj = Json.createObjectBuilder()
				.add("username", "clippiatt0")
				.build();

		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(jsonObj.toString())
			.when()
				.post("/testprj/api/v1/users/auth")
			.then()
				.extract().response();

		String string = response.getBody().jsonPath().getString("token");
		JwtUtils.parseToken(string);
	}

	
	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @throws Exception
	 */
	@Test
	public void noAuthTest() throws Exception {
		JsonObject jsonObj = Json.createObjectBuilder()
				.add("lat", 41.902606)
				.add("lng", 12.692104)
				.build();
		
		RestAssured.given()
				.contentType(ContentType.JSON)
				.body(jsonObj.toString())
			.when()
				.post("/testprj/api/v1/users/location")
			.then()
				.assertThat()
				.statusCode(401);
	}
}