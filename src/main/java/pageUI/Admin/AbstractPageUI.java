package pageUI.Admin;

public class AbstractPageUI {
	public static final String HEADER_TEXT = "//h1[text()='Dashboard']";
	public static final String PAGES_LINK = "//div[text()='Pages']";
	public static final String MEDIA_LINK = "//div[text()='Media']";
	public static final String POST_LINK = "//div[text()='Posts']";
	public static final String UPLOAD_PROGRESS_BAR = "//div[@class='thumbnail']/div[@class='media-progress-bar']";

//dynamic locator	
	public static final String DYNAMIC_PAGE_LINK = "//div[text()='%s']";
	public static final String NEW_UPDATE_POST_SUCCESS_MESSAGE = "//p[contains(text(),'%s')]";
	public static final String ROW_VALUE_WITH_COLUMN_NAME = "//td[@data-colname='%s']//a[text()='%s']";
	public static final String POST_NAME_CATEGORY_DATE = "//a[text()='%s']/parent::p/following-sibling::h2/a[text()='%s']/parent::h2/following-sibling::p/a[text()='%s']";
	public static final String HOME_PAGE_SEARCH_BUTTON = "//a[@class='search-toggle']";
	public static final String HOME_PAGE_SEARCH_TEXTBOX = "//input[@placeholder='Search']";
	public static final String PUBLISH_UPDATE_UBUTTON = "//input[@id='publish']";


}
