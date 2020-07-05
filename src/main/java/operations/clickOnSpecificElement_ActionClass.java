package operations;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import properties.initializeXpaths;

public class clickOnSpecificElement_ActionClass extends initializeXpaths
{
	Properties propX;
	WebDriver driver;
	public clickOnSpecificElement_ActionClass(WebDriver driver, WebElement e) throws IOException
	{
		this.driver = driver;
		this.propX = super.propX;
		
		Actions action = new Actions(driver);
		action.click(e).build().perform();
	}
}
