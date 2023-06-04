//Code refers to Lee Wei Hang 20458472 lab 6
import java.util.*;
import java.io.*;
import java.nio.file.Paths;

public class DSAGraph 
{
	DSALinkedList vertices; //store vertex values
	DSALinkedList nodeList; //store node values list
	DSALinkedList edgeList; //store edge values list
	EdgeNode [] result;
	int count; //vertex count
	int value;
	int nodeCount; //food toy dog
	int edgeValue; //edge unit
	int resultIndex = 0;
	String startNode, targetNode;

	public DSAGraph()
	{
		vertices = new DSALinkedList();
		nodeList = new DSALinkedList();
		edgeList = new DSALinkedList();
		result = new EdgeNode[10];
		count = 0;
	}

	public DSALinkedList getVertexList()
	{
		return vertices;
	}

	public DSALinkedList getNodeList()
	{
		return nodeList;
	}

	public DSALinkedList getEdgeList()
	{
		return edgeList;
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
		DSAGraphVertex inEdgeVertex, edgeVertex;
		String edgeString, deleteString;
		DSALinkedList edge;

		deleteString = vertex.toString();
		System.out.println("Node entered: " + vertex.toString() + "\n");
		
		//delete node's edge
		edge = vertex.getAdjacent();// return edge (linked list)

		Iterator it = edge.iterator();
		
		while(it.hasNext()) // delete vertex from it's edge
		{
			//iterate edge list
			inEdgeVertex = (DSAGraphVertex)it.next(); //graph vertex B E
			edgeString = inEdgeVertex.toString(); // edge's string
			if(edgeString != null)
			{
				edgeVertex = getVertex(edgeString);//get edgeSting's vertex to delete edge 
				edgeVertex.deleteEdge(deleteString);
				vertex.deleteEdge(edgeString); //A.delete(B)
			}
		}

		vertex.setLabel(null);
		vertex.setValue(0);
		count--;
	}

	public void updateNodeValue(String inputNode, int newValue) //update node type value
	{
		DSAGraphVertex checkVertex;
		String vertexString;
		Iterator vertexList = vertices.iterator();
		while(vertexList.hasNext())
		{
			checkVertex = (DSAGraphVertex)vertexList.next();
			if(checkVertex != null)
			{
				vertexString = checkVertex.getNodeType();

				if(vertexString.equals(inputNode)) //if vertex has updated node data type, update value 
				{
					checkVertex.setValue(newValue);
				}
			}
			
		}
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
			DSAGraphVertex v1;
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

	public String outputNodeValue() 
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

	public boolean hasNodeType(String node)
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

	public boolean hasEdgeType(String edgeType)
	{
		Iterator it =  edgeList.iterator();
		EdgeNode edge;
		String edgeString;
		boolean found = false;

		while(it.hasNext() && found == false)
		{
			edge = (EdgeNode)it.next();
			edgeString = edge.getLabel();
			if(edgeString != null)
			{
				if(edgeString.equals(edgeType))
				{
					found = true;
				}
			}
		}

		return found;
	}

	public EdgeNode getNodeType(String nodeName)
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

	public void setStart(String inStartNode)
    {
    	startNode = inStartNode;
    }

    public void setTarget(String inTargetNode)
    {
    	targetNode = inTargetNode;
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
			if(vertex.getLabel() != null)
			{
				graphMatrix[0][i] = vertex.getLabel(); //store column
				graphMatrix[i][0] = vertex.getLabel(); //store row
				compareVertex[i] = vertex.getLabel(); //store vertex for compare
			}
		}
		
		for(int i = 1; i < totalCount; i++)
		{
			vertex = getVertex(graphMatrix[i][0]); //get vertex
			if(vertex != null)
			{
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
							if(edgeString.equals(compareVertex[j]))
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
			
		}

		return graphMatrix;
	}

	public void depthFirstSearch() 
    {
       
		String path = "{ ";
		String vertexString, edgeString;
        Iterator i = vertices.iterator();
        DSAGraphVertex newVertex,oldVertex, vertex, currVertexClass, currVertex = null;
        DSAStack stack = new DSAStack();
		DSAStack pathStack = new DSAStack(); //store path string
        DSAStack pathValue = new DSAStack(); //store path value
        DSALinkedList list, list2;
		int distance = 0, vertexValue, adjValue, edgeLength;

        while(i.hasNext())//set everything to new visit
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

					if(currVertex != null)
					{
						edgeString = currVertex.getLabel();

						if(currVertex.getVisited() == false && !edgeString.equals(targetNode)) //when vertex is not visited, and is not destination
						{
							vertex = (DSAGraphVertex)stack.top(); 
							vertexString = vertex.getLabel();

							vertexValue = vertex.getValue(); // vertex 1 value
							adjValue = currVertex.getValue(); //vertex 2 value
							edgeLength = vertex.getEdgeValue(edgeString); //edge length between vertex 1 and 2

							if(pathStack.isEmpty() && pathValue.isEmpty())
							{
								path = "(" + vertexString + "," + edgeString + ")";
								distance = vertexValue + adjValue + edgeLength;		
							}
							else
							{
								path = String.valueOf(pathStack.top());
								path += "(" + vertexString + "," + edgeString + ")";

								distance = (int)pathValue.top();
								distance += vertexValue + adjValue + edgeLength;
							}

							currVertex.setVisited(); 
							pathStack.push(path);
							pathValue.push(distance);
							stack.push(currVertex); 

							list2 = currVertex.getAdjacent(); 
							ii = list2.iterator();
						}
						else if(edgeString.equals(targetNode))
						{
							vertex = (DSAGraphVertex)stack.top(); 
							vertexString = vertex.getLabel();

							vertexValue = vertex.getValue(); // vertex 1 value
							adjValue = currVertex.getValue(); //vertex 2 value
							edgeLength = vertex.getEdgeValue(edgeString); //edge length between vertex 1 and 2

							path = String.valueOf(pathStack.top());
							path += "(" + vertexString + "," + edgeString + ") }";

							distance = (int)pathValue.top();
							distance += vertexValue + adjValue + edgeLength;

							result[resultIndex] = new EdgeNode(path, String.valueOf(distance));
							resultIndex++;
						}
					}
				}
            }
            
			stack.pop();
				
		}
    }

	public String displayGraphPath()
	{
		String list = "";
		for(int i = 0; i < resultIndex; i++)
		{
			list += ("Route " + (i + 1) );
			list += (result[i].getLabel()+ "\tValue: " + result[i].getValue() + "\n");
		}

		return list;
	}

	public String displayWorld()
	{
		String world = "";
		world += ("Welcome to The World\n\n");

		world += ("Total Node Type Count = " + getNodeCount() + "\n\n");
		world += (outputNodeValue());
		world += "\n\n";

		world += ("Total Vertex Count = " + getVertexCount() + "\n\n");
		world += (outputVertexDetail());
		world += ("\n\n");

		world += ("\n\nVertex: Edge: EdgeLength:\n\n");
		world += (outputEdge());
		
		return world;
	}

     private String outputVertexDetail() //got problem
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

	private int getNodeCount() //total node 
	{
		return nodeCount;
	}

	private String outputEdge() 
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
