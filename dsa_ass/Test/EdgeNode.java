//Stores node types and its values
import java.util.*;
public class EdgeNode
{
	String node; 
	int value;

	public EdgeNode(String inNode, String inValue)
	{
		node = inNode;
		value = Integer.parseInt(inValue);
	}

	public void setValue(int inValue)
	{
		value = inValue;
	}

	public String getLabel()
	{
		return node;
	}

	public int getValue()
	{
		return value;
	}
	
	public String toString() 
	{
		return node;
	}

}