package pageUI.Admin;

public class PostPageUI {
	public static final String ADD_NEW_POST_BUTTON = "//a[@class='page-title-action' and text()='Add New']";
	public static final String SEARCH_TEXTBOX = "//input[@id='post-search-input']";
	public static final String SEARCH_BUTTON = "//input[@id='search-submit']";
	public static final String POST_DETAIL_BY_TITLE_NAME = "//a[text()='%s']";
	public static final String DELETED_POST_MESSAGE = "//p[contains(text(),'1 post moved to the Trash.')]";
	public static final String NO_POST_FOUND_MESSAGE = "//td[text()='No posts found.']";

}
