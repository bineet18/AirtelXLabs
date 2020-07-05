package operations;
import java.util.TreeMap;

public class returnLowestPrice 
{
	public Integer returnTheLowestPriceIndexFromSet(TreeMap<Double, Integer> map)
	{
		Double d = map.keySet().iterator().next();
		return map.get(d);
	}
}
