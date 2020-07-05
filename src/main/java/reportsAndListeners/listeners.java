package reportsAndListeners;

import java.sql.Timestamp;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class listeners implements ITestListener 
{
	
	ExtentReports extent = ExtentReport.getReportObject();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) 
	{
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		
		String testcaseName = result.getMethod().getMethodName();
		
		test = extent.createTest(testcaseName+" "+ ts);
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, "PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		test.fail(result.getThrowable());
		test.log(Status.FAIL, "FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		test.log(Status.SKIP, "Execution has Skipped");
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}

}
