package pages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import properties.initializeXpaths;

public class cartPage extends initializeXpaths
{
	WebDriver driver;
	Properties propX;
	public cartPage(WebDriver driver) throws IOException 
	{
		this.driver = driver;
		this.propX = super.propX;
	}
	
	public String getProductQuantity()
	{
		String quantityString = driver.findElement(By.xpath(propX.getProperty("productQuantity"))).getText();
		return quantityString.substring(0, 1);
	}
	
}
