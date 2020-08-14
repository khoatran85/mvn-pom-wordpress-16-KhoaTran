package pageUI.Admin;

public class MediaPageUI {

	public static final String ADD_NEW_BUTTON = "//a[@role='button' and text()='Add New']";
	public static final String SELECT_FILE_UPLOAD_BUTTON = "//input[@type='file']";
	public static final String UPLOAD_PROGRESS_BAR = "//div[@class='thumbnail']/div[@class='media-progress-bar']";
	public static final String ITEM_UPLOADED = "//div[@class='thumbnail']//img";
	public static final String DELETED_ITEM_TARGET = "//div[@class='thumbnail']//img[@src='%s']";
	
	public static final String BULK_SELECT_BUTTON = "//button[text()='Bulk Select']";
	public static final String DELETE_PERMANENTLY_BUTTON = "//button[contains(@class,'delete-selected-button') and text()='Delete Permanently']";

}
