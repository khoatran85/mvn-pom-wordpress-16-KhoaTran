//package com.wordpress.login;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import commons.AbstractPage;
//import pageFactory.DashboardPageObject;
//import pageFactory.LoginPageObject;
//
//
//public class Login_03_PageObjectPattern extends AbstractPage {
//	LoginPageObject loginPage;
//	DashboardPageObject dashboardPage;
//	String loginPageUrl;
//	 WebDriver driver;
//
//	@BeforeClass
//	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver",
//				"D:\\Project\\java_selenium_api_testng_16\\Browser driver\\geckodriver.exe");
//		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		openUrl(driver, "https://automationfc.wordpress.com/wp-admin/");
//		loginPage = new LoginPageObject(driver);
//		
//		loginPageUrl = loginPage.getLoginPageUrl();
//	}
//	@BeforeMethod
//	public void beforeMethod() {
//		loginPage.openLoginPage(loginPageUrl);
//	}
//	
//	@Test
//	public void Validate_01_EmptyEmail() {
//		loginPage.inputToEmailTextbox("");
//		loginPage.clickToContinueOrLoginButton();
//		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(), "Please enter a username or email address.");
//	}
//
//	@Test
//	public void Validate_02_InvalidEmail() {
//		loginPage.inputToEmailTextbox("automation^%#");
//		loginPage.clickToContinueOrLoginButton();
//		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(),
//				"User does not exist. Would you like to create a new account?");
//	}
//
//	@Test
//	public void Validate_03_EmailNotExist() {
//		loginPage.inputToEmailTextbox("automation99999@gmail.com");
//		loginPage.clickToContinueOrLoginButton();
//		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(),
//				"User does not exist. Would you like to create a new account?");
//	}
//
//	@Test
//	public void Validate_04_EmptyPassword() {
//		loginPage.inputToEmailTextbox("automationeditor");
//		loginPage.clickToContinueOrLoginButton();
//		loginPage.inputToPasswordTextbox("");
//		loginPage.clickToContinueOrLoginButton();
//		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(), "Don't forget to enter your password.");
//	}
//
//	@Test
//	public void Validate_05_PasswordLessThan6Chars() {
//		loginPage.inputToEmailTextbox("automationeditor");
//		loginPage.clickToContinueOrLoginButton();
//		loginPage.inputToPasswordTextbox("123");
//		loginPage.clickToContinueOrLoginButton();
//		Assert.assertEquals(loginPage.getEmailOrPasswordErrorMessage(),
//				"Oops, that's not the right password. Please try again!");
//	}
//
//	@Test
//	public void Validate_06_ValidPassword() {
//		loginPage.inputToEmailTextbox("automationeditor");
//		loginPage.clickToContinueOrLoginButton();
//		loginPage.inputToPasswordTextbox("automationfc");
//		loginPage.clickToContinueOrLoginButton();
//		dashboardPage = new DashboardPageObject(driver);
//		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
//	}
//
//	@AfterClass
//	public void afterClass() {
//		driver.close();
//	}
//
//}
