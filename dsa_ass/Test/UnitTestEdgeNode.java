import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UnitTestEdgeNode
{
    EdgeNode e = new EdgeNode("A", "0");

    
    @Test
	public void testSetValue()
	{
        e.setValue(2);
        assertEquals(2,e.getValue());
	}

    @Test
	public void testGetValue()
	{
        e.setValue(2);
        assertEquals(2,e.getValue());
	}

    @Test
    public void testGetLabel()
    {
        assertEquals("A", e.getLabel());
    }

    @Test
    public void testToString()
    {
        assertEquals("A", e.toString());
    }
}