package pageObjects.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.Admin.AbstractPageUI;
import pageUI.User.HomePageUI;

public class HomePageObject extends AbstractPage {
	WebDriver driver;

	public HomePageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public boolean isPostUnDisplayedOnFirstPost(String editCategoryName, String editPostTitle, String postCreatedDate) {
		return isElementUnDisplay(driver, AbstractPageUI.POST_NAME_CATEGORY_DATE, editCategoryName, editPostTitle, postCreatedDate);
	}

	public String isNewPostTitleNameDisplayed(WebDriver driver, String postTitle) {
		return (String) executeForBrowser(driver, "return.document.querySelector('h2[class='post-title']>a').text)");
	}

}
