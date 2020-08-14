package pageObjects.Admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.Admin.AbstractPageUI;
import pageUI.Admin.PostPageUI;

public class PostPageObject extends AbstractPage {
	WebDriver driver;

	public PostPageObject(WebDriver mapDriver) {
		// this.driver = mapDriver;
		driver = mapDriver;
	}

	public NewEditPostsPageObject clickToPostDetailByTitleName(String newPostTitle) {
		waitForElementClickable(driver, PostPageUI.POST_DETAIL_BY_TITLE_NAME, newPostTitle);
		clickToElement(driver, PostPageUI.POST_DETAIL_BY_TITLE_NAME, newPostTitle);
		return PageGeneratorManager.getNewEditPostsPageAdmin(driver);
	}

	public NewEditPostsPageObject clickToAddNewPostButton() {
		waitForElementVisible(driver, PostPageUI.ADD_NEW_POST_BUTTON);
		clickToElement(driver, PostPageUI.ADD_NEW_POST_BUTTON);
		return PageGeneratorManager.getNewEditPostsPageAdmin(driver);
	}



	public void inputToSearchTexbox(String value) {
		waitForElementVisible(driver, PostPageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, PostPageUI.SEARCH_TEXTBOX, value);
	}

	public void clickToSearchButton() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, PostPageUI.SEARCH_BUTTON);
		clickToElement(driver, PostPageUI.SEARCH_BUTTON);
	}

	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, PostPageUI.DELETED_POST_MESSAGE);
		return isElementDisplay(driver, PostPageUI.DELETED_POST_MESSAGE);
	}

	public boolean isNoPostFoundMessageDisplayed() {
		waitForElementVisible(driver, PostPageUI.NO_POST_FOUND_MESSAGE);
		return isElementDisplay(driver, PostPageUI.NO_POST_FOUND_MESSAGE);
	}


	

}