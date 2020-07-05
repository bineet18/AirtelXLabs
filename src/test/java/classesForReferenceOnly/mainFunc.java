package classesForReferenceOnly;

import java.io.IOException;
import java.util.Properties;
import java.util.TreeMap;

import org.openqa.selenium.WebDriver;

import operations.returnLowestPrice;
import operations.takeScreenshot;
import pages.cartPage;
import pages.homePage;
import properties.initializeConfigurations;
import properties.initializeDriver;

public class mainFunc {
	
	static Properties propC;
	static TreeMap<Double, Integer> map;

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		// TODO Auto-generated method stub
		
		initializeConfigurations c = new initializeConfigurations();
		propC = c.returnConfiguration();
		
		initializeDriver i= new initializeDriver();
		WebDriver driver = i.returnDriver();
		driver.manage().window().maximize();
		
		driver.get(propC.getProperty("URL"));
		
		homePage h = new homePage(driver);
		h.clickOnPopularItems();
		map = h.returnAllPricings();
		h.addSpecificProductToCart(new returnLowestPrice().returnTheLowestPriceIndexFromSet(map));
		h.proceedToCheckout();
		
		new takeScreenshot(driver);
		
		cartPage cp = new cartPage(driver);
		String quantity = cp.getProductQuantity();
		
		System.out.println(quantity);
		
		

	}

}
