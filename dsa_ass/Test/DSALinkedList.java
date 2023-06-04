//Code refers to Lee Wei Hang 20458472 lab 4

import java.util.*;
import java.io.Serializable;
public class DSALinkedList implements Iterable, Serializable
{
	private class DSAListNode implements Serializable //inner class
	{
		private Object value;
		private DSAListNode next, previous;

		public DSAListNode(Object inValue) 
		{
			setValue(inValue);
			next = null;
			previous = null;
		}

		public Object getValue()
		{
			return value;
		}

		public DSAListNode getNext()
		{
			return next;
		}

		public DSAListNode getPrevious()
		{
			return previous;
		}

		public void setValue(Object inValue)
		{
			value = inValue;
		}

		public void setNext(DSAListNode inNext)
		{
			next = inNext;
		}

		public void setPrevious(DSAListNode inPrevious)
		{
			previous = inPrevious;
		}

	} //list node inner class ends

	public Iterator iterator() //returns Iterator (objects that inherits from the Java Iterator interface)
	{
		return new DSALinkedListIterator(this);
	}

	private class DSALinkedListIterator implements Iterator
	{
		private DSAListNode iterNext;

		public DSALinkedListIterator(DSALinkedList theList)
		{
			iterNext = theList.head;
		}

		//Iterator interface implementation

		public boolean hasNext()
		{
			return (iterNext != null);
		}

		public Object next() //can only use once due to set next 
		{
			Object value;

			value = iterNext.getValue();
			iterNext = iterNext.getNext();

			return value;
		}

		public void remove()
		{
			throw new UnsupportedOperationException("Not supported");
		}
	} //iterator inner class ends

	Object value;
	DSAListNode head, tail, frontNode, lastNode;

	public DSALinkedList()
	{
		head = null;
		tail = null;
	}

	private boolean isEmpty()
	{
		return (head == null);
	}

	public void insertFirst(Object inValue) //insert to first, set next to second, set previous to null (default)
	{
		DSAListNode newValue = new DSAListNode(inValue);

		if(isEmpty())
		{
			head = newValue;
			tail = newValue;
		}
		else //if it has other element, insert to head previous
		{
			frontNode = newValue;
			head.setPrevious(frontNode);
			frontNode.setNext(head);
			head = frontNode;
		}
	}

	public void insertLast(Object inValue) 
	{
		DSAListNode newValue = new DSAListNode(inValue);

		if(isEmpty())
		{
			head = newValue; 
			tail = newValue;
		}
		else
		{
			lastNode = newValue;
			tail.setNext(lastNode); //last node is the new tail
			lastNode.setPrevious(tail); //set lsat node previous to old tail
			tail = lastNode;
		}
	}

	public Object peekFirst()
	{
		if (isEmpty()) 
		{
			throw new IllegalArgumentException("List is empty");
		}
		else
		{
			value = head.getValue();
		}

		return value;
	}

	public Object peekLast()
	{
		if (isEmpty()) 
		{
			throw new IllegalArgumentException("List is empty");
		}
		else
		{
			value = tail.getValue();
		}

		return value;
	}

	public Object removeFirst()
	{
		if (isEmpty()) 
		{
			throw new IllegalArgumentException("List is empty");
		}
		else
		{
			value = head.getValue(); //output the first element to be removed

			if(head.getNext() != null)
			{
				frontNode = head.getNext();
				head.setNext(null);
				head = frontNode;
				head.setPrevious(null);
			}
			else
			{
				head = null;
			}
		}

		return value;
	}

	public void removeSelect(String vertexRemove)
	{
		if (isEmpty()) 
		{
			throw new IllegalArgumentException("List is empty");
		}
		else
		{
			DSAGraphVertex vertexLL;
			DSAListNode node;
			String nodeString; //node string = stored object's string
			int index = 0;
			boolean found = false;

			if(head.getNext() != null && found == false)
			{
				index++;
				do
				{
					node = head.getNext();//get the node, node stores object
					vertexLL = (DSAGraphVertex)node.getValue(); //convert the object to vertex
					nodeString = vertexLL.getLabel();

					if(vertexRemove.equals(nodeString)) //node is the same
					{
						found = true;
					}
				}while(found == false && head.getNext() != null);

				if(found == true)//found the node to be delete
				{

				}
			}
			else
			{
				head = null;
			}
		}

	}

	public Object removeLast()
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("List is empty");
		}
		else
		{
			value = tail.getValue();

			if(tail.getPrevious() != null)
			{
				lastNode = tail.getPrevious();
				tail.setPrevious(null);
				tail = lastNode;
				tail.setNext(null);
			}
			else
			{
				tail = null;
			}
		}

		return value;
	}

	public String toString()
	{
		String list = "";
		DSAListNode temp = head;

		while(temp != null)
		{
			list += (temp.getValue() + " ");
			temp = temp.getNext();
		}
		return list;
	}


	

}