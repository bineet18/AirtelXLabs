package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import operations.clickOnSpecificElement_ActionClass;
import operations.moveToSpecificElement;
import properties.initializeXpaths;

public class homePage extends initializeXpaths
{
	WebDriver driver;
	Properties propX;
	public homePage(WebDriver driver) throws IOException 
	{
		this.driver = driver;
		this.propX = super.propX;
	}
	
	public void clickOnPopularItems()
	{
		//driver.findElement(By.xpath(propX.getProperty("popularListings"))).click();
	}
	
	
	public TreeMap<Double, Integer> returnAllPricings() throws InterruptedException
	{
		TreeMap<Double, Integer> map = new TreeMap<Double, Integer>(); 
		
		int size = driver.findElements(By.xpath(propX.getProperty("PopularPricing"))).size();
		
		for(int i=1;i<=size;i++)
		{
			WebElement e = driver.findElement(By.xpath("("+ propX.getProperty("PopularPricing") +")["+i+"]"));
			String priceWithDollar = e.getText();
			Double price = Double.parseDouble(priceWithDollar.substring(1));
			
			map.put(price, i);
		}
		
		return map;
	}
	
	
	public ArrayList<ArrayList<String>> returnDiscountedPricings()
	{
		int size = driver.findElements(By.xpath(propX.getProperty("popularOldPricing"))).size();
		
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		
		for(int i=1;i<=size;i++)
		{
			ArrayList<String> temp = new ArrayList<String>();
			WebElement newPrice = driver.findElement(By.xpath("("+ propX.getProperty("popularOldPricing") +"/preceding-sibling::span)["+i+"]"));
			WebElement oldPrice = driver.findElement(By.xpath("("+ propX.getProperty("popularOldPricing") +")["+i+"]"));
			WebElement discount = driver.findElement(By.xpath("("+ propX.getProperty("popularDiscount") +")["+i+"]"));
			
			temp.add(newPrice.getText().substring(1));
			temp.add(oldPrice.getText().substring(1));
			
			String discountString = discount.getText().substring(1);
			temp.add(discountString.substring(0,discountString.length()-1));
			
			arr.add(temp);
		}
		return arr;
	}

	
	
	public void addSpecificProductToCart(int index) throws IOException
	{
		WebElement e1 = driver.findElement(By.xpath("("+ propX.getProperty("PopularPricing") +")["+index+"]"));
		
		new moveToSpecificElement(driver, e1);
		
		WebElement e2 = driver.findElement(By.xpath("("+ propX.getProperty("addToCart") +")["+index+"]"));
		e2.click();
	}
	
	
	public void proceedToCheckout() throws InterruptedException, IOException
	{
		WebElement e1 = driver.findElement(By.xpath(propX.getProperty("proceedToCheckout")));Thread.sleep(4000);
		new moveToSpecificElement(driver,e1);
		new clickOnSpecificElement_ActionClass(driver, e1);
	}
	
		
}
