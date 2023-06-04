//Code refers to Lee Wei Hang 20458472 lab 3

public class DSAStack
{
	int count;
	boolean empty, full;
	Object topValue;
	Object [] stack;
	public static int DEFAULT_CAPACITY = 100;

	public DSAStack()
	{
		stack = new Object[DEFAULT_CAPACITY];
		count = 0;
	} 

	public DSAStack(int maxCapacity)
	{
		stack = new Object[maxCapacity];
		count = 0;
	}

	public int getCount()
	{
		return count;
	}

	public boolean isEmpty()
	{
		if(count == 0)
		{
			 empty = true;
		}
		else
		{
			empty = false;
		}

		return (count == 0);
	}

	public boolean isFull()
	{
		if(count == stack.length)
		{
			full = true;
		}
		else
		{
			full = false;
		}

		return full;
	}

	public void push(Object value)
	{
		if(isFull())
		{
			throw new StackOverflowError("The stack is full");
		}
		else
		{
			stack[count] = value; //first input stack[0] = 23 
			count++;			  //increase the count
		}
	}

	public Object pop()
	{
		topValue = top(); //call top()
		count--;
		return topValue;
	}

	public Object top()
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("The stack is empty");
		}
	
		return (stack[count - 1]); //count always +1 after push, to get top we have to -1
	}

	public String printStack()
	{
		String totalStack = "";

        for(int i = 0; i < count; i++)
        {
            totalStack += (stack[i] + " ");
        }
        return totalStack;

	}

	
}