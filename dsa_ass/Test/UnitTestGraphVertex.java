import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UnitTestGraphVertex
{
    DSAGraphVertex v = new DSAGraphVertex("A", 1);

    
    @Test
	public void testSetLabel()
	{
		v.setLabel("B");
		assertEquals("B",v.getLabel());
	}

	@Test
	public void testGetLabel()
	{
		v.setLabel("B");
		assertEquals("B",v.getLabel());
	}

	@Test
	public void testSetValue()
	{
		v.setValue(10);
		assertEquals(10,v.getValue());
	}

	@Test
	public void testGetValue()
	{
		v.setValue(10);
		assertEquals(10,v.getValue());
	}

	@Test
	public void testSetNodeType()
	{
		v.setNodeType("-");
		assertEquals("-", v.getNodeType());
	}

	@Test
	public void testGetNodeType()
	{
		v.setNodeType("-");
		assertEquals("-", v.getNodeType());
	}

	@Test
	public void testSetVisited()
	{
		v.setVisited();
		assertTrue(v.getVisited());
	}

	@Test
	public void testClearVisited()
	{
		v.clearVisited();
		assertFalse(v.getVisited());
	}

	@Test
	public void testGetVisited()
	{
		v.setVisited();
		assertTrue(v.getVisited());

		v.clearVisited();
		assertFalse(v.getVisited());
	}

	@Test
	public void testToString()
	{
		assertEquals("A", v.toString());
	}
}