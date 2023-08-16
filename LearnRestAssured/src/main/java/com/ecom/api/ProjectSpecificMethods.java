package com.ecom.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ProjectSpecificMethods {

	public static RequestSpecification loginReqSpec() {

		RequestSpecification loginReqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		return loginReqSpec;

	}

	public static RequestSpecification addProductReq() {

		RequestSpecification addProductReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", EcommerceApi.token).build();
		return addProductReq;

	}

	public static RequestSpecification createOrder() {

		RequestSpecification createOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", EcommerceApi.token).setContentType(ContentType.JSON).build();
		return createOrder;
	}
	
	public static RequestSpecification deleteProduct() {
		
		return ProjectSpecificMethods.createOrder();
	}

	public static JsonPath rawToJson(String asString) {

		JsonPath js = new JsonPath(asString);
		return js;

	}

}
