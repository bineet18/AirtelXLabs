package reportsAndListeners;

import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport 
{
	static ExtentReports extent;
	static Properties propD;
	static String testcaseName1;
	
	public void sendTestcaseName(String testcaseName)
	{
		testcaseName1 = testcaseName;		
	}
	
	public static ExtentReports getReportObject()
	{
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		extent = new ExtentReports();
		
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
		reporter.setAppendExisting(true);
		reporter.config().setReportName("UI Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Bineet");
		
		return extent;
	}
	
	public static String getTestcasename()
	{
		return testcaseName1;
	}
	
	
}
