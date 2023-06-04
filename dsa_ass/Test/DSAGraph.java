//Code refers to Lee Wei Hang 20458472 lab 6
import java.util.*;
import java.io.*;

public class DSAGraph 
{
	DSALinkedList vertices; //store vertex values
	DSALinkedList nodeList; //store node values list
	DSALinkedList edgeList; //store edge values list
	int count;
	int value;
	int nodeCount; //food toy dog
	int edgeValue; //edge unit
	String startNode, targetNode;

	public DSAGraph()
	{
		vertices = new DSALinkedList();
		nodeList = new DSALinkedList();
		edgeList = new DSALinkedList();
		count = 0;
	}

	public void addVertex(String label, int value) //add vertices to linked list 
	{
		if(hasVertex(label))
		{
			throw new IllegalArgumentException("Vertice repeated");
		}
		else
		{
			DSAGraphVertex v = new DSAGraphVertex(label, value);
			vertices.insertLast(v);
			count++;
		}
	}

	public void deleteVertex(DSAGraphVertex vertex)
	{
		vertex.setLabel(null);
		vertex.setValue(0);
	}

	public DSALinkedList returnVertexList()
	{
		return vertices;
	}

	public void addEdge(String labelOne,String labelTwo, String stringEdgeLength) //will add into edge list
	{
		if(getVertex(labelOne) == null || getVertex(labelTwo) == null)
		{
			throw new IllegalArgumentException("Vertex does not exist");
		}
		else if (isAdjacent(labelOne, labelTwo))
		{
			throw new IllegalArgumentException("Vertex already linked");
		}
		else
		{
			int edgeLength;
			DSAGraphVertex v1,v2;
			v1 = getVertex(labelOne); 
			v2 = getVertex(labelTwo);

			edgeLength = getEdgeTypeValue(stringEdgeLength); 
			v1.addEdge(v2, edgeLength); // set value to edge				
		}
	}

	public void deleteEdge(String labelOne, String labelTwo)
	{
		if(isAdjacent(labelOne,labelTwo) == false)
		{
			throw new IllegalArgumentException("Vertex is not linked together");
		}
		else
		{
			DSAGraphVertex v1,v2;
			v1 = getVertex(labelOne); 
			v1.deleteEdge(labelTwo);
				
		}
	}

	public boolean isAdjacent(String labelOne, String labelTwo)
	{
		DSAGraphVertex v1, v2;
		DSALinkedList edge;
		String edgeString;
		boolean found = false;

		v1 = getVertex(labelOne);

		if(v1 != null)
		{
			edge = v1.getAdjacent();
		
			Iterator it = edge.iterator();// return gv class
			
			while(it.hasNext() && found == false)
			{
				v2 = (DSAGraphVertex)it.next();
				edgeString = v2.getLabel();
				if(edgeString != null)
				{
					if(edgeString.equals(labelTwo))
					{
						found = true;
					}
				}
			}
		}
		

		return found;
	}

	public int getEdgeValue(DSAGraphVertex v1, DSAGraphVertex v2) //value between A - B
	{
		return v1.getEdgeValue(v2.toString());
	}

	public void addEdgeType(String inEdgeType, String inEdgeValue) // edge type = edge weight
	{
		EdgeNode edge = new EdgeNode(inEdgeType, inEdgeValue);
		edgeList.insertLast(edge); //store edge length 
		//edge type is not added	
	}

	public int getEdgeTypeValue(String inEdgeType) // enter nodeType, return nodeValue
	{
		Iterator it = edgeList.iterator();
		EdgeNode edgeType;
		boolean found = false;
		String edgeTypeString;
		int edgeValue = 0;
		while(it.hasNext() && found == false)
		{
			edgeType = (EdgeNode)it.next(); 
			edgeTypeString = edgeType.getLabel();
			if(edgeTypeString != null)
			{
				if(edgeTypeString.equals(inEdgeType))
				{
					edgeValue = edgeType.getValue();
					found = true;
				}
			}
		}
		return edgeValue;
	}

	public void addNodeType(String nodeName, String nodeValue) //store node type value to linked list
	{
		EdgeNode node = new EdgeNode(nodeName, nodeValue);
		nodeList.insertLast(node); //store EdgeNode
		nodeCount++;
	}

	public String outputNodeValue() //no problem
    {
		String list = "";
    	Iterator i = nodeList.iterator();
    	while(i.hasNext())
    	{
    		EdgeNode vertex = (EdgeNode)i.next();
    		list += "Node: " + vertex.getLabel() + "\tValue: " + vertex.getValue() + "\n";
    	}

		return list;
	}

	public DSALinkedList returnNodeList()
	{
		return nodeList;
	}


	public boolean hasNodeValue(String node)
	{
		Iterator it =  nodeList.iterator();
		EdgeNode regNode;
		String regNodeString;
		boolean found = false;

		while(it.hasNext() && found == false)
		{
			regNode = (EdgeNode)it.next();
			regNodeString = regNode.getLabel();
			if(regNodeString != null)
			{
				if(regNodeString.equals(node))
				{
					found = true;
				}
			}
		}

		return found;
	}

	public EdgeNode getNode(String nodeName)
	{
		Iterator it =  nodeList.iterator();
		EdgeNode regNode, confirmedNode = null;
		String regNodeString;
		boolean found = false;

		while(it.hasNext() && found == false)
		{

			regNode = (EdgeNode)it.next();
			regNodeString = regNode.getLabel();
			if(regNodeString != null)
			{
				if(regNodeString.equals(nodeName))
				{
					confirmedNode = regNode;
					found = true;
				}
			}
		}

		return confirmedNode;
	}

	public int getNodeValue(String nodeName) 
	{
		Iterator it =  nodeList.iterator();
		EdgeNode regNode;
		String regNodeString;
		int nodeValue = 0;
		boolean found = false;

		while(it.hasNext() && found == false)
		{

			regNode = (EdgeNode)it.next();
			regNodeString = regNode.getLabel();
			if(regNodeString != null)
			{
				if(regNodeString.equals(nodeName))
				{
					
					nodeValue = regNode.getValue();
					found = true;
				}
			}
		}

		return nodeValue;
	}

	public void updateNodeValue(String nodeName, int nodeValue)/*once update, have to read the file again*/
	{
		Iterator it = nodeList.iterator();
		EdgeNode regNode;
		String regNodeString;
		boolean found = false;

		while(it.hasNext() && found == false)
		{
			regNode = (EdgeNode)it.next();
			regNodeString = regNode.getLabel();
			if(regNodeString != null)
			{
				if(regNodeString.equals(nodeName))
				{
					regNode.setValue(nodeValue);
					found = true;
				}
			}
		}
	}

	public void setStart(String inStartNode)
    {
    	startNode = inStartNode;
    }

    public void setTarget(String inTargetNode)
    {
    	targetNode = inTargetNode;
    }

	public int getNodeCount() //total node 
	{
		return nodeCount;
	}

	public boolean hasVertex(String label)
	{
		DSAGraphVertex tempVertex;
		String verLabel;
		Iterator iterator = vertices.iterator();
		boolean duplicate = false;

		
		while(iterator.hasNext() && duplicate == false)		
		{
			tempVertex = (DSAGraphVertex)iterator.next();
			verLabel = tempVertex.getLabel();
			if(verLabel != null)
			{
				if(verLabel.equals(label))
				{
					duplicate = true;	
				}
			}
		}
		
		return duplicate;
	}

	public int getVertexCount() //total vertex count
	{
		return count;
	}

	public int getTotalEdgeCount() //total edge count
	{ 
		DSAGraphVertex vertex;
		int count = 0;
		Iterator it = vertices.iterator();
		while(it.hasNext())
		{
			vertex = (DSAGraphVertex)it.next();
			count += vertex.getCount();
		}

		return count;
	}

	public DSAGraphVertex getVertex(String label) 
	{	
		DSAGraphVertex tempVertex;
		DSAGraphVertex finalVertex = null;
		String verLabel;
		Iterator iterator = vertices.iterator();
		int found = 0;

		do
		{
			tempVertex = (DSAGraphVertex)iterator.next();
			verLabel = tempVertex.getLabel();

			if(verLabel != null)
			{
				if(verLabel.equals(label))
				{
					finalVertex = tempVertex;
					found = 1;
				}
			}

		}while(iterator.hasNext() && found == 0);
		return finalVertex;
	}

	public DSALinkedList getAdjacent(String label) // 2 vertex is adjacent if there is edge between
	{
		DSAGraphVertex tempVertex;
		DSALinkedList edge;
		tempVertex = getVertex(label);
		edge = tempVertex.getAdjacent();

		return edge; //this adjacent is a EdgeNode linked list

	}

	public String [][] displayMatrix()
	{
		String graphMatrix[][], compareVertex[], edgeString;
		DSAGraphVertex vertex,edge;
		DSALinkedList edgeList;
		Iterator vertexIt = vertices.iterator();
		int totalCount = count + 1;
		boolean found = false;
		graphMatrix = new String [totalCount][totalCount];
		compareVertex = new String [totalCount];

		for(int i = 0; i < totalCount; i++)
		{
			for(int j = 0; j < totalCount; j++)
			{
				graphMatrix[i][j] = " ";
			}
		}

		for(int i = 1; i < totalCount; i++) 
		{
			vertex = (DSAGraphVertex)vertexIt.next();
			graphMatrix[0][i] = vertex.getLabel(); //store column
			graphMatrix[i][0] = vertex.getLabel(); //store row
			compareVertex[i] = vertex.getLabel(); //store vertex for compare
		}
		
		for(int i = 1; i < totalCount; i++)
		{
			vertex = getVertex(graphMatrix[i][0]); //get vertex
			edgeList = vertex.getAdjacent(); //get adj linked list
			
			for (int j = 1; j < totalCount; j++) 
			{	
				Iterator it = edgeList.iterator();
				found = false;
				while(it.hasNext() && found == false)
				{
					edge = (DSAGraphVertex)it.next();
					edgeString = edge.getLabel();
					if(edgeString != null)
					{
						if(edgeString.equals(compareVertex[j - 1]))
						{
							graphMatrix[i][j] = String.valueOf(edge.getValue());
							found = true;
						}

					}
				}

				if(found == false)
				{
					graphMatrix[i][j] = "0";
				}
			}
		}
	

		for(int i = 0; i < totalCount; i++) //print out
		{
			for(int j = 0; j < totalCount; j++)
			{
				System.out.print(graphMatrix[i][j] + " ");
			}
			System.out.println("\n");
		}

		return graphMatrix;
	}

	public String depthFirstSearch() //have to modify
    {
        String t = "{ ";
        Iterator i = vertices.iterator();
        DSAGraphVertex newVertex,oldVertex, vertex, currVertexClass, currVertex = null;
        DSAStack stack = new DSAStack();
        DSALinkedList list,list2;

        while(i.hasNext())/*set everything to new visit*/
        {
            newVertex = (DSAGraphVertex)i.next(); 
            newVertex.clearVisited();
        }
        oldVertex = (DSAGraphVertex)vertices.peekFirst(); //oldVertex = head = first element
        
        stack.push(oldVertex); 
        oldVertex.setVisited();

        while(stack.isEmpty() == false) //stack is not empty
        {
            vertex = (DSAGraphVertex)stack.top(); 
            list = vertex.getAdjacent();

            Iterator ii = list.iterator();
            while(ii.hasNext()) 
            {
                currVertexClass = (DSAGraphVertex)ii.next(); //currVertexClass = head of GraphVertex adjacent class
				if(currVertexClass != null)
				{
					currVertex = getVertex(currVertexClass.getLabel());

					if(currVertex.getVisited() == false) 
					{
						vertex = (DSAGraphVertex)stack.top(); 
						//if vertex.getLabel != null
						t = t + "(" + vertex.getLabel() + "," + currVertex.getLabel() + ")";
						currVertex.setVisited(); 
						stack.push(currVertex); 
						list2 = currVertex.getAdjacent(); 
						ii = list2.iterator();
					}
				}
            }
            
			stack.pop();	
		}
        t = t + " }";
        return t;
    }

     public String outputVertexValue() //got problem
    {
		String list = "";
    	Iterator i = vertices.iterator();
    	while(i.hasNext())
    	{
    		DSAGraphVertex vertex = (DSAGraphVertex)i.next();
    		list += ("Vertex: " + vertex.getLabel() + " 	Value: " + vertex.getValue() + "\n");
    	}

		return list;
    }

	public String outputVertex()
	{
		String list = "";
		Iterator i = vertices.iterator();
    	while(i.hasNext())
    	{
    		DSAGraphVertex vertex = (DSAGraphVertex)i.next();
			if(vertex.getLabel() != null)
			{
    			list += vertex.getLabel() + " ";
			}
					}
		return list;
	}

	public String outputEdge() //as matrix 
	{	
		String list = "";
		DSAGraphVertex vertex, inEdge;
		DSALinkedList edge;
		Iterator it = vertices.iterator(); //vertex

		while(it.hasNext())
		{
			vertex = (DSAGraphVertex)it.next(); //first vertex
			edge = vertex.getAdjacent(); //Edge

			Iterator it2 = edge.iterator();
			while(it2.hasNext())
			{
				inEdge = (DSAGraphVertex)it2.next(); //First edge
				list += vertex.getLabel() + "\t" + inEdge.getLabel() + "\t" +vertex.getEdgeValue(inEdge.getLabel()) + "\n";
			}
		}

		return list;
	}


 
    


}