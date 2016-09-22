import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderedCollection<T extends Comparable<T>>  {
	
	
	ArrayList<T> tokenCollection = new ArrayList<T>();
	
	//get the smallest object 
	public T findMin ()
	{
		if(!tokenCollection.isEmpty())
		{
			T figure = tokenCollection.get(0);
			for(int i = 1; i < tokenCollection.size() - 1; i++)
			{
				if(figure.compareTo(tokenCollection.get(i)) > 0)
					figure = tokenCollection.get(i);
			}
			return figure;
		}
		
		return null;
	}
	
	//get the largest object 
	public T findMax()
	{
		if(!tokenCollection.isEmpty())
		{
			T figure = tokenCollection.get(0);
			for(int i = 1; i < tokenCollection.size() - 1; i++)
			{
				if(figure.compareTo(tokenCollection.get(i)) < 0)
					figure = tokenCollection.get(i);
			}
			return figure;
		}
		
		return null;
	}//end findMax
	
	public boolean isEmpty ()
	{
		if(tokenCollection.size()==0)
			return true;
		
		return false;
	}//end isEmpty
	
	public int makeEmpty()
	{
		tokenCollection.clear();
		
		return tokenCollection.size();
	}//end makeEmpty
	
	public int insert(T Element)
	{
		tokenCollection.add(Element);
		
		return tokenCollection.size();
	}//end insert
	
	public int remove (T Element)
	{
		if(!isEmpty()){
		tokenCollection.remove(Element);
		}
		
		return tokenCollection.size();
	}//end remove
	

}//end OrderedCollection class