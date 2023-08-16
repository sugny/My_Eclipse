package com.leaftaps.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.leaftaps.ui.base.ProjectSpecificMethods;

public class CreateLeadPage extends ProjectSpecificMethods {

	public CreateLeadPage(RemoteWebDriver receivedDriver) {

		driver = receivedDriver;

	}

	public CreateLeadPage clickCreateLead() {

		driver.findElement(By.xpath("//a[text()='Create Lead']")).click();
		return this;
	}

	public CreateLeadPage enterCname(String CompanyName) {

		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(CompanyName);
		return this;

	}

	public CreateLeadPage enterFname(String FirstName) {

		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(FirstName);
		return this;

	}

	public CreateLeadPage enterLname(String LastName) {

		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(LastName);
		return this;

	}

	public ViewLeadPage clickSubmitButton() {

		driver.findElement(By.name("submitButton")).click();
		return new ViewLeadPage(driver);
	}
}
