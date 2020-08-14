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

public class Login_01_RepeatYourself {
	WebDriver driver;
	WebDriverWait explicitWait;
	By userNameTextBox = By.xpath("//input[@id='usernameOrEmail']");
	By submitButton = By.xpath("//button[@type='submit']");
	By passwordTextBox = By.xpath("//input[@id='password']");
	By errorLoginMessage = By.xpath("//div[@role='alert']");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver","D:\\Project\\java_selenium_api_testng_16\\Browser driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
	}

	@BeforeMethod
	public void BeforeMethod() {
		driver.get("https://automationfc.wordpress.com/wp-admin/");
	}

	@Test
	public void Validate_01_EmptyEmail() {
		driver.findElement(userNameTextBox).sendKeys("");
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(errorLoginMessage).getText(), "Please enter a username or email address.");
	}
	@Test
	public void Validate_02_InvalidEmail() {
		driver.findElement(userNameTextBox).sendKeys("automation^%#");
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(errorLoginMessage).getText(), "User does not exist. Would you like to create a new account?");
	}
	@Test
	public void Validate_03_EmailNotExist() {
		driver.findElement(userNameTextBox).sendKeys("automation99999@gmail.com");
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(errorLoginMessage).getText(), "User does not exist. Would you like to create a new account?");
	}
	@Test
	public void Validate_04_EmptyPassword() {
		driver.findElement(userNameTextBox).sendKeys("automationeditor");
		driver.findElement(submitButton).click();
		explicitWaitUntilvisible(passwordTextBox);
		driver.findElement(passwordTextBox).sendKeys("");
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(errorLoginMessage).getText(), "Don't forget to enter your password.");
	}
	@Test
	public void Validate_05_PasswordLessThan6Chars() {
		driver.findElement(userNameTextBox).sendKeys("automationeditor");
		driver.findElement(submitButton).click();
		explicitWaitUntilvisible(passwordTextBox);
		driver.findElement(passwordTextBox).sendKeys("123");
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(errorLoginMessage).getText(), "Oops, that's not the right password. Please try again!");
	}
	@Test
	public void Validate_06_ValidPassword() {
		driver.findElement(userNameTextBox).sendKeys("automationeditor");
		driver.findElement(submitButton).click();
		explicitWaitUntilvisible(passwordTextBox);
		driver.findElement(passwordTextBox).sendKeys("automationfc");
		driver.findElement(submitButton).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Dashboard']")).getText(), "Dashboard");
	}

	public void explicitWaitUntilvisible(By locator) {
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
