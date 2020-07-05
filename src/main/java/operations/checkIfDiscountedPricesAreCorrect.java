package operations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class checkIfDiscountedPricesAreCorrect 
{
	public Set<String> checkForDiscountedPrices(ArrayList<ArrayList<String>> arr)
	{
		Set<String> result = new HashSet<String>();
		for(int i=0;i<arr.size();i++)
		{
			double newPrice = Double.parseDouble(arr.get(i).get(0));
			double oldPrice = Double.parseDouble(arr.get(i).get(1));
			double discount = Double.parseDouble(arr.get(i).get(2));
			
			double calculatedPrice = oldPrice - (oldPrice*discount/100);
			
			calculatedPrice = Double.parseDouble(String.format("%.2f", calculatedPrice));
			newPrice = Double.parseDouble(String.format("%.2f", newPrice));
			
			if(newPrice == calculatedPrice)
			{result.add("Correct");}
			else
			{result.add("Incorrect");}
		}
		return result;
	}
}
