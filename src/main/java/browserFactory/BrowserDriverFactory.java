package browserFactory;

public class BrowserDriverFactory {
	public static DriverManager getBrowserDriver(String browserName) {
		DriverManager driverManager;
		switch(browserName) {
		case "chrome":
			driverManager = new ChromeDriverManager();
			break;
		case "firefox":
			driverManager = new FirefoxDriverManager();
			break;
		default:
			driverManager = new FirefoxDriverManager();
			break;
	}
		return driverManager;
}	
}