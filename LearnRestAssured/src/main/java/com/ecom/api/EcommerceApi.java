package com.ecom.api;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ecom.api.pojo.CreateOrder;
import com.ecom.api.pojo.LoginRequest;
import com.ecom.api.pojo.LoginResponse;
import com.ecom.api.pojo.Order;
import com.ecom.api.pojo.OrderResponse;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class EcommerceApi {

	public static String token;

	public static void main(String[] args) {

		// section 14
		// Login POST Request

		LoginRequest lreq = new LoginRequest();
		lreq.setUserEmail("suganyan796@gmail.com");
		lreq.setUserPassword("Sweetsandsnacks@22");

		RequestSpecification loginReqSpec = given().spec(ProjectSpecificMethods.loginReqSpec()).body(lreq);

		LoginResponse loginResponse = loginReqSpec.when().post("/api/ecom/auth/login").then().extract().response()
				.as(LoginResponse.class);

		token = loginResponse.getToken();
		String userId = loginResponse.getUserId();
		System.out.println(token);
		System.out.println(userId);

		// Add New Product POST Request

		RequestSpecification addProductReq = given().spec(ProjectSpecificMethods.addProductReq())
				.param("productName", "qwerty").param("productAddedBy", userId).param("productCategory", "fashion")
				.param("productSubCategory", "shirts").param("productPrice", "11500")
				.param("productDescription", "Addias Originals").param("productFor", "men")
				.multiPart("productImage", new File("C:\\suganya\\image.png"));

		String response = addProductReq.when().post("/api/ecom/product/add-product").then().log().all().extract()
				.response().asString();
		JsonPath js = ProjectSpecificMethods.rawToJson(response);
		String productId = js.get("productId");
		// System.out.println(productId);

		// Create order

		CreateOrder createOrder = new CreateOrder();
		createOrder.setCountry("India");
		createOrder.setProductOrderedId(productId);
		List<CreateOrder> orderList = new ArrayList<CreateOrder>();
		orderList.add(createOrder);
		Order order = new Order();
		order.setOrders(orderList);

		RequestSpecification createRequest = given().spec(ProjectSpecificMethods.createOrder()).body(order);

		

		OrderResponse or = createRequest.expect().defaultParser(Parser.JSON).when().post("/api/ecom/order/create-order")
				.as(OrderResponse.class);

		List<String> ordersId = or.getOrders();
		System.out.println(ordersId);
		

		// Delete product

		RequestSpecification deleteResponse = given().spec(ProjectSpecificMethods.deleteProduct())
				.pathParam("productId", productId);

		String deleteString = deleteResponse.when().delete("/api/ecom/product/delete-product/{productId}").then()
				.extract().response().asString();

		JsonPath js2 = ProjectSpecificMethods.rawToJson(deleteString);
		String message1 = js2.get("message");

		Assert.assertEquals("Product Deleted Successfully", message1);
		
		

		// Delete Order
		
		RequestSpecification deleteOrderReq = given().spec(ProjectSpecificMethods.addProductReq()).pathParam("ordersId", ordersId.get(0));
		System.out.println(ordersId);
		
		String deleteRes = deleteOrderReq.when().delete("/api/ecom/order/delete-order/{ordersId}")
				.then().log().all().extract()
				.response().asString();
		
		System.out.println(deleteRes);

	}

}
