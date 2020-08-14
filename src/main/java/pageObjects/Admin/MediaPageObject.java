package pageObjects.Admin;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUI.Admin.MediaPageUI;

public class MediaPageObject extends AbstractPage {
	WebDriver driver;

	public MediaPageObject(WebDriver mapDriver) {
		// this.driver = mapDriver;
		driver = mapDriver;
	}

	public void clickToBulkSelect() {
		waitForElementClickable(driver, MediaPageUI.BULK_SELECT_BUTTON);
		clickToElement(driver, MediaPageUI.BULK_SELECT_BUTTON);
	}

	public void clickToUploadedFiles(String... imageName) {
		waitForElementsVisible(driver, MediaPageUI.ITEM_UPLOADED);

		scrollToBottomPage(driver);
		waitForElementsVisible(driver, MediaPageUI.ITEM_UPLOADED);
		scrollToBottomPage(driver);
		waitForElementsVisible(driver, MediaPageUI.ITEM_UPLOADED);

		List<WebElement> elements = findElementsByXpath(driver, MediaPageUI.ITEM_UPLOADED);

		List<String> imageLink = new ArrayList<String>();
		List<String> imageRename = new ArrayList<String>();
		for (String image : imageName) {
			String[] files = image.split("\\.");
			image = files[0].toLowerCase();
			imageRename.add(image);
			for (WebElement element : elements) {
				String link = element.getAttribute("src");
				if (link.contains(image)) {
					System.out.println(imageLink);

					imageLink.add(link);

				}
			}
		}

		for (String link : imageLink) {
//	clickToElement(driver, MediaPageUI.DELETED_ITEM_TARGET, link);
			scrollToElement(driver, MediaPageUI.DELETED_ITEM_TARGET, link);
			clickToElementByJS(driver, MediaPageUI.DELETED_ITEM_TARGET, link);
//	clickToElement(driver, MediaPageUI.DELETED_ITEM_TARGET, link);
		}
	}

	public void clickToDeleteButton() {
		waitForElementClickable(driver, MediaPageUI.DELETE_PERMANENTLY_BUTTON);
		clickToElement(driver, MediaPageUI.DELETE_PERMANENTLY_BUTTON);
	}

	public void clickToOKInDeleteAlert() {
		waitAlertPresent(driver);
		acceptAlert(driver);
	}

	public boolean areAllItemsDeleted(String... imageName) {
		sleepInSeconds(5);
		boolean status = true;
		int i = 0;
		List<WebElement> elements = findElementsByXpath(driver, MediaPageUI.ITEM_UPLOADED);

		List<String> imageLink = new ArrayList<String>();
		for (WebElement element : elements) {
			imageLink.add(element.getAttribute("src"));
			System.out.println(element.getAttribute("src"));
		
		}

		for (String image : imageName) {
			String[] files = image.split("\\.");
			image = files[0].toLowerCase();

			System.out.println(image);
			for (i = 0; i < imageLink.size(); i++) {
				if (!imageLink.get(i).contains(image)) {
					status = true;
				}
				if (i == imageLink.size() - 1) {
					return status;
				} else {
					status = false;
					break;
				}
			}

		}
		return status;
	}
}
