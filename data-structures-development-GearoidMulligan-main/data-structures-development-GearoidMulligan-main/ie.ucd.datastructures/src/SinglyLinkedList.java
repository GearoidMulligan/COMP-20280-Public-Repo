
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A basic singly linked list implementation.
 */
public class SinglyLinkedList<E> implements Cloneable, Iterable<E>, List<E> {

    //---------------- nested Node class ----------------

    /**
     * Node of a singly linked list, which stores a reference to its
     * element and to the subsequent node in the list (or null if this
     * is the last node).
     */
    private static class Node<E> {
        private E element;
        
        private Node<E> next;
        
        
        public Node(E e, Node<E> n) {
        	element = e;
        	next = n;
        }
        
        public E getElement() {
        	return element;
        }
        
        public Node <E> getNext (){
        	return next;
        }
        
        public void setNext(Node<E> n ) {
        	next=n;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the SinglyLinkedList
    private Node<E> head = null; // head node of the list (or null if empty)
    private Node<E> tail = null;
    
    private int size = 0; // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    // access methods

    /**
     * Returns the number of elements in the linked list.
     *
     * @return number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

   @Override
    public E get(int i) throws IndexOutOfBoundsException {
	   Node<E> walk = head;
	   for(int a=0;a!=i;a++) {
		   walk=walk.getNext();
	   }
	   return walk.getElement();
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
    	Node<E> last = head;
    	int a=0;
    	while (a<i-1) {
    		last = last.getNext();
    		a++;
    	}
    	Node<E> newest = new Node<E>(e, last.getNext());
    	last.setNext(newest);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
    	Node<E> walk = head;
    	Node<E> temp = head;
 	   for(int a=0;a!=i-1;a++) {
 		   walk=walk.getNext();
 	   }
 	   temp=walk;
 	   walk=walk.getNext();
 	   temp.setNext(walk.getNext());
 	   return walk.getElement();
 	   
    }

    
    
    public E removeLast() throws IndexOutOfBoundsException {
    	Node<E> walk = head;
    	Node<E> temp = head;
 	   for(int a=0;a!=size-2;a++) {
 		   walk=walk.getNext();
 	   }
 	   temp=walk;
 	   walk=walk.getNext();
 	   temp.setNext(walk.getNext());
 	   return walk.getElement();
 	   
    }
    
    /**
     * Returns (but does not remove) the first element of the list
     *
     * @return element at the front of the list (or null if empty)
     */

	public E first() {
    	if(head==null) {
    		return null;
    	}
    	else {
    		
    		return head.getElement();
    	}
    }

    /**
     * Returns the last node of the list
     *
     * @return last node of the list (or null if empty)
     */
    public Node<E> getLast() {
    	if(head==null) {
    		return null;
    	}
    	else {
    		Node<E> walk = head;
    	 	   while(walk.getNext()!=null) {
    	 		   walk=walk.getNext();
    	 	   }
    	 	   return walk;
    	    	}
    }

    /**
     * Returns (but does not remove) the last element of the list.
     *
     * @return element at the end of the list (or null if empty)
     */
    public E last() {
    	
    	if(head==null) {
    		return null;
    	}
    	else {
    	Node<E> walk = head;
 	   while(walk.getNext()!=null) {
 		   walk=walk.getNext();
 	   }
 	   return walk.getElement();
    	}
    }

    // update methods

    /**
     * Adds an element to the front of the list.
     *
     * @param e the new element to add
     */
    public void addFirst(E e) {
    	head=new Node<E>(e, head);
    	size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param e the new element to add
     */
    public void addLast(E e) {
        Node<E> newest = new Node<E>(e, null);
        Node<E> last = head;
        if(last==null) {
        	head = newest;
        }
        else {
        	while (last.getNext() != null) {
        		last = last.getNext();
        	}
        	last.setNext(newest);
        }
        size++;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */

	public E removeFirst() {
		if (isEmpty( )) return null; // nothing to remove
		E answer = head.getElement( );
		head = head.getNext( ); // will become null if list had only one node
		size--;
		if (size == 0) {
		tail = null; // special case as list is now empty
		}
		return answer;
    }

    @SuppressWarnings({})
    public boolean equals(Object o) {
    	if(this==o) {
    		return true;
    	}
  	   
        return false;   // if we reach this, everything matched successfully
    }

    @SuppressWarnings({})
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        // 34
        return null;
    }


    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     * @return 
     */
    public String toString() {
    	StringBuffer str = new StringBuffer();

        Node<E> walk = head;
        System.out.print("[");
        str.append("[");
        while(walk.next!=null) {
        	System.out.print(walk.getElement()+", ");
        	str.append(walk.getElement()+ ", ");
        	walk=walk.getNext();
        }
        System.out.print(walk.getElement()+"]");
        str.append(walk.getElement()+ "]");
        
    	
       return str.toString();
    }


	private class SinglyLinkedListIterator implements Iterator<E> {

		Node<E> curr=head;
		@Override
		public boolean hasNext() {
			return curr!=null;
		}

		@Override
		public E next() {
			E res = (E) curr.getElement();
            curr=curr.getNext();
            return res;

		}
    }

    public Iterator<E> iterator() {
       return new SinglyLinkedListIterator();
    }

    public static void main(String[] args) {
    	SinglyLinkedList <Integer> ll =new SinglyLinkedList <Integer>();
    	//LinkedList<Integer>ll=newLinkedList<Integer>();
    	ll.addFirst(0);
    	ll.addFirst(1);
    	ll.addFirst(3);
    	ll.addFirst(4);
    	ll.addFirst(5);
    	ll.add(3, 2);
    	System.out.println(ll);
    	ll.addFirst(-100);
    	ll.addLast(+100);
    	System.out.println(ll);
    	ll.removeFirst();
    	//ll.removeLast();
    	System.out.println(ll);
    	//Removes the item in the specified index
    	ll.remove(2);
    	System.out.println(ll);
    	ll.removeFirst();
    	System.out.println(ll);
    	//ll.removeLast();
    	System.out.println(ll);
    	ll.removeFirst();
    	System.out.println(ll);
    	ll.addFirst(9999);
    	ll.addFirst(8888);
    	ll.addFirst(7777);


    	System.out.println(ll);
    	System.out.println(ll.get(0));
    	System.out.println(ll.get(1));
    	System.out.println(ll.get(2));
    	System.out.println(ll);
    	ll.toString();
    }
}

