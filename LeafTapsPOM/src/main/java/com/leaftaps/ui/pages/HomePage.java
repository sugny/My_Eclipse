package com.leaftaps.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.leaftaps.ui.base.ProjectSpecificMethods;

public class HomePage extends ProjectSpecificMethods {

	public HomePage(RemoteWebDriver receivedDriver) {

		driver = receivedDriver;
	}

	public CreateLeadPage leads() {

		driver.findElement(By.xpath("//a[text()='Leads']")).click();
		return new CreateLeadPage(driver);
	}

}
