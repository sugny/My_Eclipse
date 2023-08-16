package com.pojo.section11;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Section10 {

	public static void main(String[] args) {
		
		//
		//https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&service=lso&o2v=2&flowName=GeneralOAuthFlow
		//
		String[] actualInput = {"Selenium Webdriver Java","Cypress","Protractor"};
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWtgzh5lWaKTU4ENIxKrgn8v0lEfU6X2VdKcY971fZqiUspgwWfOJR-nfhWcM9wUUk2r9w&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
	
		String splitUrl = url.split("code=")[1];
		String code = splitUrl.split("&scope")[0];
		System.out.println(code);
		
		String response = given().queryParams("code", code).urlEncodingEnabled(false)
		.queryParams("grant_type", "authorization_code")
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret" , "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String accessToken = js.get("access_token");
		System.out.println(accessToken);
		
		/*
		 * String courses = given().queryParams("access_token",accessToken)
		 * .when().log().all().get("https://rahulshettyacademy.com/getCourse.php").
		 * asString();
		 * 
		 * System.out.println(courses);
		 */
		
		GetCourses gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		
		System.out.println(gc.getInstructor());
		System.out.println(gc.getExpertise());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		List<WebAutomation> webAutomation = gc.getCourses().getWebAutomation();
		int size = webAutomation.size();
		for(int i =0;i<size;i++) {
			
			if(webAutomation.get(i).getCourseTitle().equalsIgnoreCase("Cypress")) {
				System.out.println(webAutomation.get(i).getPrice());
			break;
			}
		}
		
		List<String> asList = Arrays.asList(actualInput);
		ArrayList<String> responseList = new ArrayList<String>();
		
		for(int i =0;i<size;i++) {
			
			responseList.add(webAutomation.get(i).getCourseTitle());
			System.out.println(responseList);
		}
		
		Assert.assertTrue(responseList.equals(asList));
	}

}
