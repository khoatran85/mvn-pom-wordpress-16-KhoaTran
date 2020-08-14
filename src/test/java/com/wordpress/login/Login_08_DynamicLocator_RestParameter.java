package com.wordpress.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browserFactory.BrowserDriverFactory;
import browserFactory.DriverManager;
import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.Admin.DashboardPageObject;
import pageObjects.Admin.LoginPageObject;
import pageObjects.Admin.MediaPageObject;
import pageObjects.Admin.PagesPageObject;
import pageObjects.Admin.PostPageObject;

public class Login_08_DynamicLocator_RestParameter extends AbstractTest{
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
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
		loginPage.inputToEmailTextbox(GlobalConstants.USER_NAME);
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);
		dashboardPage = loginPage.clickToContinueOrLoginButton();
	}

	@Test
	//sử dụng đối với page có ít menu (10-20 menu)
	public void TC01_Less_Page() {
		// Dashboard -> post
		postPage = (PostPageObject) dashboardPage.openPageByPageName(driver, "Posts");
		//Post -> Pages
		pagesPage = (PagesPageObject) postPage.openPageByPageName(driver, "Pages");
		//Pages -> Media
		mediaPage = (MediaPageObject) pagesPage.openPageByPageName(driver, "Media");
		//Media -> Post
		postPage = (PostPageObject) mediaPage.openPageByPageName(driver, "Posts");
		//Post -> Media
		mediaPage = (MediaPageObject) postPage.openPageByPageName(driver, "Media");
	}
	
	@Test
	//sử dụng đối với page có nhiều menu 
	public void TC01_More_Page() {
		mediaPage.openPageByName(driver, "Posts");
		postPage = PageGeneratorManager.getPostPageAdmin(driver);
		
		
		postPage.openPageByName(driver, "Pages");
		pagesPage = PageGeneratorManager.getPagesPageAdmin(driver);
		
		//Pages -> Media
		pagesPage.openPageByName(driver, "Media");
		mediaPage = PageGeneratorManager.getMediaPageAdmin(driver);
		
		//Media -> Post
		mediaPage.openPageByName(driver, "Dashboard");
		dashboardPage = PageGeneratorManager.getDashboardPageAdmin(driver);
	
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
