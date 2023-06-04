import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UnitTestGraph
{
	
	DSAGraph g = new DSAGraph();
	

	@Test
	public void testAddVertex() throws IllegalArgumentException
	{
		//test add vertex
		g.addVertex("one",1);
		assertEquals("one" ,g.getVertex("one").toString());

		//test add same vertex 
		try
		{
			g.addVertex("one",1);
			assert false;
		}
		catch(IllegalArgumentException e)
		{

		}
	}

	@Test
	public void testAddEdge()
	{
		g.addVertex("one",1);
		g.addVertex("two",1);
		g.addEdge("one","two", "1");
		assertEquals("two " ,g.getAdjacent("one").toString());

		//test add ege with non-exist vertex
		try
		{
			g.addEdge("one","five", "1");
			assert false;
		}
		catch(IllegalArgumentException e)
		{

		}

		//test add repeat edge
		try
		{
			g.addEdge("one","two", "1");	
			assert false;
		}
		catch(IllegalArgumentException e)
		{

		}
	}

	@Test
	public void testAddEdgeType()
	{
		//test add edge type
		g.addEdgeType("-","1");
		assertEquals(1,g.getEdgeTypeValue("-"));
	}
	
	@Test
	public void testAddEdgeValue()
	{
		DSAGraphVertex v1,v2;
		
		g.addVertex("A",1);
		g.addVertex(("B"), 2);

		g.addEdgeType("-","1");

		g.addEdge("A","B","-");
		
		v1 = g.getVertex("A");
		v2 = g.getVertex("B");

		assertEquals(1 ,g.getEdgeValue(v1, v2));
	}

	@Test
	public void testGetEdgeValue()
	{
		DSAGraphVertex v1,v2;
		
		g.addVertex("A",1);
		g.addVertex(("B"), 2);

		g.addEdgeType("-","1");

		g.addEdge("A","B","-");
		
		v1 = g.getVertex("A");
		v2 = g.getVertex("B");

		assertEquals(1 ,g.getEdgeValue(v1, v2));
	}

	@Test
	public void testHasVertex() 
	{
		g.addVertex("one",1);
		assertTrue(g.hasVertex("one"));
		assertFalse(g.hasVertex("two"));
	}

	@Test
	public void testGetVertex() 
	{
		g.addVertex("one",1);
		assertEquals("one" ,g.getVertex("one").toString());
	}

	@Test
	public void testGetAdjacent()
	{
		g.addVertex("one",1);
		g.addVertex("two",1);
		g.addEdge("one","two", "1");
		assertEquals("two " ,g.getAdjacent("one").toString());
	}

	@Test
	public void testDepthFirstSearch()
	{
		g.addVertex("A",1);
		g.addVertex("B",1);
		g.addVertex("C",1);
		g.addVertex("D",1);
		g.addVertex("E",1);
		g.addVertex("F",1);
		g.addEdge("A","B", "1");
		g.addEdge("A","C", "1");
		g.addEdge("A","D", "1");
		g.addEdge("D", "E", "1");
		g.addEdge("D", "F", "1");
		g.addEdge("B", "F", "1");

		assertEquals("{ (A,B)(B,F)(A,C)(A,D)(D,E) }", g.depthFirstSearch());

	}

	@Test
	public void testGetVertexCount()
	{
		g.addVertex("A",1);
		g.addVertex("B",1);
		g.addVertex("C",1);
		assertEquals(3,g.getVertexCount());
	}

	@Test
	public void testGetTotalEdgeCount()
	{
		g.addVertex("A",1);
		g.addVertex("B",1);
		g.addVertex("C",1);

		g.addEdge("A","B", "1");
		g.addEdge("A","C", "1");
		g.addEdge("B","C", "1");
		

		assertEquals(3,g.getTotalEdgeCount());
	}

	@Test
	public void testDisplayMatrix()
	{

	}

	@Test
	public void testDeleteVertex()
	{
		DSAGraphVertex c;
	
		g.addVertex("A",1);
		g.addVertex("B",1);
		g.addVertex("C",1);

		c = g.getVertex("C");
		g.deleteVertex(c);

		assertEquals("A B ", g.outputVertex());
	}

	@Test
	public void testIsAdjacent()
	{
		g.addVertex("A",1);
		g.addVertex(("B"), 2);
		g.addVertex(("C"), 2);

		g.addEdgeType("-","1");

		g.addEdge("A","B","-");

		assertTrue(g.isAdjacent("A", "B"));
		assertFalse(g.isAdjacent("A", "C"));
	}

	@Test
	public void testUpdateEdgeValue() //this is inside graph vertex testing 
	{
		DSAGraphVertex v1,v2;

		g.addVertex("A",1);
		g.addVertex(("B"), 2);

		g.addEdgeType("o", "2");
		g.addEdgeType("-","1");

		g.addEdge("A","B","-");
		
		v1 = g.getVertex("A");
		v2 = g.getVertex("B");

		assertEquals(1 ,g.getEdgeValue(v1, v2));
		
		int edgeValue = g.getEdgeTypeValue("o");
		v1.updateEdgeValue("B", edgeValue);
		assertEquals(2 ,g.getEdgeValue(v1, v2));
	}

	@Test
	public void testUpdateNodeValue() //also in graph vertex testing
	{

	}
}