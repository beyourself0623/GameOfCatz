//Code refers to Lee Wei Hang 20458472 lab 6
import java.util.*;

public class DSAGraphVertex
{
	String label; 
	DSALinkedList edgeList; // stores edge and edge's value 
	int value;
	boolean visited;
	int count;
	String nodeType;

	public DSAGraphVertex(String inLabel,int inValue)
	{
		setLabel(inLabel);
		setValue(inValue);
		edgeList = new DSALinkedList();
		visited = false;
		nodeType = "";
		count = 0;
	}

	public void setLabel(String inLabel)
	{
		label = inLabel;
	}

	public void setValue(int inValue)
	{
		value = inValue;
	}

	public String getLabel()
	{
		return label;
	}

	public int getValue()
	{
		return value;
	}

	public void setNodeType(String inNodeType)
	{
		nodeType = inNodeType;
	}

	public String getNodeType()
	{
		return nodeType;
	}

	public void addEdge(DSAGraphVertex vertex, int edgeLength) //called in graph class
	{
		DSAGraphVertex e = new DSAGraphVertex(vertex.toString(), edgeLength);
		edgeList.insertLast(e);
	
		count++;
	}

	public void updateEdgeValue(String edge, int edgeValue)
	{
		Iterator it = edgeList.iterator();
		boolean updated = false;
		DSAGraphVertex v;
		String vString;
		while(it.hasNext() && updated == false)
		{
			v = (DSAGraphVertex)it.next();
			vString = v.getLabel();
			if(vString != null)
			{
				if(vString.equals(edge)) 
				{
					v.setValue(edgeValue);
					updated = true;
				}
			}
		}
	}

	public void deleteEdge(String deleteVertex) // set the deleted edge's vertex as null
	{
		Iterator it = edgeList.iterator();
		boolean deleted = false;
		DSAGraphVertex v;
		String vString;
		while(it.hasNext() && deleted == false)
		{
			v = (DSAGraphVertex)it.next();
			vString = v.getLabel();
			if(vString != null)
			{
				if(vString.equals(deleteVertex)) 
				{
					v.setLabel(null);
					v.setValue(0);
					deleted = true;
				}
			}
		}
	}

	public int getEdgeValue(String vertexName)
	{
		Iterator it = edgeList.iterator();
		boolean found = false;
		DSAGraphVertex headEdge;
		String edgeString;
		int edgeValue = 0;
		while(it.hasNext() && found == false)
		{
			headEdge = (DSAGraphVertex)it.next();
			edgeString = headEdge.getLabel();

			if(edgeString != null)
			{
				if(edgeString.equals(vertexName))
				{
					edgeValue = headEdge.getValue();
					found = true;
				}
			}
		}
		return edgeValue;
	}

	public int getCount()
	{
		return count;
	}
	
	public DSALinkedList getAdjacent() //exports vertexlist
	{
		return edgeList; //return DSAGraphVertex class
	}

	public void setVisited() //for traversal
	{
		visited = true;
	}

	public void clearVisited()
	{
		visited = false;
	}

	public boolean getVisited()
	{
		return visited;
	}

	public String toString() 
	{
		return label;
	}

}