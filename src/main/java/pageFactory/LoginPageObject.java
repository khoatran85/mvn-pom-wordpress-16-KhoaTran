package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPageObject extends AbstractPage{
	WebDriver driver;
	
	@FindBy(id = "usernameOrEmail")
	private WebElement emailTexBox;
	
	@FindBy(xpath ="//button[@type='submit']")
	private WebElement continueOrLoginButton;
	
	@FindBy(id = "password")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement loginErrorMessage;
	
	
	public LoginPageObject(WebDriver mapDriver){
		//this.driver = mapDriver;
		driver = mapDriver;
		PageFactory.initElements(driver, this);
	}
	
	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTexBox);
		sendkeyToElement(driver, emailTexBox, email);
	}
	public void clickToContinueOrLoginButton() {
		waitForElementClickable(driver, continueOrLoginButton);
		clickToElement(driver, continueOrLoginButton);
		
	}
	public void inputToPasswordTextbox(String pasword) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, pasword);
	}
	public String getEmailOrPasswordErrorMessage() {
		waitForElementVisible(driver, loginErrorMessage);
		return getElementText(driver, loginErrorMessage);
	}
	public LoginPageObject openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
		return PageGeneratorManager.getLoginPage(driver);
	}
	public String getLoginPageUrl() {
		return getCurrentUrl(driver);

	}



}