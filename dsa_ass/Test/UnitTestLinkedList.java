import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UnitTestLinkedList
{
	DSALinkedList l = new DSALinkedList();

	@Test
	public void testInsertFirst()
	{
		Object obj;
		l.insertFirst(1);
		l.insertFirst(2);
		obj = l.peekFirst();
		assertEquals(2,obj);
	}

	@Test
	public void testInsertLast()
	{
		Object obj;
		l.insertLast(1);
		l.insertLast(2);
		obj = l.peekFirst();
		assertEquals(1,obj);
	}

	@Test
	public void testPeekFirst()
	{
		try
		{
			l.peekFirst();
			assert false;
		}
		catch(IllegalArgumentException e)
		{

		}

		Object obj;
		l.insertFirst(1);
		l.insertFirst(2);
		obj = l.peekFirst();
		assertEquals(2,obj);
	}

	@Test
	public void testPeekLast()
	{
		try
		{
			l.peekLast();
			assert false;
		}
		catch(IllegalArgumentException e)
		{

		}

		Object obj;
		l.insertLast(1);
		l.insertLast(2);
		obj = l.peekFirst();
		assertEquals(1,obj);
	}

	@Test
	public void testRemoveFirst()
	{
		try
		{
			l.removeFirst();
			assert false;
		}
		catch(IllegalArgumentException e)
		{

		}
		Object obj;
		l.insertLast(1);
		l.insertLast(2);
		obj = l.removeFirst();
		assertEquals(1, obj);

	}

	@Test
	public void testRemoveLast()
	{
		try
		{
			l.removeLast();
			assert false;
		}
		catch(IllegalArgumentException e)
		{

		}
		
		Object obj;
		l.insertLast(1);
		l.insertLast(2);
		obj = l.removeLast();
		assertEquals(2, obj);
	}

	@Test
	public void testToString()
	{
		String obj;
		l.insertLast(1);
		l.insertLast(2);
		obj = l.toString();
		assertEquals("1 2 ", obj);
	}
}