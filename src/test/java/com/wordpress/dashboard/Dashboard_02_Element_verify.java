package com.wordpress.dashboard;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browserFactory.BrowserDriverFactory;
import browserFactory.DriverManager;
import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.Admin.DashboardPageObject;
import pageObjects.Admin.LoginPageObject;


public class Dashboard_02_Element_verify extends AbstractTest{
	WebDriver driver;
	DriverManager driverManager;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	
	@Parameters("browser")
	@BeforeTest
	public void BeforeTest(String browserName) {
		
		log.info("Pre-Condition - Open browser");
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		
		log.info("Pre-Condition - Step 01: Open login page");
		loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
		
		log.info("Pre-Condition - Step 01: input email textbox");
		loginPage.inputToEmailTextbox(GlobalConstants.USER_NAME);
		
		log.info("Pre-Condition - Step 02: click to continue button");
		loginPage.clickToContinueOrLoginButton();
		
		log.info("Pre-Condition - Step 03: input password textbox");
		loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);
		
		log.info("Pre-Condition - Step 04: click to login button");
		dashboardPage =  loginPage.clickToContinueOrLoginButton();
	}
	
	@Test
	public void Dashboard_check_Element_visible() {
		log.info("Dashboard_check_Element_visible - STEP 01: click to screen option");
		dashboardPage.clickToScreenOptions();

		log.info("Dashboard_check_Element_visible - STEP 02: verify Activity checkbox display");
		verifyTrue(dashboardPage.isActivityCheckboxDisPlay());
		
		log.info("Dashboard_check_Element_visible - STEP 03: click to screen option");
		dashboardPage.clickToScreenOptions();
		
		log.info("Dashboard_check_Element_visible - STEP 04: verify Activity checkbox not display");
		verifyTrue(dashboardPage.isActivityCheckboxDisPlay());

		log.info("Dashboard_check_Element_visible - STEP 04: verify all posts submenu not display");
		verifyFalse(dashboardPage.isAllPostsSubmenuUnDisplayed());
		
	}
	@Test
	public void Element_Undisplayed_02_Not_In_Dom() {
		log.info("Element_Undisplayed_02_Not_In_Dom - STEP 04: verify all posts submenu not display");
		verifyTrue(dashboardPage.isPlanMenuUnDisplayed());	
	}
	
	@AfterTest
	public void AfterTest() {
		log.info("Post Condition - Close browser");
		driver.close();
	}
}
