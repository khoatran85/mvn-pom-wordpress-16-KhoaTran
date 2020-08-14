package pageObjects.User;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.User.SearchReSultPageUI;

public class SearchReSultPage extends AbstractPage{
	WebDriver driver;
	public SearchReSultPage(WebDriver mapDriver){
		driver = mapDriver;
	}
	public boolean isNoPostFoundMessageDisplay() {
		return isElementDisplay(driver, SearchReSultPageUI.NO_POST_FOUND_MESSAGE);
	}
	
}
