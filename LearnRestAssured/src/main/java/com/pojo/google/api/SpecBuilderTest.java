package com.pojo.google.api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

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

		RestAssured.useRelaxedHTTPSValidation();

		RequestSpecification res = given().spec(SpecBuildersMethods.request()).body(gm);

		Response response = res.when().post("/maps/api/place/add/json").then().spec(SpecBuildersMethods.response())
				.extract().response();
		String responseString = response.asString();

		System.out.println(responseString);

	}

}
