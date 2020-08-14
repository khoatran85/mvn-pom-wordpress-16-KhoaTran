package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPageObject extends AbstractPage {
	WebDriver driver;

	@FindBy(xpath = "//h1[text()='Dashboard']")
	private WebElement headerText;

	public DashboardPageObject(WebDriver mapDriver) {
		// this.driver = mapDriver;
		driver = mapDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isHeaderTextDisplayed() {
		waitForElementVisible(driver, headerText);
		return isElementDisplay(driver, headerText);
	}

}
