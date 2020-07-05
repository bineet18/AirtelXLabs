package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class initializeXpaths 
{
	public Properties propX;
	public initializeXpaths() throws IOException
	{
		propX = new  Properties();
		
		FileInputStream fis = new FileInputStream("xpath.properties"); 		
		propX.load(fis);
	}
}
