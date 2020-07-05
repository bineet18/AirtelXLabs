package SDETExercise;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import operations.checkIfDiscountedPricesAreCorrect;
import operations.returnLowestPrice;
import operations.takeScreenshot;
import pages.cartPage;
import pages.homePage;
import properties.initializeConfigurations;
import properties.initializeDriver;
import reportsAndListeners.ExtentReport;

public class testcases 
{
	static Properties propC;
	static TreeMap<Double, Integer> map;
	static WebDriver driver;
	static ArrayList<ArrayList<String>> arr;
	
	@BeforeMethod
	public void beforeMethod() throws IOException
	{
		
		initializeConfigurations c = new initializeConfigurations(); // Initialize the Configuration File
		propC = c.returnConfiguration();
		
		initializeDriver i= new initializeDriver(); // Initialize the WebDriver
		driver = i.returnDriver();
		driver.manage().window().maximize(); // Maximize the Window
	}
	
	@Test
	public void addProductToCart() throws IOException, InterruptedException
	{

		//test = extent.createTest("Validate if Product is added in the Cart" + " "+ ts); // Create the Testcase name
		
		driver.get(propC.getProperty("URL"));// Step1: Navigate to the URL
		
		homePage h = new homePage(driver);
		h.clickOnPopularItems(); // Step2: Click on the Popular icon 
		map = h.returnAllPricings();
		
		h.addSpecificProductToCart(new returnLowestPrice()
				.returnTheLowestPriceIndexFromSet(map));// Step3: Add the item with the lowest Price
		h.proceedToCheckout(); // Step4: Click  on the Checkout and navigate to Cart
		
		new takeScreenshot(driver); // Step5: Take the screenshot on the Cart page
		
		cartPage cp = new cartPage(driver);
		String quantity = cp.getProductQuantity();// Step 6: Get the quantity of the items added
		
		Assert.assertTrue(quantity.equals("1"));// Step7: Validate that the quantity of the item added is 1
	}

	@Test
	public void validateDiscountPrice() throws IOException, InterruptedException
	{
		//test = extent.createTest("Validate if Discounted Price is correct" + " "+ ts);// Create the Testcase name
		
		
		driver.get(propC.getProperty("URL")); // Step1: Navigate to the URL
		
		homePage h = new homePage(driver);
		h.clickOnPopularItems(); //Step2: Click on the Popular icon 
		arr = h.returnDiscountedPricings(); //Step3: Get all the prices with the discount on it
		
		//Step4: Check if the discounted prices are computed correctly
		checkIfDiscountedPricesAreCorrect c = new checkIfDiscountedPricesAreCorrect(); 
		Set<String> result1 = c.checkForDiscountedPrices(arr);
		Assert.assertTrue(!result1.contains("Incorrect"));
	}


	@AfterMethod
	public void afterMethod(ITestResult result) 
	{
		driver.close();
	}
}
