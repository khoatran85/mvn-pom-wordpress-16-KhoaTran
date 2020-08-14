package pageFactory;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	private Select select;
	private JavascriptExecutor js;
	private WebDriverWait explicitWait;
	private WebElement element;
	private List<WebElement> elements;
	private Actions action;
	private long longTimeout = 30;

	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}


	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}


	public void sleepInSeconds(long timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();

	}

	public void sendkeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText().trim();
	}

	

	public void waitForElementVisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementInvisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}


	public boolean isElementDisplay(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}

}
