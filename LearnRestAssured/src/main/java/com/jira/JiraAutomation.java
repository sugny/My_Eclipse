package com.jira;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.asserts.Assertion;

public class JiraAutomation {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.useRelaxedHTTPSValidation();
		
		// login request
		SessionFilter session = new SessionFilter();
		String response = given().header("Content-Type","application/json").body(JiraPayload.loginCredentials()).filter(session)
		.when().post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		/*
		 * JsonPath js = ReusableCode.rawToJson(response); String sessionValue =
		 * js.get("session.value"); System.out.println(sessionValue);
		 */
		
		//Add comment
		String addCommentResponse = given().pathParam("key", "10002").header("Content-Type","application/json").body(JiraPayload.addComment()).filter(session)
		.when().post("/rest/api/2/issue/{key}/comment")
		.then().assertThat().statusCode(201).extract().response().asString();
		JsonPath js = ReusableCode.rawToJson(addCommentResponse);
		String commentID = js.get("id");
		String ActualComment = js.get("body");
		System.out.println(commentID);
		
		// Add attachment 
		given().pathParam("key", "10002").header("Content-Type","multipart/form-data").header("X-Atlassian-Token","no-check")
		.multiPart("file",new File("jira.txt")).filter(session)
		.when().post("/rest/api/2/issue/{key}/attachments").then().assertThat().statusCode(200);
		
		// get Issue
		
		String getIssueResponse = given().filter(session).pathParam("key", "10002").queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{key}").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReusableCode.rawToJson(getIssueResponse);
		int commentCount = js1.getInt("fields.comment.comments.size()");
		System.out.println(commentCount);
		String commentIdIssue = "";
		for(int i=0;i<commentCount;i++) {
			
			commentIdIssue = js1.get("fields.comment.comments["+i+"].id");
			System.out.println(commentIdIssue);
			if (commentID.equalsIgnoreCase(commentIdIssue)) {
				
				String expectedComment = js1.get("fields.comment.comments["+i+"].body");
				System.out.println(expectedComment);
				System.out.println(ActualComment);
				Assert.assertEquals(expectedComment, ActualComment);
			}
		}
		
	}

}
