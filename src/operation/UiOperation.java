package operation;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UiOperation {
	static WebDriver driver;

	public UiOperation(WebDriver driver) {
		this.driver = driver;
	}

	public void perform(Properties p, String operation, String objectName,
			String objectType, String value) throws Exception {
		switch (operation.toUpperCase()) {
		case "CLICK":
			// Perform click
			Thread.sleep(5000);
			driver.findElement(this.getObject(p, objectName, objectType))
					.click();
			Thread.sleep(1000);
			break;

		case "SETTEXT":
			// Set text on control
			Thread.sleep(10000);
			// driver.switchTo().defaultContent();
			// driver.switchTo().frame("empcontentframe");
			Thread.sleep(1000);
			driver.findElement(this.getObject(p, objectName, objectType))
					.sendKeys(p.getProperty(value));
			break;
		case "DEFAULT":
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			break;

		case "FRAME":

			driver.switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions
					.frameToBeAvailableAndSwitchToIt(value));

			break;
	
		case "GOTOURL":
			// Get url of application
			driver.get(p.getProperty("url"));
			break;

		default:
			break;
		}
	}

	/**
	 * Find element BY using object type and value
	 * 
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(Properties p, String objectName, String objectType)
			throws Exception {
		// Find by xpath
		if (objectType.equalsIgnoreCase("XPATH")) {

			return By.xpath(p.getProperty(objectName));
		}
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {

			return By.className(p.getProperty(objectName));

		}
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) {

			return By.name(p.getProperty(objectName));

		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSS")) {

			return By.cssSelector(p.getProperty(objectName));

		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {

			return By.linkText(p.getProperty(objectName));

		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {

			return By.partialLinkText(p.getProperty(objectName));

		} else if (objectType.equalsIgnoreCase("ID")) {

			return By.id(p.getProperty(objectName));

		} else {
			throw new Exception("Wrong object type");
		}
	}
}