package com.resourses;

public enum ApiResources {
	
	addPlace("/maps/api/place/add/json"),
	getPlace("/maps/api/place/get/json"),
	deletePlace("/maps/api/place/delete/json");
	private String a;
	
	
	ApiResources(String a){
		this.a=a;
	}
	
	public String getResources() {
		
		return a;
	}
	
	
	

}
