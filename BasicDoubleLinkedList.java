
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class BasicDoubleLinkedList<T> implements Iterable<T> 
{
	int size;
	Node head, tail;
	
	//Constructor to set to initialize head, tail and size to null, null and 0
	public BasicDoubleLinkedList() 
	{
		size = 0;
		head = null;
		tail = null;
	}
	
	public class Node 
	 {
		T data;
		Node next, prev;

		Node(T dataNode)
		{
			this.data = dataNode;
		}
	}
	
		public class DoubleLinkedListIterator implements ListIterator<T>
		{
			private Node current;
			private Node last;

			//Constructor to initialize the current pointer to the head of the BasicDoubleLinkedList
			public DoubleLinkedListIterator()
			{
				current = head;
				last = null;
			}
			
			
			@Override
			public boolean hasNext() 
			{
				return current != null;
			}
			
			@Override
		public T next() throws NoSuchElementException
		{
			if(current != null)
			{
				T data = current.data;
				last = current;
				current = current.next;
				
				if(current != null)
				current.prev = last;

				return data;
			} else
				throw new NoSuchElementException();
		}
			@Override
			public boolean hasPrevious() 
			{
				return last != null;
			}
			
			@Override
			public T previous() throws NoSuchElementException
			{
				if(last != null)
				{
					current = last;
					last = current.prev;
					T data = current.data;
					return data;
				}
				else
					throw new NoSuchElementException();
			}
			
			@Override
			public int nextIndex() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
			public void set(T data) {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public void add(T e) {
				throw new UnsupportedOperationException();
			}
		}

	 
	public int getSize() 
	{
		return size;
	}

	/**
	 * Adds an element to the end of the list and updated the size of the list
	 * 
	 * @param data
	 */
	public void addToEnd(T data)
	{
		Node newNode = new Node(data);
		
			if (tail != null) {
				tail.next = newNode;
				size++;
			}
			tail = newNode;
	
			if (head == null) {
				head = newNode;
				size++;
			}
	}
	
	/**
	 * Adds element to the front of the list and updated the size of the list
	 * 
	 * @param data
	 */
	public void addToFront(T data)
	{
		Node newNode = new Node(data);
		
		if(size==0)
		{
			head = newNode;
			tail= newNode;
		}
		else
		{
			head.prev=newNode;
			newNode.next=head;
			head=newNode;
		}
		size++;
	}
	
	/**
	 * Returns but does not remove the first element from the list
	 * If there are no elements the method returns null
	 * 
	 * @return the data element or null
	 */
	public T getFirst() {
		return head.data;
	}

	/**
	 * Returns but does not remove the last element from the list.
	 * If there are no elements the method returns null. Do not implement this method using iterators
	 * 
	 * @return the data element or null
	 */
	public T getLast() {
		return tail.data;
	}
	
	/**
	 * Returns a ListIterator object
	 */
	public ListIterator<T> iterator() 
	{
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * @param targetData
	 * @param comparator
	 * @return a node containing the targetData or null
	 */
	public BasicDoubleLinkedList.Node remove(T targetData, java.util.Comparator<T> comparator) 
	{
		Node temp = null;
		Node current = head;
		
		while (current != null) 
		{
			if (comparator.compare(targetData, current.data) == 0) 
			{
				if (current == head) 
				{
					head = head.next;
					head.prev=null;
					current = head;
				} 
				else if (current == tail) 
				{
					current = null;
					tail = temp;
					temp.next = null;
					size--;
				} 
				else 
				{
					temp.next = current.next;
					current = current.next;
					size--;
				}
			} 
			else {
				temp = current;
				current = current.next;
			}
		}
		return current;
	}
	
	/**
	 * Removes and returns the first element from the list. If there are no elements the method returns null
	 * 
	 * @return data element or null
	 */
	public T retrieveFirstElement() 
	{
		if (head != null) 
		{
			Node node = head;
		head = head.next;
		head.prev = null;
		size--;
		return node.data;
		} else
			return null;
	}
	
	/**
	 * Removes and returns the last element from the list 
	 * If there are no elements the method returns null
	 * 
	 * @return data element or null
	 */
	public T retrieveLastElement() 
	{
		if (head != null) 
		{
			Node current = head;
			Node previousNode = null;
			while (current != null) {
				if (current.equals(tail)) {
					tail = previousNode;
					break;
				}
				previousNode = current;
				current = current.next;
			}
			size--;
			return current.data;
		}else
			return null;
		}

	/**
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList()
	{
		ArrayList<T> list=new ArrayList<T>(getSize());
		Node current = head;
		
		while(current !=null)
		{
			list.add(current.data);
			current= current.next;
		}
		return list;
		}

}

