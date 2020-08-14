package com.wordpress.dashboard;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browserFactory.BrowserDriverFactory;
import browserFactory.DriverManager;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.Admin.DashboardPageObject;
import pageObjects.Admin.LoginPageObject;

public class Dashboard_01_Element_Undisplayed {
	WebDriver driver;
	DriverManager driverManager;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	
	@Parameters("browser")
	@BeforeTest
	public void BeforeTest(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
		loginPage.inputToEmailTextbox(GlobalConstants.USER_NAME);
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);
		loginPage.clickToContinueOrLoginButton();
		dashboardPage = PageGeneratorManager.getDashboardPageAdmin(driver);
	}
//	@Test
	public void Element_Undisplayed_01_In_Dom() {
		dashboardPage.clickToScreenOptions();
		
		Assert.assertTrue(dashboardPage.isActivityCheckboxDisPlay());
		
		dashboardPage.clickToScreenOptions();
		
		Assert.assertFalse(dashboardPage.isActivityCheckboxDisPlay());

		Assert.assertFalse(dashboardPage.isAllPostsSubmenuUnDisplayed());
		
	}
	@Test
	public void Element_Undisplayed_02_Not_In_Dom() {
		Assert.assertTrue(dashboardPage.isPlanMenuUnDisplayed());
		
	}
	
	@AfterTest
	public void AfterTest() {
		driver.close();
	}
}
