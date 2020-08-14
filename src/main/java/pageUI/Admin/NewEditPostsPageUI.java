package pageUI.Admin;

public class NewEditPostsPageUI {
	public static final String POST_TITLE_TEXTBOX = "//input[@name='post_title']";
	public static final String DYNAMIC_CATEGORY_CHECKBOX = "//label[contains(text(),'%s')]";
	public static final String POST_CONTENT_IFRAME = "//iframe[@id='content_ifr']";
	public static final String POST_CONTENT_TEXTBOX = "//body[@id='tinymce']/p";
	public static final String TAGS_TEXTBOX = "//input[@name='newtag[post_tag]']";
	public static final String ADD_TAGS_BUTTON = "//input[@class='button tagadd']";
	public static final String SET_FEATURED_IMAGE_LINK = "//a[text()='Set featured image']";
	public static final String UPLOAD_FILES_TAB = "//button[@id='menu-item-upload']";
	public static final String SET_FEATURED_IMAGE_BUTTON = "//button[text()='Set featured image']";
	public static final String FEATURED_IMAGE_REVIEW = "//a[@id='set-post-thumbnail']/img";
	public static final String CHECKED_CATEGORY = "//ul[@id='categorychecklist']//input[@checked='checked']";
	public static final String DELETE_TAG_ICON = "//li[text()='%s']//span[@class='remove-tag-icon']";
	public static final String MOVE_TO_TRASH_TEXTLINK = "//a[@class='submitdelete deletion']";
	
}
