package com.restAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;




public class LearnAPI {

	public static void main(String[] args) throws IOException {
		
		//section 7.38
		//post response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\suganya\\addPlace.json"))))
						.when().post("/maps/api/place/add/json").then().assertThat()
				.statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)").body("scope", equalTo("APP")).extract().response().asString();
		//
		//System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id");
		System.out.println(placeID);
		
		//put response    
		
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		
				}

}
