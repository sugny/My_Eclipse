package com.leaftaps.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.leaftaps.ui.base.ProjectSpecificMethods;

public class WelcomePage extends ProjectSpecificMethods {

	public WelcomePage(RemoteWebDriver receivedDriver) {
		driver = receivedDriver;
	}

	public HomePage clickCRMSFA() {
		driver.findElement(By.linkText("CRM/SFA")).click();
		return new HomePage(driver);
	}
	
	public LoginPage logout() {
		driver.findElement(By.id("logout")).click();
		return new LoginPage(driver);
	}

}
