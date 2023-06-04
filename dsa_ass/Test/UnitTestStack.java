import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UnitTestStack
{
	DSAStack s = new DSAStack(5);
	@Test
	public void testGetCount()
	{
		s.push(1);
		s.push(2);
		assertEquals(2, s.getCount());
	}

	@Test
	public void testIsEmpty()
	{
		assertTrue(s.isEmpty());

		s.push(1);
		assertFalse(s.isEmpty());
	}	

	@Test
	public void testIsFull()
	{
		assertFalse(s.isFull());

		s.push(1);
		s.push(2);
		s.push(1);
		s.push(2);
		s.push(1);
		assertTrue(s.isFull());
	}

	@Test
	public void testPush()
	{
		String st;
		s.push(1);
		s.push(2);
		st = s.printStack();
		assertEquals("1 2 ", st);
	}

	@Test
	public void testPop()
	{
		try
		{
			s.pop();
			assert false;
		}
		catch(IllegalArgumentException e)
		{

		}
		String st;
		s.push(1);
		s.push(2);
		s.pop();
		st = s.printStack();
		assertEquals("1 ", st);
	}

	@Test
	public void testTop()
	{
		s.push(1);
		s.push(2);
		assertEquals(2, s.top());
	}

	@Test
	public void testPrintStack()
	{
		
		String st;
		s.push(1);
		s.push(2);
		st = s.printStack();
		assertEquals("1 2 ", st);
	}
}