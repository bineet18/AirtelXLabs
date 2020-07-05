package reportsAndListeners;

import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport 
{
	static ExtentReports extent;
	static Properties propD;

	public static ExtentReports getReportObject()
	{
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		extent = new ExtentReports();
		
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
		reporter.setAppendExisting(true);
		reporter.config().setReportName("AirtelX SDET Testcases");
		reporter.config().setDocumentTitle("Test Results");
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Bineet");
		
		return extent;
	}
}
