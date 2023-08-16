package com.restAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;

import com.files.Payload;
import com.files.ReusableCode;

public class Test {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		String Postresponse = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.addPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).header("Server","Apache/2.4.41 (Ubuntu)")
		.body("scope", equalTo("APP")).extract().response().asString();
		System.out.println(Postresponse);
		JsonPath js = ReusableCode.rawToJson(Postresponse);
		String placeID = js.get("place_id");
		System.out.println(placeID);				
	
		//update (put) response
		
		String actualAddress = "70 winter walk, USA";
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\r\n"
				+ "\"place_id\":\""+ placeID +"\",\r\n"
				+ "\"address\":\""+ actualAddress +"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"))
		.header("Content-Type", equalTo("application/json;charset=UTF-8"));
		
		//get response
		
		String getResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get("/maps/api/place/get/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReusableCode.rawToJson(getResponse);
		String newAddress = js1.get("address");
		System.out.println(newAddress);
		Assert.assertEquals(newAddress, actualAddress);
	
	}
}