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
	
	ExtentReports extent = ExtentReport.getReportObject();
	ExtentTest test;
	Timestamp ts;
	
	@BeforeMethod
	public void beforeMethod() throws IOException
	{
		
		Date date= new Date();
		long time = date.getTime();
		ts = new Timestamp(time);
		
		
		initializeConfigurations c = new initializeConfigurations();
		propC = c.returnConfiguration();
		
		initializeDriver i= new initializeDriver();
		driver = i.returnDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void testcase1() throws IOException, InterruptedException
	{

		test = extent.createTest("Validate if Product is added in the Cart" + " "+ ts);
		
		driver.get(propC.getProperty("URL"));
		homePage h = new homePage(driver);
		h.clickOnPopularItems();
		map = h.returnAllPricings();
		h.addSpecificProductToCart(new returnLowestPrice().returnTheLowestPriceIndexFromSet(map));
		h.proceedToCheckout();
		
		new takeScreenshot(driver);
		
		cartPage cp = new cartPage(driver);
		String quantity = cp.getProductQuantity();
		
		Assert.assertTrue(quantity.equals("1"));
	}

	@Test
	public void testcase2() throws IOException, InterruptedException
	{
		test = extent.createTest("Validate if Discounted Price is correct" + " "+ ts);
		
		
		driver.get(propC.getProperty("URL"));
		homePage h = new homePage(driver);
		h.clickOnPopularItems();
		arr = h.returnDiscountedPricings();
		
		checkIfDiscountedPricesAreCorrect c = new checkIfDiscountedPricesAreCorrect();
		Set<String> result1 = c.checkForDiscountedPrices(arr);
		System.out.println("ABCD12345");
		Assert.assertTrue(!result1.contains("Incorrect"));
	}


	@AfterMethod
	public void afterMethod(ITestResult result) 
	{
		driver.close();
		
		if(result.getStatus() ==  ITestResult.SUCCESS)
		{
			test.log(Status.PASS, "PASSED");
		}
		if(result.getStatus() ==  ITestResult.FAILURE)
		{
			test.log(Status.PASS, "FAILED");
		}
		if(result.getStatus() ==  ITestResult.SKIP)
		{
			test.log(Status.PASS, "SKIPPED");
		}

		extent.flush();
	}
}
