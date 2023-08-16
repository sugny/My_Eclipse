package com.restAPI;

import java.util.List;

import com.files.Payload;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class Section6 {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Payload.coursePrice());
		
		// Print No of courses returned by API
		int coursesCount = js.getInt("courses.size()");
		System.out.println(coursesCount);
		
		//2.Print Purchase Amount
		int ActualPurchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(ActualPurchaseAmt);
		
		//Print Title of the first course
		String title = js.get("courses[0].title");
		System.out.println(title);
		
		//Print All course titles and their respective Prices
		List<String> list = js.getList("courses.title");
		System.out.println(list);
		
		//Print All course titles and their respective Prices
		
		for(int i=0;i<coursesCount;i++) {
			
			String courseAndPrices = js.get("courses["+ i +"].title")+" "+ js.get("courses["+ i+ "].price");
			System.out.println(courseAndPrices);
		}
		
		//Print no of copies sold by RPA Course
		
		for(int i=0;i<coursesCount;i++) { 
			
			String courses = js.get("courses["+ i +"].title");
			if(courses.equalsIgnoreCase("Selenium Python")) {
				
				System.out.println("Selenium Python copies sold : "+js.getInt("courses["+ i +"].copies"));
				break;
			}
		}
		
		int purchaseAmt=0;
		for(int i =0;i<coursesCount;i++) {
			int price = js.getInt("courses["+ i +"].price");
			int copies= js.getInt("courses["+ i +"].copies");
			purchaseAmt = purchaseAmt+(price*copies);
		}
		System.out.println("Calculated purchase amt : "+purchaseAmt);
		Assert.assertEquals(ActualPurchaseAmt, purchaseAmt);
		if(ActualPurchaseAmt==purchaseAmt) {
			
			System.out.println("Sum of all Course prices matches with Purchase Amount");
		}
		else
			System.out.println("Sum of all Course prices is NOT matches with Purchase Amount");
		
	}

}
