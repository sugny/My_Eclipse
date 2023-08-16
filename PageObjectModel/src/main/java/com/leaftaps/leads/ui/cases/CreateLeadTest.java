package com.leaftaps.leads.ui.cases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.leaftaps.ui.base.ProjectSpecificMethods;
import com.leaftaps.ui.pages.LoginPage;

public class CreateLeadTest extends ProjectSpecificMethods {
	@BeforeTest
	public void setData() {
		excelFileName = "tc001";
	}
	
	@Test(dataProvider = "testData")
	public void runCreateLead(String username, String password,String fname,String lname,String cname) {
		LoginPage pg = new LoginPage(driver);
		pg.typeUsername(username)
		.typePassword(password)
		.clickLoginButton_Positive()
		.clickCRM_SFA()
		.clickLeads()
		.clickCreateLead()
		.typeCompanyname(cname)
		.typeFirstname(fname)
		.typeLastname(lname)
		.clickCreateLeadButton();
		
	}
	

}
