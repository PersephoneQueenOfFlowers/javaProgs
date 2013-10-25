package arraystack;

/**
 *
 * @author sethschoenfeld
 */
public class ArrayStack<T> implements StackInterface
{
//    private static boolean isEmpty;
    String name;
    T[] internalArray;
    private T T;

    ArrayStack()
    {
        internalArray = (T[]) new Object[1];
    }
    ArrayStack(Object T)
    {
        internalArray = (T[]) new Object[1];
    }   
    
    @Override
    public void push(Object newEntry)
    {
        if(isEmpty())
        {
            internalArray[0] = (T) newEntry;
        }
        else
        {
            add1toArray();
            internalArray[0] = (T) newEntry;
        }
    }
    @Override
  public T pop()
  {
      T removed = internalArray[0];
      internalArray[0] = null;
     return removed;
  }
  
    @Override
  public T peek()
  {
      for(int i = 0; i < internalArray.length; i++)
      {
          if(internalArray[i] != null)
          {
               return internalArray[i];        
          }
      }
      return null;
  }
 
    @Override
  public boolean isEmpty()
  {
      for(int i = 0; i < internalArray.length; i++)
      {
          if(internalArray[i] != null) 
          {
              return false;
          }   
      }
      return true;
  }
 
    @Override
  public void clear()
  {
    for(int i = 0; i < internalArray.length; i++)
    {internalArray[i] = null;}
    
  }
    
  private void add1toArray()
  {
      T[] oldArray = internalArray;
      int oldSize = oldArray.length;
      
      internalArray = (T[]) new Object[oldSize + 1];//add one to the array
      
      //copy entries
      for(int i = 1; i < oldSize; i++) 
      {
          internalArray[i] = oldArray[i];
      }
  }
    
    
    
    
    public static void main(String[] args) 
    {
	System.out.println("Create a stack: ");
	StackInterface<String> myStack = new ArrayStack<String>();
	System.out.println("\nisEmpty() returns " + myStack.isEmpty() + "\n");
		
        System.out.println("Add to stack to get\n" +
		                   "Joe Jane Jill Jess Jim");
	myStack.push("Jim");
	myStack.push("Jess");
	myStack.push("Jill");
	myStack.push("Jane");
	myStack.push("Joe");

	System.out.println("\nisEmpty() returns " + myStack.isEmpty());

	System.out.println("\n\nTesting peek and pop:\n");
	while (!myStack.isEmpty())
	{
		String top = myStack.peek();
		System.out.println(top + " is at the top of the stack.");
			
		top = myStack.pop();
		System.out.println(top + " is removed from the stack.\n");
	} // end while

	System.out.print("\nThe stack should be empty: ");
	System.out.println("isEmpty() returns " + myStack.isEmpty() + "\n\n");

	System.out.println("Add to stack to get\n" +
		                   "Jim Jess Joe\n");
	myStack.push("Joe");
	myStack.push("Jess");
	myStack.push("Jim");
		
	System.out.println("\nTesting clear:");
	myStack.clear();
			
	System.out.print("\n\nThe stack should be empty: ");
        System.out.println("\nisEmpty() returns " + myStack.isEmpty() + "\n\n");

	System.out.println("myStack.peek() returns " +  myStack.peek());
	System.out.println("myStack.pop()  returns " +  myStack.pop() + "\n");
    }

}
