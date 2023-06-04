import java.util.*;
import java.io.*;
public class gameofcatz
{
	public static void main(String[]args)
	{
		if(args.length == 0)
		{
			System.out.println("Usage of the command line\n");
			System.out.println("java gameofcatz -i            --- for interactive mode");
			System.out.println("java gameofcatz -s <filename> --- for simulation mode\n");
			System.out.println("Note: file name must include .txt");
		}

		if(args.length > 0)
		{
			if(args[0].equals("-i"))
			{
				int choice, nodeValue, totalCount;
				DSAGraph graph = new DSAGraph();
				DSAGraphVertex vertex, firstVertex;
				String vertexString, nodeType;
				String msg, vertexStringOne, vertexStringTwo, edgeType;
				String graphMatrix [][];
				boolean vertexFound = false;
				boolean read = false;
				boolean save;
				boolean generate = false;
				EdgeNode foundNode;

				do
				{
					menu();		
					choice = userInputInt(10);
					switch(choice)
					{
						case 1:							
							if(read == false)
							{
								graph = readFile("gameofcatz.txt", graph);
								read = true;
							}
						
								break;

						case 2: //node operations
							if(read == true)
							{
								vertex = null;
								vertexFound = false;
							
								do
								{	
									vertexMenu(vertexFound, vertex);
									choice = userInputInt(5);
									switch(choice) //inner switch
									{
										case 1: //find vertex
			
											msg = "(Enter vertex to search)";
											vertexString = inputVertexSearch(msg, graph);
										
											vertex = graph.getVertex(vertexString);
											vertexFound = true;
											
											break;

										case 2://insert vertex

											msg = "Enter vertex to add (String) ";
											vertexString = inputVertexAdd(msg, graph);
											
											msg = "Enter node type";
											nodeType = inputNodeSearch(msg,graph); 
											nodeValue = graph.getNodeValue(nodeType); // get value of the type 

											graph.addVertex(vertexString, nodeValue);
											System.out.println("Add vertex done");
											break;

										case 3://delete vertex

											if(vertex == null) //if not yet find
											{
												System.out.println("\nPlease find a node.");
											}
											else
											{
												graph.deleteVertex(vertex);
												System.out.println("Node delete complete.\n");
											}
											break;

										case 4://update vertex
											if(vertex == null) //if not yet find
											{
												System.out.println("\nPlease find a node.");
											}
											else
											{
												msg = "(Enter the node type for update) ";
												nodeType = inputNodeSearch(msg, graph);
												nodeValue = graph.getNodeValue(nodeType);
												vertex.setValue(nodeValue);

												System.out.println("Update vertex done");
												
											}
											break;
									}
								}while(choice != 5);
							}
							else
							{
								System.out.println("Read file first");
							}
							break;
						
						case 3:// edge operations
							if(read == true)
							{
								msg = "";
								vertexStringOne = "";
								vertexStringTwo = "";
								edgeType = "";

								boolean edgeFound = false;
								int edgeValue;
								do
								{
									edgeMenu(edgeFound, vertexStringOne, vertexStringTwo);
									choice = userInputInt(5);
									switch(choice)
									{
										case 1: //find node
											msg = "(Enter first vertex (from):)";
											vertexStringOne = inputVertexSearch(msg, graph);

											msg = "Enter second vertex (to):";
											vertexStringTwo = inputVertexSearch(msg, graph);
											
											if(graph.isAdjacent(vertexStringOne, vertexStringTwo) == false) // nodes are not neighbour
											{
												System.out.println("These 2 vertex are not linked together.");
											}
											else
											{
												edgeFound = true;
												System.out.println("Find edge done");
											}
											break;

										case 2://insert edge

											msg = "(Enter first vertex (from):)";
											vertexStringOne = inputVertexSearch(msg, graph);

											msg = "(Enter second vertex (to):)";
											vertexStringTwo = inputVertexSearch(msg, graph);							
											
											//
											if(graph.isAdjacent(vertexStringOne, vertexStringTwo) == true) // nodes are already neighbour
											{
												System.out.println("These 2 nodes have already been linked.");
											}
											else
											{
												setEdge(msg, graph, vertexStringOne, vertexStringTwo);
												System.out.println("Edge set done");
											}	
											break;

										case 3://delete edge based on find vertex
											if(edgeFound == false)
											{
												System.out.println("Please find an edge.");
											}
											else // do deletion
											{
												firstVertex = graph.getVertex(vertexStringOne);
												firstVertex.deleteEdge(vertexStringTwo);
												System.out.println("Delete edge done");
											}

											break;

										case 4://update edge (weight) based on find
											if(edgeFound == false)
											{
												System.out.println("Please find an edge.");
											}
											else //update edge's value
											{
												firstVertex = graph.getVertex(vertexStringOne);
												System.out.println("Enter the edge type * Invalid edge type will result in 0 *");

												edgeType = userInputString();
												edgeValue = graph.getEdgeTypeValue(edgeType);
												firstVertex.updateEdgeValue(vertexStringTwo, edgeValue);
												System.out.println("Edge value updated");
											}
											
											break;

										case 5://exit to menu
											break;
									}
								}while(choice != 5);
							}
							else
							{
								System.out.println("Read file first");
							}
							break;

						case 4:// parameter tweaks
							if(read == true)
							{
								graph.outputNodeValue();
								System.out.println("Select node type to update it's value\n");
								
								msg = "Node Type:";
								nodeType = inputNodeSearch(msg, graph);
								
								msg = "Enter Value";
								nodeValue = userInput(msg);

								foundNode = graph.getNodeType(nodeType); //return EdgeNode class
								foundNode.setValue(nodeValue); //done setting value

								graph.updateNodeValue(nodeType, nodeValue);
								System.out.println("Parameter update done");
							}
							else 
							{
								System.out.println("Read file first");
							}
							break;

						case 5:// display graph , weighted matrix
							if(read == true)
							{
								graphMatrix = graph.displayMatrix();
								totalCount = graph.getVertexCount();
								for(int i = 0; i < totalCount; i++) //print out
								{
									for(int j = 0; j < totalCount; j++)
									{
										System.out.print(graphMatrix[i][j] + " ");
									}
									System.out.println("\n");
								}

								save = saveDecision();

								if(save) //user wants to save
								{		
									writeMatrix(graphMatrix, totalCount);
									System.out.println("\nSaved to GraphMatrix.txt\n ");
								}
							}
							else
							{
								System.out.println("Read file first");
							}
							break;
						
						case 6:// display world
							if(read == true)
							{
								System.out.println(graph.displayWorld());

								save = saveDecision();
								if(save) //user wants to save
								{
									writeWorld(graph);
									System.out.println("\nSaved to World.txt\n ");
								}
							}
							else
							{
								System.out.println("Read file first");
							}
							
							break;

						case 7:// generate routes
							if(read == true)
							{
								graph.depthFirstSearch();
								graph.displayGraphPath(); //remove this
								System.out.println("Generate routes done");
								generate = true;
							}
							else
							{
								System.out.println("Read file first");
							}
							break;

						case 8:// display routes, option to save
							if(read == true && generate == true)
							{
								System.out.println(graph.displayGraphPath());

								save = saveDecision();
								if(save) //user wants to save
								{
									writeRoutes(graph);
									System.out.println("\nSaved to Routes.txt\n ");
								}
							}
							else
							{
								if(read == false)
								{
									System.out.println("Read file first");
								}
								if(generate == false)
								{
									System.out.println("Generate routes first");
								}
							}

							break;
						
						case 9:// save network to text file
							
							if(read == true)
							{	
								graphMatrix = graph.displayMatrix();
								totalCount = graph.getVertexCount();
								writeNetwork(graph, graphMatrix, totalCount);

								System.out.println("\nSaved to Network.txt\n ");
								
								
							}
							else
							{
								System.out.println("Read a file");
							}
							break;
					
					}
				}while(choice != 10);
			}
			
			else if(args[0].equals("-s"))
			{
				if(args.length == 2)
				{
					DSAGraph graph = new DSAGraph();
					String fileName;
					fileName = args[1];
					graph = readFile(fileName, graph);

					if(graph.getVertexCount() != 0)
					{
						graph.depthFirstSearch();
						System.out.println(graph.displayGraphPath());
						writeRoutes(graph);
						System.out.println("\nSaved to Routes.txt\n ");
					}
					else
					{
						System.out.println("File doesn't exist or file format is not correct ");
					}


				}
				else
				{
					System.out.println("Enter an argument for the file.");
					System.out.println("java gameofcatz -s <filename> \n");
					System.out.println("Note: file name must include .txt");
				}
			}
		}
				
				
	}

	public static void menu()
	{
		System.out.println("\n(1) Load input file");
		System.out.println("(2) Node operations(find, insert, delete, update)");
		System.out.println("(3) Edge operations(find,add,remove,update)");
		System.out.println("(4) Parameter tweaks(adjust mapping of codes to penalty/boost features, see sample input file)");
		System.out.println("(5) Display graph(weighted adjacency matrix,option to save)");
		System.out.println("(6) Display world (your choice of representation, does not need to be graphical, should include counts of features, option to save)");
		System.out.println("(7) Generate routes");
		System.out.println("(8) Display routes(ranked,option to save)");
		System.out.println("(9) Save network");
		System.out.println("(10) Exit interactive testing environment\n");
		
	}

	public static void vertexMenu(boolean found, DSAGraphVertex v)
	{
		if(found == false)
		{
			System.out.println("\nNode Operation:");
			System.out.println("\n1)Find\n2)Insert\n3)Delete\n4)Update\n5)Exit to Main Menu");
		}
		else
		{
			System.out.println("\nNode Operation on " + v.getLabel() + " :");
			System.out.println("\n1)Find\n2)Insert\n3)Delete\n4)Update\n5)Exit to Main Menu");
		}
	}

	public static void edgeMenu(boolean found, String firstNode, String secondNode) // to be modified
	{
		if(found == false)
		{
			System.out.println("\nEdge Operation:");
			System.out.println("\n1)Find\n2)Insert\n3)Delete\n4)Update\n5)Exit to Main Menu");
		}
		else
		{
			System.out.println("\nEdge Operation on " + firstNode + " and " + secondNode + " :");
			System.out.println("\n1)Find\n2)Insert\n3)Delete\n4)Update\n5)Exit to Main Menu");
		}
	}

	public static String inputVertexAdd(String msg, DSAGraph graph)
	{
		String vertexString;
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println(msg);
			vertexString = userInputString();

			if(graph.hasVertex(vertexString))
			{
				System.out.println("Vertex exist. Please enter again");
			}

		}while(graph.hasVertex(vertexString)); //if vertex exist, input again

		return vertexString;

	}

	public static String inputVertexSearch(String msg, DSAGraph graph)
	{
		String vertex = "";
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println(msg);
			vertex = userInputString();
			if(graph.getVertex(vertex) == null)
			{
				System.out.println("Vertex doesn't exist. Please enter again.");
			}
		}while(graph.getVertex(vertex) == null); //if vertex not exist

		return vertex;
	}

	public static String inputNodeSearch(String msg, DSAGraph graph)
	{
		String node = "";
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.print("Node Type: ");
			node = userInputString();

			if(!graph.hasNodeType(node))
			{
				System.out.println("Node doesn't exist. Please enter again");
			}

		}while(graph.hasNodeType(node) == false); //if node doesn't exist

		return node;
	}

	public static void setEdge(String msg, DSAGraph graph, String vertexStringOne, String vertexStringTwo)
	{
		DSAGraphVertex firstVertex, secondVertex;
		String edgeType;
		int edgeValue;

		if(graph.isAdjacent(vertexStringOne, vertexStringTwo) == true) // nodes are already neighbour
		{
			System.out.println("These 2 nodes have already been linked.");
		}
		else
		{
			firstVertex = graph.getVertex(vertexStringOne);
			secondVertex = graph.getVertex(vertexStringTwo);

			System.out.println("Enter edge type  * Invalid edge type will result in 0 *");
			edgeType = userInputString();

			edgeValue = graph.getEdgeTypeValue(edgeType);
			firstVertex.addEdge(secondVertex, edgeValue);
			System.out.println("Adding edge done");
		}	
	}

	public static int userInputInt(int max)
    {
    	Scanner sc = new Scanner(System.in);
    	int input;
    	do
    	{
	    	System.out.print("Selection: ");
	    	input = sc.nextInt();
    	}while(input < 1 || input > max);

    	return input;

    }

	public static int userInput(String msg)
	{
		Scanner sc = new Scanner(System.in);
		int input;

		System.out.println(msg);
		System.out.print("Selection: ");
		input = sc.nextInt();
    	

    	return input;
	}

    public static String userInputString()
    {
    	Scanner sc = new Scanner(System.in);
		String input;
		do
    	{
	    	System.out.print("Selection: ");
	    	input = sc.nextLine();
    	}while(input == null);

    	return input;
    }

	public static DSAGraph readFile(String fileName, DSAGraph graph) //return graph g
	{

        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line, name, valueString, edgeLength;
        int value;
        String [] token;
        try
        {
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            
            
            while(line != null)
            {
                try
                {
                    token = line.split(" ");
                    
                 	
                 	if(!token[0].equals("#"))
                 	{
                 		//System.out.println("Token[0] = " + token[0]);
	                	if(token[0].equals("Ncode")) //code type (Food, Dog Toy)
	                	{
	                		name = token[1];
	                		valueString = token[2];

	                		graph.addNodeType(token[1], token[2]);
	                		
	                	}
	                	else if(token[0].equals("Node")) //vertex and it's value
	                	{
	                		name = token[1];
	                		value = graph.getNodeValue(token[2]); //token[2] = node type
	                		graph.addVertex(name, value);
							DSAGraphVertex gv = graph.getVertex(name);
							gv.setNodeType(token[2]);
	                	}
	                	else if(token[0].equals("Ecode"))//get edge's length value
	                	{
	                		name = token[1];
	                		valueString = token[2];

	                		graph.addEdgeType(name,valueString);
	                	}
	                	else if(token[0].equals("Edge")) //Edge between 2 nodes and it's edge value
	                	{
	                		edgeLength = token[3];
	                		graph.addEdge(token[1], token[2], edgeLength); 
	                	}
	                	else if(token[0].equals("Start")) //start node
	                	{
	                		graph.setStart(token[1]);
	                	}
	                	else if(token[0].equals("Target"))//end node
	                	{
	                		graph.setTarget(token[1]);
	                	}    
	                 }        
                }   	
                catch(Exception e)
                {
                    e.printStackTrace(); // print out errors
                }
                line = bufRdr.readLine();
            }
            fileStream.close();
        }
        catch (IOException e)
        {
            if(fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch(IOException ex2)
                {
                    System.out.println("Error in fileProcessing" + e.getMessage());
                }
                
            }
        }

        return graph;
	}

	public static void writeMatrix(String [][] graphMatrix, int totalCount)
	{
		FileOutputStream fileStream = null;
        PrintWriter pw;
        try
        {
            fileStream = new FileOutputStream("GraphMatrix.txt");
            pw = new PrintWriter(fileStream);
    		totalCount += 1;
    		for(int i = 0; i < totalCount; i++) //print out
			{
				for(int j = 0; j < totalCount; j++)
				{
					pw.print(graphMatrix[i][j] + " ");
				}
				pw.println("\n");
				pw.println("\n");
			}

            pw.close();
           
        }
        catch(IOException e)
        {
            
        }
	}

	public static void writeRoutes(DSAGraph graph)
	{
		FileOutputStream fileStream = null;
        PrintWriter pw;
        try
        {
            fileStream = new FileOutputStream("Routes.txt");
            pw = new PrintWriter(fileStream);
    		
    		pw.print(graph.displayGraphPath());
			pw.println("\n");

            pw.close();
           
        }
        catch(IOException e)
        {
            
        }
	}

	public static void writeWorld(DSAGraph graph)
	{
		FileOutputStream fileStream = null;
        PrintWriter pw;
        try
        {
            fileStream = new FileOutputStream("World.txt");
            pw = new PrintWriter(fileStream);
    		
    		pw.print(graph.displayWorld());
			pw.println("\n");

            pw.close();
           
        }
        catch(IOException e)
        {
            
        }
	}

	public static void writeNetwork(DSAGraph graph, String [][] graphMatrix, int totalCount)
	{
		FileOutputStream fileStream = null;
        PrintWriter pw;
        try
        {
            fileStream = new FileOutputStream("Network.txt");
            pw = new PrintWriter(fileStream);
    		totalCount += 1;
			pw.println("Graph Network ");
			pw.println("\n");
			pw.println("Graph Matrix");
    		for(int i = 0; i < totalCount; i++) //print out
			{
				for(int j = 0; j < totalCount; j++)
				{
					pw.print(graphMatrix[i][j] + " ");
				}
				pw.println("\n");

			}

			pw.println("Graph World");
			pw.print(graph.displayWorld());
			pw.println("\n");

			pw.println("Graph path");
			pw.print(graph.displayGraphPath());
			pw.println("\n");

			

            pw.close();
           
        }
        catch(IOException e)
        {
            
        }
	}

	public static boolean saveDecision()
	{
		String choice;
		boolean decide = false;
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.print("Save to a file (Y/N) :");
			choice = sc.nextLine();
		}while(!choice.equals("y") && !choice.equals("Y") && !choice.equals("n") && !choice.equals("N")  );

		if(choice.equals("y") || choice.equals("Y"))
		{
			decide = true;
		}
		
		return decide;
	}

    

}