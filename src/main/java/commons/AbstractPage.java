package commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.Admin.DashboardPageObject;
import pageObjects.Admin.MediaPageObject;
import pageObjects.Admin.PagesPageObject;
import pageObjects.Admin.PostPageObject;
import pageObjects.User.HomePageObject;
import pageObjects.User.PostDetailPageObject;
import pageObjects.User.SearchReSultPage;
import pageUI.Admin.AbstractPageUI;
import pageUI.Admin.DashboardPageUI;
import pageUI.Admin.MediaPageUI;
import pageUI.Admin.PagesPageUI;
import pageUI.Admin.PostPageUI;
import pageUI.User.HomePageUI;

public abstract class AbstractPage {
	private Select select;
	private JavascriptExecutor js;
	private WebDriverWait explicitWait;
	private WebElement element;
	private List<WebElement> elements;
	private Actions action;

	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();

	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAler(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void senkeyToAlear(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void waitAlertPresent(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String runWindows : allWindowIDs) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String targetTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String runWindows : allWindowIDs) {
			driver.switchTo().window(runWindows);
			String currentWindowTitle = driver.getTitle();
			if (currentWindowTitle.equals(targetTitle)) {
				break;
			}
		}
	}

	public boolean areAllWindowsCloseWithoutParent(WebDriver driver, String parentWindow) {

		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String runWindows : allWindowIDs) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		}
		return false;
	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}
	public WebElement findElementByXpath(WebDriver driver, String locator, String...values) {
		return driver.findElement(byXpath(castToObject(locator, values)));
	}

	public String castToObject(String locator, String... values) {
		return String.format(locator, (Object[]) values);
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}

	public void sleepInSeconds(long timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickToElement(WebDriver driver, String locator) {
		if(driver.toString().toLowerCase().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInSeconds(5);
		}else {
			findElementByXpath(driver, locator).click();
		}
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		if(driver.toString().toLowerCase().contains("internet explorer")) {
			clickToElementByJS(driver, locator, values);
		}else {
			findElementByXpath(driver, locator, values).click();
		}
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}

	public String getElementText(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText().trim();
	}

	public String getElementAttribute(WebDriver driver, String locator, String AttributeName) {
		return findElementByXpath(driver, locator).getAttribute(AttributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String AttributeName, String...values) {
		return findElementByXpath(driver, castToObject(locator, values)).getAttribute(AttributeName);
	}
	
	public void selectValueInDropdown(WebDriver driver, String locator, String value) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(value);
	}

	public String getSelectedIteminDropdown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String allItemsLocator,
			String targetValue) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, parentLocator);
		js.executeScript("arguments[0].click();", element);
		sleepInSeconds(1);
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(allItemsLocator)));
		elements = findElementsByXpath(driver, allItemsLocator);
		for (WebElement item : elements) {
			if (item.getText().equals(targetValue)) {
				if (item.isDisplayed()) {
					js.executeScript("arguments[0].click();", item);
				} else {
					js.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSeconds(1);
					js.executeScript("arguments[0].click();", item);
				}
				sleepInSeconds(1);
				break;
			}
		}
	}

	public int countElementNumber(WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}
	public int countElementNumber(WebDriver driver, String locator, String...values) {
		return findElementsByXpath(driver, castToObject(locator, values)).size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplay(WebDriver driver, String locator) {
		try {
			return findElementByXpath(driver, locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementUndisplay(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, locator);
		if(elements.size() == 0) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		}
		else if(elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		}
		else {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}
	
	private void overrideGlobalTimeout(WebDriver driver, int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		
	}

	public boolean isElementDisplay(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
		
	}
	
	public boolean isElementUnDisplay(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, castToObject(locator, values));
		if(elements.size() == 0) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		}
		else if(elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		}
		else {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementEnable(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();

	}

	public boolean isElementselected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();

	}

	public void switchToFrameorIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}

	public void switchToDefault(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void DragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(findElementByXpath(driver, sourceLocator), findElementByXpath(driver, targetLocator))
				.perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}

	public void upload1FileBySenkey(WebDriver driver, String locator, String imagepath) {
		findElementByXpath(driver, locator).sendKeys(imagepath);

	}

	public void upload3FilesBySenkey(WebDriver driver, String locator, String imagepath1, String imagepath2,
			String imagepath3) {
		findElementByXpath(driver, locator).sendKeys(imagepath1 + "\n" + imagepath2 + "\n" + imagepath3);
	}

	public Object executeForBrowser(WebDriver driver, String javaSript) {
		js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}
	
	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
//		js.executeScript("arguments[0].setAttribute('value', '')", element);

	}

	public boolean verifyTextInInnerText(WebDriver driver, String expectedText) {
		js = (JavascriptExecutor) driver;
		String textActual = (String) js
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return textActual.equals(expectedText);
	}

	public void scrollToBottomPage(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	public void scrollToTopPage(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	
	public void highlightElement(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		sleepInSeconds(1);
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		js.executeScript("arguments[0].click();", element);
	}
	public void clickToElementByJS(WebDriver driver, String locator, String...values) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, castToObject(locator, values));
		js.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void scrollToElement(WebDriver driver, String locator, String...values) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, castToObject(locator, values));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		boolean status = (boolean) js.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				element);
		if (status) {
			return true;
		}
		return false;
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

//	public void waitForElementInvisible(WebDriver driver, String locator) {
//		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
//	}
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForElementsInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}
	public void waitForElementClickable(WebDriver driver, String locator, String...values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
	}

//switch menu

	public PostPageObject clickToPostMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.POST_LINK);
		clickToElement(driver, AbstractPageUI.POST_LINK);
		return PageGeneratorManager.getPostPageAdmin(driver);
	}

	public PagesPageObject clickToPagesMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.PAGES_LINK);
		clickToElement(driver, AbstractPageUI.PAGES_LINK);
		return PageGeneratorManager.getPagesPageAdmin(driver);
	}

	public MediaPageObject clickToMediaMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.MEDIA_LINK);
		clickToElement(driver, AbstractPageUI.MEDIA_LINK);
		return PageGeneratorManager.getMediaPageAdmin(driver);
	}

//Switch menu using dynamic locator
	public AbstractPage openPageByPageName(WebDriver driver, String pageName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);

//		if (pageName.equals("Posts")) {
//			return PageGeneratorManager.getPostPage(driver);
//		} else if (pageName.equals("Media")) {
//			return PageGeneratorManager.getMediaPage(driver);
//		} else if (pageName.equals("Pages")) {
//			return PageGeneratorManager.getPagesPage(driver);
//		} else {
//			return PageGeneratorManager.getDashboardPage(driver);
//		}

		switch (pageName) {
		case "Posts":
			return PageGeneratorManager.getPostPageAdmin(driver);
		case "Media":
			return PageGeneratorManager.getMediaPageAdmin(driver);
		case "Pages":
			return PageGeneratorManager.getPagesPageAdmin(driver);
		default:
			return PageGeneratorManager.getDashboardPageAdmin(driver);
		}

	}

	public void openPageByName(WebDriver driver, String pageName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
	}

	public void clickToAddNewButton(WebDriver driver) {
		waitForElementClickable(driver, MediaPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, MediaPageUI.ADD_NEW_BUTTON);

	}

	public boolean areAllItemsUploaded(WebDriver driver, String... imageName) {
		boolean status = false;
		waitForElementsInvisible(driver, MediaPageUI.UPLOAD_PROGRESS_BAR);
		sleepInSeconds(8);
		elements = findElementsByXpath(driver, MediaPageUI.ITEM_UPLOADED);

		int i = 0;
		// tạo mảng chứa các attribute "src" vừa mới upload
		List<String> imageLink = new ArrayList<String>();
		for (WebElement element : elements) {
			imageLink.add(element.getAttribute("src"));
			i++;
			System.out.println(element.getAttribute("src"));
			// lấy i = sô lượng item cần upload
			if (i == imageName.length) {
				break;
			}
		}

// tạo vòng lập kiểm tra các tên file có trong các link hình không
		// tạo vòng lập kiểm tra tên file trong mảng
		for (String image : imageName) {
			// cắt đuôi file
			String[] files = image.split("\\.");
			// chỉnh sang lowercase (nếu như hệ thống chỉ lưu lowercase)
			image = files[0].toLowerCase();

			System.out.println(image);
			// tạo vòng lặp kiểm tra link có chưa tên file không
			for (i = 0; i < imageLink.size(); i++) {
				if (!imageLink.get(i).contains(image)) {
					status = false;
				}
				if (i == imageLink.size() - 1) {
					return status;
				} else {
					status = true;
					break;
				}
			}

		}
		return status;

	}


	public void sendKeyToUploadMultipleFile(WebDriver driver, String... imageName) {
		String imageFullPath = "";
		for (String image : imageName) {
			imageFullPath = imageFullPath + GlobalConstants.UPLOAD_FOLDER + image + "\n";
		}
		imageFullPath = imageFullPath.trim();
//		System.out.println(imageFullPath);
		sendkeyToElement(driver, MediaPageUI.SELECT_FILE_UPLOAD_BUTTON, imageFullPath);
	}

	public HomePageObject openUserHomePage(WebDriver driver) {
		openUrl(driver, GlobalConstants.WORDPRESS_USER_URL);
		return PageGeneratorManager.getUserHomePage(driver);
		
	}
	public DashboardPageObject openAdminLoggedPage(WebDriver driver) {
		openUrl(driver, GlobalConstants.WORDPRESS_ADMIN_URL);
		return PageGeneratorManager.getDashboardPageAdmin(driver);
		
	}
	
	public SearchReSultPage InputToSearchTextbox(WebDriver driver, String value) {
	//wait
		waitForElementClickable(driver, AbstractPageUI.HOME_PAGE_SEARCH_BUTTON);
		clickToElement(driver, AbstractPageUI.HOME_PAGE_SEARCH_BUTTON);
	//sendkey
		waitForElementVisible(driver, AbstractPageUI.HOME_PAGE_SEARCH_TEXTBOX);
		sendkeyToElement(driver, AbstractPageUI.HOME_PAGE_SEARCH_TEXTBOX, value);

	//click search button
		sendKeyboardToElement(driver, AbstractPageUI.HOME_PAGE_SEARCH_TEXTBOX, Keys.ENTER);
	return PageGeneratorManager.getUserSearchReSultPage(driver);
	}

	
	public boolean isValueDisplayInRow(WebDriver driver, String columnName, String rowValue) {
		waitForElementVisible(driver, AbstractPageUI.ROW_VALUE_WITH_COLUMN_NAME, columnName, rowValue);
		return isElementDisplay(driver, AbstractPageUI.ROW_VALUE_WITH_COLUMN_NAME, columnName, rowValue);
	}
	
	public boolean isPostDisplayedOnFirstPost(WebDriver driver, String categoryName, String newPostTitle, String postCreatedDate) {
		waitForElementVisible(driver, AbstractPageUI.POST_NAME_CATEGORY_DATE, categoryName, newPostTitle, postCreatedDate);
		return isElementDisplay(driver, AbstractPageUI.POST_NAME_CATEGORY_DATE, categoryName, newPostTitle, postCreatedDate);
	}
	
	public boolean isPostImageDisplayedAtPostTitleName(WebDriver driver, String newPostTitle,String imageName) {
		String imagelink = getElementAttribute(driver, HomePageUI.POST_IMAGE_WITH_POST_TITLE_NAME, "src",newPostTitle, imageWithoutType(imageName));
		System.out.println(imagelink);
		if(imagelink.contains(imageWithoutType(imageName))) {
			return true;
		}
		return false;
	}
	
	public PostDetailPageObject clickToPostDetailWithNameTitle(WebDriver driver, String postTitle) {
		//popup accept cookie
		if (isElementDisplay(driver, "//form[@method='post']")) {
			clickToElement(driver, "//input[@value='Close and accept']");
		}
		waitForElementVisible(driver, HomePageUI.FIRST_POST_TITLE, postTitle);
		clickToElement(driver, HomePageUI.FIRST_POST_TITLE, postTitle);
		return PageGeneratorManager.getUserPostDetailPage(driver);
	}
	
	public String imageWithoutType(String imageName) {
		String[] images = imageName.split("\\.");
		String imageNameWithoutType = images[0];
		return imageNameWithoutType;
		
	}
}	

