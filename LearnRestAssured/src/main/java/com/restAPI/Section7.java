package com.restAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.AddBookPayload;
import com.files.ReusableCode;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Section7 {

	@Test(dataProvider = "getData")
	public void addBook(String isbn, String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().header("Content-Type", "application/json")
				.body(AddBookPayload.addBookJson(isbn, aisle)).when().post("Library/Addbook.php").then().extract().response().asString();
		System.out.println(response);
		JsonPath js = ReusableCode.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);

	}

	// Delete Book

	
	  @Test(dataProvider = "getData")
	  public void deleteBook(String isbn, String aisle) {
	  RestAssured.baseURI = "https://rahulshettyacademy.com";
	  RestAssured.useRelaxedHTTPSValidation(); 
	  given().body(AddBookPayload.addBookJson(isbn, aisle)).
	  when().post("Library/DeleteBook.php").then().assertThat().statusCode(200); }
	 

	@DataProvider(name = "getData")
	public Object[][] setData() {

		return new Object[][] { { "abd", "164" }, { "qwe", "163" }, { "zaq", "162" }, };
	}
}
