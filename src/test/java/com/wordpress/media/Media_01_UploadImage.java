package com.wordpress.media;

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
import pageObjects.Admin.MediaPageObject;

public class Media_01_UploadImage {
	WebDriver driver;
	DriverManager driverManager;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	MediaPageObject mediaPage;
//	String image1 = "image1.png";
//	String image2 = "iMage2.png";
//	String image3 = "Image3.png";
String[] image = {"image1.png", "iMage2.png", "Image3.png"};
	@Parameters("browser")
	@BeforeTest
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		loginPage = PageGeneratorManager.getLoginPageAdmin(driver);
		loginPage.inputToEmailTextbox(GlobalConstants.USER_NAME);
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);
		loginPage.clickToContinueOrLoginButton();
		dashboardPage = PageGeneratorManager.getDashboardPageAdmin(driver);
	}

	@Test
	public void Media01_UploadMultipleFiles() {
		mediaPage = (MediaPageObject) dashboardPage.openPageByPageName(driver, "Media");
		mediaPage.clickToAddNewButton(driver);
		mediaPage.sendKeyToUploadMultipleFile(driver, image);
		Assert.assertTrue(mediaPage.areAllItemsUploaded(driver, image));
	}
@Test
	public void Media02_RemoveUploadedFiles() {
	mediaPage = (MediaPageObject) dashboardPage.openPageByPageName(driver, "Media");

		mediaPage.clickToBulkSelect();
		mediaPage.clickToUploadedFiles(image);
		mediaPage.clickToDeleteButton();
		mediaPage.clickToOKInDeleteAlert();
		Assert.assertFalse(mediaPage.areAllItemsDeleted(image));
	}
	@AfterTest
	public void afterClass() {
		driver.close();
	}

}
