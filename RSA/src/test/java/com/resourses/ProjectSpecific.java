package com.resourses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ProjectSpecific {
	
	public static RequestSpecification reqSpec;
	public RequestSpecification requestSpec() throws IOException {
		if(reqSpec==null) {
		PrintStream ps = new PrintStream(new FileOutputStream("logging.txt"));
		reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
										.addFilter(RequestLoggingFilter.logRequestTo(ps))
										.addFilter(ResponseLoggingFilter.logResponseTo(ps))
										.setContentType(ContentType.JSON).build();
		return reqSpec;
		}
		else
			return reqSpec;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\suganya\\myJavaPrograms\\RSA\\src\\test\\java\\com\\resourses\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String rawToJson(Response response,String key) {
		
		String getResponse = response.asString();
		System.out.println("======>"+getResponse);
		JsonPath js = new JsonPath(getResponse);
		String keyValue = js.get(key);
		return keyValue;
		
	}
	
	
	

}
