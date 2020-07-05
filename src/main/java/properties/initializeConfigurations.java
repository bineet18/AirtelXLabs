package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class initializeConfigurations 
{
	public Properties propC;
	public initializeConfigurations() throws IOException
	{
		propC = new  Properties();
		
		FileInputStream fis = new FileInputStream("configuration.properties"); 		
		propC.load(fis);
	}
	
	public Properties returnConfiguration()
	{return propC;}
}
