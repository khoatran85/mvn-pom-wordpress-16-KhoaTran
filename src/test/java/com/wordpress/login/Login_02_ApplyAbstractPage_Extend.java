package com.wordpress.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.AbstractPage;

public class Login_02_ApplyAbstractPage_Extend extends AbstractPage {
	WebDriver driver;
	WebDriverWait explicitWait;
	String userName = "//input[@id='usernameOrEmail']";
	String submitButton = "//button[@type='submit']";
	String password = "//input[@id='password']";
	String errorLoginMessage = "//div[@role='alert']";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",
				"D:\\Project\\java_selenium_api_testng_16\\Browser driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
	}

	@BeforeMethod
	public void BeforeMethod() {
		openUrl(driver, "https://automationfc.wordpress.com/wp-admin/");
	}

	@Test
	public void Validate_01_EmptyEmail() {
		sendkeyToElement(driver, userName, "");
		clickToElement(driver, submitButton);
		waitForElementVisible(driver, errorLoginMessage);
		Assert.assertEquals(getElementText(driver, errorLoginMessage), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {
		sendkeyToElement(driver, userName, "automation^%#");
		clickToElement(driver, submitButton);
		waitForElementVisible(driver, errorLoginMessage);
		Assert.assertEquals(getElementText(driver, errorLoginMessage),
				"User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_03_EmailNotExist() {
		sendkeyToElement(driver, userName, "automation99999@gmail.com");
		clickToElement(driver, submitButton);
		waitForElementVisible(driver, errorLoginMessage);
		Assert.assertEquals(getElementText(driver, errorLoginMessage),
				"User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {
		sendkeyToElement(driver, userName, "automationeditor");
		clickToElement(driver, submitButton);
		// explicitWaitUntilvisible(passwordTextBox);
		waitForElementVisible(driver, password);
		sendkeyToElement(driver, password, "");
		clickToElement(driver, submitButton);
		waitForElementVisible(driver, errorLoginMessage);
		Assert.assertEquals(getElementText(driver, errorLoginMessage), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThan6Chars() {
		sendkeyToElement(driver, userName, "automationeditor");
		clickToElement(driver, submitButton);
		waitForElementVisible(driver, password);
		sendkeyToElement(driver, password, "123");
		clickToElement(driver, submitButton);
		waitForElementVisible(driver, errorLoginMessage);
		Assert.assertEquals(getElementText(driver, errorLoginMessage),
				"Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {
		sendkeyToElement(driver, userName, "automationeditor");
		clickToElement(driver, submitButton);
		waitForElementVisible(driver, password);
		sendkeyToElement(driver, password, "automationfc");
		clickToElement(driver, submitButton);
		waitForElementVisible(driver, "//h1");
		Assert.assertTrue(isElementDisplay(driver, "//h1[text()='Dashboard']"));
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
