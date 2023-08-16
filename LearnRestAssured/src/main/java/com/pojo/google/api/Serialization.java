package com.pojo.google.api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;

public class Serialization {

	@Test
	public void postGoogleAPI() {
		
		GoogleMaps gm = new GoogleMaps();
		gm.setAccuracy(50);
		gm.setName("Frontline house");
		gm.setAddress("29, side layout, cohen 09");
		gm.setLanguage("French-IN");
		gm.setPhone_number("+91) 983 893 3937");
		gm.setWebsite("http://google.com");
		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");
		gm.setTypes(typesList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		gm.setLocation(l);
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().queryParam("key", "qaclick123").body(gm)
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);

		
		

	}

}
