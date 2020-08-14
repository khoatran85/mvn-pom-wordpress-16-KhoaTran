package pageObjects.Admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.Admin.LoginPageUI;

public class LoginPageObject extends AbstractPage{
	WebDriver driver;
	
	public LoginPageObject(WebDriver mapDriver){
		//this.driver = mapDriver;
		driver = mapDriver;
	}
	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}
	public DashboardPageObject clickToContinueOrLoginButton() {
		waitForElementClickable(driver, LoginPageUI.CONTINUE_OR_LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.CONTINUE_OR_LOGIN_BUTTON);
		return PageGeneratorManager.getDashboardPageAdmin(driver);
	}
	public void inputToPasswordTextbox(String pasword) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, pasword);
	}
	public String getEmailOrPasswordErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
	}
	public void openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
		
	}
	public String getLoginPageUrl() {
		return getCurrentUrl(driver);

	}



}