package pageObjects.Admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.Admin.LoginPageUI;
import pageUI.Admin.PagesPageUI;
import pageUI.Admin.PostPageUI;

public class PagesPageObject extends AbstractPage{
	WebDriver driver;
	
	public PagesPageObject(WebDriver mapDriver){
		//this.driver = mapDriver;
		driver = mapDriver;
	}

}