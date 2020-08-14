package pageObjects.Admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.Admin.AbstractPageUI;
import pageUI.Admin.NewEditPostsPageUI;
import pageUI.Admin.PostPageUI;

public class NewEditPostsPageObject extends AbstractPage{
	WebDriver driver;
	public NewEditPostsPageObject(WebDriver mapDriver){
		driver = mapDriver;
	}
	public void inputToPostTitleTextbox(String postTitle) {
		waitForElementVisible(driver, NewEditPostsPageUI.POST_TITLE_TEXTBOX);
		sendkeyToElement(driver, NewEditPostsPageUI.POST_TITLE_TEXTBOX, postTitle);
	}
	public void inputToPostcontentTexbox(String value) {
		waitForElementVisible(driver, NewEditPostsPageUI.POST_CONTENT_IFRAME);
		switchToFrameorIframe(driver, NewEditPostsPageUI.POST_CONTENT_IFRAME);
		waitForElementVisible(driver, NewEditPostsPageUI.POST_CONTENT_TEXTBOX);
		clickToElementByJS(driver, NewEditPostsPageUI.POST_CONTENT_TEXTBOX);
		sendkeyToElement(driver, NewEditPostsPageUI.POST_CONTENT_TEXTBOX, value);
		switchToDefault(driver);
	}
	public void selectCategoryCheckbox(String categoryName) {
//		waitForElementVisible(driver, NewEditPostsPageUI.DYNAMIC_CATEGORY_CHECKBOX, categoryName);
//		scrollToElement(driver, NewEditPostsPageUI.DYNAMIC_CATEGORY_CHECKBOX, categoryName);
//		sleepInSeconds(1);
//		clickToElement(driver, NewEditPostsPageUI.DYNAMIC_CATEGORY_CHECKBOX, categoryName);
		clickToElementByJS(driver, NewEditPostsPageUI.DYNAMIC_CATEGORY_CHECKBOX, categoryName);
	}
	public void inputToTagTextbox(String value) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, NewEditPostsPageUI.TAGS_TEXTBOX);
		sendkeyToElement(driver, NewEditPostsPageUI.TAGS_TEXTBOX, value);
	}
	public void clickToAddTagsButton() {
		waitForElementClickable(driver, NewEditPostsPageUI.ADD_TAGS_BUTTON);
		clickToElement(driver, NewEditPostsPageUI.ADD_TAGS_BUTTON);
	}
	public void clickToSetFeaturedImageLink() {
		waitForElementClickable(driver, NewEditPostsPageUI.SET_FEATURED_IMAGE_LINK);
		clickToElement(driver, NewEditPostsPageUI.SET_FEATURED_IMAGE_LINK);
	}
	public void clickToUploadFileTab() {
		waitForElementClickable(driver, NewEditPostsPageUI.UPLOAD_FILES_TAB);
		clickToElement(driver, NewEditPostsPageUI.UPLOAD_FILES_TAB);
	}
	public void clickToSetFeaturedImageButton() {
		waitForElementClickable(driver, NewEditPostsPageUI.SET_FEATURED_IMAGE_BUTTON);
		clickToElement(driver, NewEditPostsPageUI.SET_FEATURED_IMAGE_BUTTON);
	}
	public void clickToPublishUpdateButton() {
		waitForElementsVisible(driver, NewEditPostsPageUI.FEATURED_IMAGE_REVIEW);
		scrollToTopPage(driver);
		clickToElement(driver, AbstractPageUI.PUBLISH_UPDATE_UBUTTON);
	
	}
	public boolean isImageUploaded(String imageName) {
		waitForElementInvisible(driver, AbstractPageUI.UPLOAD_PROGRESS_BAR);		
		String[] images = imageName.split("\\.");
		String imageNameWithoutType = images[0];
		if(findElementByXpath(driver, "//div[@class='filename']").getText().contains(imageNameWithoutType)){
			return true;
		}
		return false;
	}
	public void deselectCategoryCheckbox() {
		waitForElementClickable(driver, NewEditPostsPageUI.CHECKED_CATEGORY);
		uncheckToCheckbox(driver, NewEditPostsPageUI.CHECKED_CATEGORY);
		
	}
	public void clickToDeleteTagIconWithTagName(String categoryName) {
		waitForElementClickable(driver, NewEditPostsPageUI.DELETE_TAG_ICON, categoryName);
		clickToElement(driver, NewEditPostsPageUI.DELETE_TAG_ICON, categoryName);
		
	}

	public boolean isSuccessMessageDisplayed(String expectedMessage) {
		waitForElementVisible(driver, AbstractPageUI.NEW_UPDATE_POST_SUCCESS_MESSAGE, expectedMessage);
		return isElementDisplay(driver, AbstractPageUI.NEW_UPDATE_POST_SUCCESS_MESSAGE, expectedMessage);
	}
	public PostPageObject clickToMoveToTrashButton() {
		waitForElementClickable(driver, NewEditPostsPageUI.MOVE_TO_TRASH_TEXTLINK);
		clickToElement(driver, NewEditPostsPageUI.MOVE_TO_TRASH_TEXTLINK);
		return PageGeneratorManager.getPostPageAdmin(driver);
	}
}
