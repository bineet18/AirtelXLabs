package operations;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;

public class takeScreenshot 
{
	public takeScreenshot(WebDriver driver) throws IOException
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screenshot.png"));
	}
}
