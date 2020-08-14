package pageObjects.User;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.User.PostDetailPageUI;

public class PostDetailPageObject extends AbstractPage{
	WebDriver driver;
	public PostDetailPageObject(WebDriver mapDriver){
		driver = mapDriver;
	}
	public boolean isCategoryNameDisplayed(String categoryName) {
		waitForElementVisible(driver, PostDetailPageUI.CATEGORY_NAME, categoryName);
		if (isElementDisplay(driver, PostDetailPageUI.CATEGORY_NAME, categoryName)){
			return true;
		}
		return false;
	}
	public boolean isPostTitleDisplayed(String titleName) {
		waitForElementVisible(driver, PostDetailPageUI.POST_TITLE, titleName);
		if(isElementDisplay(driver, PostDetailPageUI.POST_TITLE, titleName)) {
			return true;
		}
		
		return false;
	}
	public boolean isPostImageDisplayed(String imageName) {
		waitForElementsVisible(driver, PostDetailPageUI.POST_IMAGE);
		String[] images = imageName.split("\\.");
		String imageNameWithoutType = images[0];
		String imagelink = getElementAttribute(driver, PostDetailPageUI.POST_IMAGE, "src");
		if(imagelink.contains(imageNameWithoutType)) {
			return true;
		}
		return false;
	}
	public boolean isTextContentDisplayed(String newPostContent) {
		waitForElementVisible(driver, PostDetailPageUI.POST_CONTENT,newPostContent);
//		scrollToElement(driver, PostDetailPageUI.POST_CONTENT);
		if(isElementDisplay(driver, PostDetailPageUI.POST_CONTENT, newPostContent)) {
			return true;
		}
		return false;
	}
	public boolean isAuthorNameDisplayed(String authorName) {
		waitForElementVisible(driver, PostDetailPageUI.AUTHOR_NAME, authorName);
		return isElementDisplay(driver, PostDetailPageUI.AUTHOR_NAME, authorName);
	}
	public boolean isDateCreatedDisplayed(String postCreatedDate) {
		waitForElementVisible(driver, PostDetailPageUI.CREATED_DATE, postCreatedDate);
		return isElementDisplay(driver, PostDetailPageUI.CREATED_DATE, postCreatedDate);
	}
	
}
