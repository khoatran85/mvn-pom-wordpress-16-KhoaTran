package pageObjects.Admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.Admin.AbstractPageUI;
import pageUI.Admin.DashboardPageUI;

public class DashboardPageObject extends AbstractPage {
	WebDriver driver;

	public DashboardPageObject(WebDriver mapDriver) {
		// this.driver = mapDriver;
		driver = mapDriver;
	}

	public boolean isHeaderTextDisplayed() {
		return isElementDisplay(driver, DashboardPageUI.HEADER_TEXT);
	}

	public void clickToScreenOptions() {
		waitForElementVisible(driver, DashboardPageUI.SCREEN_OPTIONS_BUTTON);
		clickToElement(driver, DashboardPageUI.SCREEN_OPTIONS_BUTTON);
		sleepInSeconds(2);
	}

	public boolean isActivityCheckboxDisPlay() {
		return isElementDisplay(driver, DashboardPageUI.ACTIVITY_CHECKBOX);
	}
	

	public boolean isAllPostsSubmenuUnDisplayed() {
//		return isElementDisplay(driver, DashboardPageUI.ALL_POSTS_MENU);
		return isElementUndisplay(driver, DashboardPageUI.ALL_POSTS_MENU);
	}

	public boolean isPlanMenuUnDisplayed() {
		return isElementUnDisplay(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, "Plan");
	}

	
}
