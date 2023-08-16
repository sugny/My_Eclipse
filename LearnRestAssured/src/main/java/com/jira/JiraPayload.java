package com.jira;

public class JiraPayload {
	
	
	public static String loginCredentials() {
		
		return "{ \"username\": \"suganyan796\", \"password\": \"Sweetsandsnacks@22\" }";
		
	}
	
	
	public static String addComment() {
		
		return "{\r\n"
				+ "    \"body\": \"Check for comment \",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
	}
}
