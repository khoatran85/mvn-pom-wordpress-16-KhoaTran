package com.wordpress.post;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browserFactory.BrowserDriverFactory;
import browserFactory.DriverManager;
import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.Admin.DashboardPageObject;
import pageObjects.Admin.LoginPageObject;
import pageObjects.Admin.NewEditPostsPageObject;
import pageObjects.Admin.PostPageObject;
import pageObjects.User.HomePageObject;
import pageObjects.User.PostDetailPageObject;
import pageObjects.User.SearchReSultPage;
import pageUI.Admin.NewEditPostsPageUI;
import pageUI.Admin.PostPageUI;

public class Post_01_CreateViewEditDeletedPost extends AbstractTest {
	WebDriver driver;
	DriverManager driverManager;
	LoginPageObject loginPageAdmin;
	DashboardPageObject dashboardPageAdmin;
	PostPageObject postPageAdmin;
	NewEditPostsPageObject newEditPostsPageAdmin;
	HomePageObject userHomePage;
	PostDetailPageObject userPostDetail;
	SearchReSultPage userSearchResultPage;
	String imageName = "image1.png";

	String newPostTitle = "Automation_Test_Class_16_Topic " + randomNumber();
//	String newPostTitle = "Automation Test Class 16 Topic";
	String editPostTitle = "Manual_Test_Class_16";
	String newPostContent = "New Post Content " + randomNumber();
	String editPostContent = "Edit content Manual Test";
	String newPostCategoryName = "TEST COMPLETE";
	String editCategoryName = "EDIT LIVE CODING";
	String authorName = "Automation FC";
	String postCreatedDate = getCurrentDateText();
	String tagName = "Class_16";
	String editTagName = "Class_16" + randomNumber();

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName,String url) {
		log.info("Pre-condition - Open browser");
		driver = getBrowserDriver(browserName, url);
		loginPageAdmin = PageGeneratorManager.getLoginPageAdmin(driver);

		log.info("Pre-condition - Input Email");
		loginPageAdmin.inputToEmailTextbox(GlobalConstants.USER_NAME);

		log.info("Pre-condition - Click continue");
		loginPageAdmin.clickToContinueOrLoginButton();

		log.info("Pre-condition - Input Password");
		loginPageAdmin.inputToPasswordTextbox(GlobalConstants.PASSWORD);

		log.info("Pre-condition - Click login");
		dashboardPageAdmin = loginPageAdmin.clickToContinueOrLoginButton();
	}

	@Test
	public void Post_01_Creat_New_Post_At_Admin_Page() {
		log.info("Post_01 - Step 1: Click 'POSTS' menu");
		dashboardPageAdmin.openPageByName(driver, "Posts");
		postPageAdmin = PageGeneratorManager.getPostPageAdmin(driver);

		log.info("Post_01 - Step 2: Click 'Add New' button");
		newEditPostsPageAdmin = postPageAdmin.clickToAddNewPostButton();

		log.info("Post_01 - Step 3: Input Title");
		newEditPostsPageAdmin.inputToPostTitleTextbox(newPostTitle);

		log.info("Post_01 - Step 4: Input to Posts content textbox ");
		newEditPostsPageAdmin.inputToPostcontentTexbox(newPostContent);

		log.info("Post_01 - Step 5: Select 'TEST COMPLETE' category ");
		newEditPostsPageAdmin.selectCategoryCheckbox(newPostCategoryName);

		log.info("Post_01 - Step 6: Input text to Tag Textbox");
		newEditPostsPageAdmin.inputToTagTextbox(tagName);

		log.info("Post_01 - Step 7: Click To Add Tags button");
		newEditPostsPageAdmin.clickToAddTagsButton();

		log.info("Post_01 - Step 8: Click To Set Feature Image Link");
		newEditPostsPageAdmin.clickToSetFeaturedImageLink();

		log.info("Post_01 - Step 9: Click To Upload Files tab");
		newEditPostsPageAdmin.clickToUploadFileTab();

		log.info("Post_01 - Step 10: Upload image 'image1.png'");
		newEditPostsPageAdmin.sendKeyToUploadMultipleFile(driver, imageName);

		log.info("Post_01 - Step 11: Verify image1.png uploaded succesful");
//		verifyTrue(newEditPostsPageAdmin.areAllItemsUploaded(driver, imageName));
		verifyTrue(newEditPostsPageAdmin.isImageUploaded(imageName));

		log.info("Post_01 - Step 12: Click to Set Featured Image button");
		newEditPostsPageAdmin.clickToSetFeaturedImageButton();

		log.info("Post_01 - Step 13: Click Publish button");
		newEditPostsPageAdmin.clickToPublishUpdateButton();

		log.info("Post_01 - Step 14: Verify 'Post published.' displayed");
		verifyTrue(newEditPostsPageAdmin.isSuccessMessageDisplayed("Post published"));

		// search post at admin page
		log.info("Post_01 - Step 15: Click to 'Post' menu");
		newEditPostsPageAdmin.openPageByName(driver, "Posts");
		postPageAdmin = PageGeneratorManager.getPostPageAdmin(driver);

		log.info("Post_01 - Step 16: input post title in search textbox");
		postPageAdmin.inputToSearchTexbox(newPostTitle);

		log.info("Post_01 - Step 17: Click Search Post button");
		postPageAdmin.clickToSearchButton();

		// kiem tra bai thay viet
		log.info("Post_01 - Step 18: Verify post displayed in search result");
		verifyTrue(postPageAdmin.isValueDisplayInRow(driver, "Title", newPostTitle));

		// view post at user page
		log.info("Post_01 - Step 19: Verify post displayed in search result");
		userHomePage = postPageAdmin.openUserHomePage(driver);

		// design in abstract page (need review)
		log.info("Post_01 - Step 20: Verify new post display at first");
		
//		verifyEquals(userHomePage.isNewPostTitleNameDisplayed(driver, newPostTitle), "Automation Test Class 16 Topic");
		verifyTrue(userHomePage.isPostDisplayedOnFirstPost(driver, newPostCategoryName, newPostTitle, postCreatedDate));

//		kiem tra lại
		log.info("Post_01 - Step 21: Verify post image display above post title");
		verifyTrue(userHomePage.isPostImageDisplayedAtPostTitleName(driver, newPostTitle, imageName));

		log.info("Post_01 - Step 22: Click to 'Automation_Test_Class_16' post");
		userPostDetail = userHomePage.clickToPostDetailWithNameTitle(driver, newPostTitle);

		log.info("Post_01 - Step 23: verify post category is 'EDIT LIVE CODING'");
		verifyTrue(userPostDetail.isCategoryNameDisplayed(newPostCategoryName));

		log.info("Post_01 - Step 24: verify post title is 'Automation_Test_Class_16'");
		verifyTrue(userPostDetail.isPostTitleDisplayed(newPostTitle));

		log.info("Post_01 - Step 25: verify post image displayed");
		verifyTrue(userPostDetail.isPostImageDisplayed(imageName));

		log.info("Post_01 - Step 26: verify Post detail is 'New Post Content'");
		verifyTrue(userPostDetail.isTextContentDisplayed(newPostContent));

		log.info("Post_01 - Step 27: verify post title is 'Automation_Test_Class_16'");
		verifyTrue(userPostDetail.isDateCreatedDisplayed(postCreatedDate));

		log.info("Post_01 - Step 28: verify author Name display");
		verifyTrue(userPostDetail.isAuthorNameDisplayed(authorName));

		// search at user page
		log.info("Post_01 - Step 30: search with Post title");
		userSearchResultPage = userPostDetail.InputToSearchTextbox(driver, newPostTitle);

		log.info("Post_01 - Step 31: verify new post display");
		verifyTrue(userSearchResultPage.isPostDisplayedOnFirstPost(driver, newPostCategoryName, newPostTitle, postCreatedDate));
		verifyTrue(userSearchResultPage.isPostImageDisplayedAtPostTitleName(driver, newPostTitle, imageName));

	}

//	@Test
	public void Post_02_Edit_Post_At_Admin_Page() {
		log.info("Post_02 - Step 01 - Open Admin page");
		dashboardPageAdmin = userSearchResultPage.openAdminLoggedPage(driver);

		log.info("Post_02 - Step 02 - Click to Post menu");
		dashboardPageAdmin.openPageByName(driver, "Posts");
		postPageAdmin = PageGeneratorManager.getPostPageAdmin(driver);

		// search post at admin page
		log.info("Post_02 - Step 03 - Input post title in search post textbox");
		postPageAdmin.inputToSearchTexbox(newPostTitle);

		log.info("Post_02 - Step 04 - CLick to Search post");
		postPageAdmin.clickToSearchButton();

		log.info("Post_02 - Step 05 - Verify result");
		verifyTrue(postPageAdmin.isValueDisplayInRow(driver, "Title", newPostTitle));

		// click to post detail
		log.info("Post_02 - Step 06 - Click to post Name to view post detail");
		newEditPostsPageAdmin = postPageAdmin.clickToPostDetailByTitleName(newPostTitle);

		// Edit Post
		log.info("Post_02 - Step 07 - Edit Post Title");
		newEditPostsPageAdmin.inputToPostTitleTextbox(editPostTitle);
		;

		log.info("Post_02 - Step 08 - Edit Post content");
		newEditPostsPageAdmin.inputToPostcontentTexbox(editPostContent);
		;

		log.info("Post_02 - Step 09 - Deselect checked category checkbox");
		newEditPostsPageAdmin.deselectCategoryCheckbox();

		log.info("Post_02 - Step 10 - select new category checkbox");
		newEditPostsPageAdmin.selectCategoryCheckbox(editCategoryName);
		;

		log.info("Post_02 - Step 11 - input new tag");
		newEditPostsPageAdmin.inputToTagTextbox(editTagName);

		log.info("Post_02 - Step 12 - Click to Add Tag");
		newEditPostsPageAdmin.clickToAddTagsButton();

		log.info("Post_02 - Step 13 - Delete old tag");
		newEditPostsPageAdmin.clickToDeleteTagIconWithTagName(tagName);
		;

		log.info("Post_02 - Step 14 - Delete old tag");
		newEditPostsPageAdmin.clickToPublishUpdateButton();

		log.info("Post_02 - Step 15 - Verify update Post successs");
		verifyTrue(newEditPostsPageAdmin.isSuccessMessageDisplayed("Post updated"));

		log.info("Post_02 - Step 16 - CLick to Posts menu");
		newEditPostsPageAdmin.openPageByName(driver, "Posts");
		postPageAdmin = PageGeneratorManager.getPostPageAdmin(driver);

		// search post in admin page
		log.info("Post_02 - Step 17 - input edited Post Title");
		postPageAdmin.inputToSearchTexbox(editPostTitle);

		log.info("Post_02 - Step 18 - Click to Search button");
		postPageAdmin.clickToSearchButton();

		log.info("Post_02 - Step 19 - Verify Title name is 'Manual_Test_Class_16'");
		verifyTrue(postPageAdmin.isValueDisplayInRow(driver, "Title", editPostTitle));

		// navigate home page user
		log.info("Post_02 - Step 20 - Open User homepage");
		userHomePage = postPageAdmin.openUserHomePage(driver);

		// design in abstract page
		log.info("Post_02 - Step 21 - Verify Post with Category Name, Post Title, Created date Display");
		verifyTrue(userHomePage.isPostDisplayedOnFirstPost(driver, editCategoryName, editPostTitle, postCreatedDate));

		log.info("Post_02 - Step 22 - Verify post image display");
		verifyTrue(userHomePage.isPostImageDisplayedAtPostTitleName(driver, editPostTitle, imageName));

		log.info("Post_02 - Step 23 - Open Post detail");
		userHomePage.clickToPostDetailWithNameTitle(driver, editPostTitle);

		log.info("Post_02 - Step 24: verify post category is 'TEST COMPLETE'");
		verifyTrue(userPostDetail.isCategoryNameDisplayed(editCategoryName));

		log.info("Post_02 - Step 25: verify post title is 'Automation_Test_Class_16'");
		verifyTrue(userPostDetail.isPostTitleDisplayed(editPostTitle));

		log.info("Post_02 - Step 26: verify post image displayed");
		verifyTrue(userPostDetail.isPostImageDisplayed(imageName));

		log.info("Post_02 - Step 27: verify Post detail is 'New Post Content'");
		verifyTrue(userPostDetail.isTextContentDisplayed(editPostContent));

		log.info("Post_02 - Step 28: verify post title is 'Automation_Test_Class_16'");
		verifyTrue(userPostDetail.isDateCreatedDisplayed(postCreatedDate));

		log.info("Post_02 - Step 29: verify author Name display");
		verifyTrue(userPostDetail.isAuthorNameDisplayed(authorName));

		// search at user page
		log.info("Post_02 - Step 30: search edit post title in search textbox ");
		userSearchResultPage = userPostDetail.InputToSearchTextbox(driver, editPostTitle);

		log.info("Post_02 - Step 31: verify result display");
		verifyTrue(userSearchResultPage.isPostDisplayedOnFirstPost(driver, editCategoryName, editPostTitle, postCreatedDate));
		verifyTrue(userSearchResultPage.isPostImageDisplayedAtPostTitleName(driver, editPostTitle, imageName));
	}

//	@Test
	public void Post_03_Delete_Post_At_Admin_Page() {
		log.info("Post_03 - Step 01 - Open Admin page");
		dashboardPageAdmin = userSearchResultPage.openAdminLoggedPage(driver);

		log.info("Post_03 - Step 02 - Click to Post menu");
		dashboardPageAdmin.openPageByName(driver, "Posts");
		postPageAdmin = PageGeneratorManager.getPostPageAdmin(driver);

		// search post at admin page
		log.info("Post_03 - Step 03 - Input post title in search post textbox");
		postPageAdmin.inputToSearchTexbox(editPostTitle);

		log.info("Post_03 - Step 04 - CLick to Search post");
		postPageAdmin.clickToSearchButton();

		log.info("Post_03 - Step 05 - Verify result");
		verifyTrue(postPageAdmin.isValueDisplayInRow(driver, "Title", editPostTitle));

		// click to post detail
		log.info("Post_03 - Step 06 - Click to post Name to view post detail");
		newEditPostsPageAdmin = postPageAdmin.clickToPostDetailByTitleName(editPostTitle);

		// click to delete post
		log.info("Post_03 - Step 07 - Click to 'Move To Trash' button");
		postPageAdmin = newEditPostsPageAdmin.clickToMoveToTrashButton();

		log.info("Post_03 - Step 08 - Verify deleted success message display");
		verifyTrue(postPageAdmin.isSuccessMessageDisplayed());

		// search post in admin page
		log.info("Post_03 - Step 09 - input edit Title in search textbox");
		postPageAdmin.inputToSearchTexbox(editPostTitle);;

		log.info("Post_03 - Step 10 - click to search button");
		postPageAdmin.clickToSearchButton();

		log.info("Post_03 - Step 11 - Verify no post found message display");
		verifyTrue(postPageAdmin.isNoPostFoundMessageDisplayed());

		// navigate home page user
		log.info("Post_03 - Step 12 - Open user home page");
		userHomePage = postPageAdmin.openUserHomePage(driver);

		// design in abstract page
		log.info("Post_03 - Step 13 - Verify post not display in home page");
		verifyTrue(userHomePage.isPostUnDisplayedOnFirstPost(editCategoryName, editPostTitle, postCreatedDate));
//		verifyFalse(userHomePage.isPostImageDisplayedAtPostTitleName(driver, editPostTitle, imageName));

		// search at user page	
		log.info("Post_03 - Step 14 - Search post title");
		userSearchResultPage = userHomePage.InputToSearchTextbox(driver, editPostTitle);

		log.info("Post_03 - Step 15 - Verify No post found");
		verifyTrue(userSearchResultPage.isNoPostFoundMessageDisplay());
	}

	@AfterClass
	public void afterClass() {
//		closeBrowserAndDriver(driver);
	}

}
