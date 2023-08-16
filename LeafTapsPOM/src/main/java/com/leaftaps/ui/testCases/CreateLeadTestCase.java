package com.leaftaps.ui.testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.leaftaps.ui.base.ProjectSpecificMethods;
import com.leaftaps.ui.pages.LoginPage;

public class CreateLeadTestCase extends ProjectSpecificMethods {
	
	@BeforeTest
	public void setData() {
		
		excelFileName = "tc001";
		
	}

	@Test(dataProvider="testData")
	public void createLeadTest(String username,String password,String FirstName,String LastName,String CompanyName) {

		LoginPage lg = new LoginPage(driver);
		lg.enterUserName(username).enterPassword(password).clickLoginButton().clickCRMSFA().leads().clickCreateLead()
		.enterCname(CompanyName).enterFname(FirstName)
				.enterLname(LastName).clickSubmitButton();

	}

}
