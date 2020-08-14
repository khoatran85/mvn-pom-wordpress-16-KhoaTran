package com.wordpress.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browserFactory.DriverManager;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.Admin.DashboardPageObject;
import pageObjects.Admin.LoginPageObject;
import pageObjects.Admin.MediaPageObject;
import pageObjects.Admin.PagesPageObject;
import pageObjects.Admin.PostPageObject;

public class Login_07_Driver_Life_Cycle extends AbstractTest{
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	PostPageObject postPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;
	String loginPageUrl;
	WebDriver driver;
	DriverManager driverManager;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.wordpress.com/wp-admin/");
		loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
	}


	@Test
	public void TC01_login() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox("automationfc");
		// dashboardPage = new DashboardPageObject(driver);
		//dashboardPage = PageGeneratorManager.getDashboardPage(driver);
		dashboardPage = loginPage.clickToContinueOrLoginButton();
		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}
	
	@Test
	public void TC02_navigaToPage() {
		// Dashboard -> post
		postPage = dashboardPage.clickToPostMenu(driver);
		//Post -> Pages
		pagesPage = postPage.clickToPagesMenu(driver);
		//Pages -> Media
		mediaPage = pagesPage.clickToMediaMenu(driver);
		//Media -> Post
		postPage = mediaPage.clickToPostMenu(driver);
		//Post -> Media
		mediaPage = postPage.clickToMediaMenu(driver);
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
