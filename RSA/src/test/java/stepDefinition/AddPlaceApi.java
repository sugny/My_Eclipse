package stepDefinition;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.runner.RunWith;

import com.resourses.ApiResources;
import com.resourses.ProjectSpecific;
import com.resourses.TestDataBuild;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

@RunWith(Cucumber.class)
public class AddPlaceApi extends ProjectSpecific {

	public static RequestSpecification req;
	public ResponseSpecification responseSpec;
	public Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws Throwable {

		req = given().spec(requestSpec()).body(data.placePayload(name, language, address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) throws IOException {

		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		ApiResources api = ApiResources.valueOf(resource);
		String resources = api.getResources();
		if (httpMethod.equalsIgnoreCase("post"))
			response = req.when().post(resources);
		else if (httpMethod.equalsIgnoreCase("get"))
			response = req.when().get(resources);
		else {
			response = delRequest.when().post(resources);
		}

		// String globalValue = getGlobalValue("post");
	}

	@Then("the API call got success with a status code {int}")
	public void the_api_call_got_success_with_a_status_code_200(Integer code) {

		assertEquals(response.getStatusCode(), 200);

	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedKey) {

			assertEquals(rawToJson(response, key), expectedKey);
	}

	@And("verify the place_id created maps to {string} using {string}")
	public void verify_the_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		place_id = rawToJson(response, "place_id");
		req = given().spec(requestSpec()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "get");
		String actualName = rawToJson(response, "name");
		assertEquals(actualName, expectedName);
	}

	// Delete Place Api should always with addPlace to get a place_id 

	RequestSpecification delRequest;

	@Given("deletePlace Payload with place_id")
	public void delete_place_payload_with_place_id() throws IOException {

		delRequest = given().spec(requestSpec()).body(data.deletePayload(place_id));

	}

}
