package com.leaftaps.ui.base;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.leaftaps.ui.utils.ReadExcelFile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProjectSpecificMethods {
	
	public RemoteWebDriver driver;
	public String excelFileName;
	
	@DataProvider(name ="testData")
	public String[][] testData() throws IOException {
		
		return ReadExcelFile.readExcel(excelFileName);
		
	}
	
	@BeforeMethod
	public void bm() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterMethod
	public void am() {
		driver.close();
	}
	
	
}
