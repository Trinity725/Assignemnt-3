
import java.util.ListIterator;
import java.util.Comparator;


public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	Comparator<T> compareList;
	
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		this.compareList = compareableObject;
	}
	
	/**
	 * Inserts the specified element at the correct position in the sorted list
	 * Notice we can insert the same element several times
	 * 
	 * @param data
	 */
	public void add (T data)
	{
		Node newNode = new Node(data);
		
		if(size == 0)
		{
			head = newNode;
			tail = newNode;
		} else if (compareList.compare((T)head.data, data) > 0)
		{
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
			
		} else if (compareList.compare((T)tail.data, data) < 0)
		{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;

		}else
		{
			Node tempNode = head;
			
			while (tempNode != null) 
			{
				if (compareList.compare((T) tempNode.data, data) < 0 && compareList.compare((T) tempNode.next.data, data)>0)
				{
					Node temp = tempNode.next;
					tempNode.next.prev = tempNode.next = newNode;
					newNode.next = temp;
					newNode.prev = tempNode;
				}
				tempNode = tempNode.next; 
			}
		}
        size++;	
	}

	
	@Override
	public void addToEnd(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	
	@Override
	public void addToFront(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/**
	 * Returns an iterator positioned at the head of the list
	 */
	@Override
	public ListIterator<T> iterator() 
	{
		return super.iterator();
	}
	
	/**
	 * Returns a node containing the data or null
	 * @param data
	 */
	@Override
	public BasicDoubleLinkedList.Node remove(T data, Comparator<T> comparator) 
	{
		return super.remove(data, comparator);
	}
	
}