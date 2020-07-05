package operations;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import properties.initializeXpaths;

public class moveToSpecificElement extends initializeXpaths
{
	Properties propX;
	WebDriver driver;
	public moveToSpecificElement(WebDriver driver, WebElement e) throws IOException
	{
		this.driver = driver;
		this.propX = super.propX;
		
		Actions action = new Actions(driver);
		action.moveToElement(e).build().perform();
	}
}
