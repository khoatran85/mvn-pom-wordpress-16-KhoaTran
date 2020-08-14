package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.Admin.DashboardPageObject;
import pageObjects.Admin.LoginPageObject;
import pageObjects.Admin.MediaPageObject;
import pageObjects.Admin.NewEditPostsPageObject;
import pageObjects.Admin.PagesPageObject;
import pageObjects.Admin.PostPageObject;
import pageObjects.User.HomePageObject;
import pageObjects.User.PostDetailPageObject;
import pageObjects.User.SearchReSultPage;

public class PageGeneratorManager {

	public static LoginPageObject getLoginPageAdmin(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static DashboardPageObject getDashboardPageAdmin(WebDriver driver) {
		return new DashboardPageObject(driver);
		
	}

	public static PostPageObject getPostPageAdmin(WebDriver driver) {
		return new PostPageObject(driver);
	}

	public static MediaPageObject getMediaPageAdmin(WebDriver driver) {
		return new MediaPageObject(driver);
	}

	public static PagesPageObject getPagesPageAdmin(WebDriver driver) {
		return new PagesPageObject(driver);
	}
	
	public static NewEditPostsPageObject getNewEditPostsPageAdmin(WebDriver driver) {
		return new NewEditPostsPageObject(driver);
	}
	public static HomePageObject getUserHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static PostDetailPageObject getUserPostDetailPage(WebDriver driver) {
		return new PostDetailPageObject(driver);
	}
	public static SearchReSultPage getUserSearchReSultPage(WebDriver driver) {
		return new SearchReSultPage(driver);
	}
	
	
}
