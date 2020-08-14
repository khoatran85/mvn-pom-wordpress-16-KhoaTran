package com.wordpress.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browserFactory.BrowserDriverFactory;
import browserFactory.DriverManager;
import commons.PageGeneratorManager;
import pageObjects.Admin.DashboardPageObject;
import pageObjects.Admin.LoginPageObject;

public class Login_06_Browser_Factory_Pattern {
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	String loginPageUrl;
	WebDriver driver;
	DriverManager driverManager;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.wordpress.com/wp-admin/");
		loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@BeforeMethod
	public void beforeMethod() {
		loginPage.openLoginPage(loginPageUrl);
	}

	@Test
	public void Validate_01_EmptyEmail() {
		loginPage.inputToEmailTextbox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {
		loginPage.inputToEmailTextbox("automation^%#");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(),
				"User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_03_EmailNotExist() {
		loginPage.inputToEmailTextbox("automation99999@gmail.com");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(),
				"User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThan6Chars() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(),
				"Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox("automationfc");
		loginPage.clickToContinueOrLoginButton();
		// dashboardPage = new DashboardPageObject(driver);
		dashboardPage = PageGeneratorManager.getDashboardPageAdmin(driver);
		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
