import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {
    //---------------- nested Node class ----------------
    /**
     * Singly linked node, which stores a reference to its element and
     * to the subsequent node in the list.
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

    // instance variables of the CircularlyLinkedList
    /** The designated cursor of the list */
    private Node<E> tail = null;                  // we store tail (but not head)

    /** Number of nodes in the list */
    private int size = 0;                         // number of nodes in the list

    /** Constructs an initially empty list. */
    public CircularlyLinkedList() { }             // constructs an initially empty list

    // access methods
    /**
     * Returns the number of elements in the linked list.
     * @return number of elements in the linked list
     */
    public int size() { return size; }

    /**
     * Tests whether the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() { return size == 0; }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
    	Node<E> walk = tail.getNext();
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
    	Node<E> last = tail.getNext();
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
    	Node<E> walk = tail.getNext();
    	Node<E> temp = tail.getNext();
 	   for(int a=0;a!=i-1;a++) {
 		   walk=walk.getNext();
 	   }
 	   temp=walk;
 	   walk=walk.getNext();
 	   temp.setNext(walk.getNext());
 	   return walk.getElement();
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator();
    }
    
    private class CircularlyLinkedListIterator implements Iterator<E> {
    	Node<E> curr=tail.getNext();
    	int check=0;
		@Override
		public boolean hasNext() {
			if(curr==tail.getNext()) {
				check++;
			}
			return check!=2;
		}

		@Override
		public E next() {
			E res = (E) curr.getElement();
            curr=curr.getNext();
            return res;

		}
    }
    

    /**
     * Returns (but does not remove) the first element of the list
     * @return element at the front of the list (or null if empty)
     */
    public E first() {             // returns (but does not remove) the first element
        if (size==0){
        return null;
        }
        else{
        	return tail.getNext().getElement();
        }
    }

    /**
     * Returns (but does not remove) the last element of the list
     * @return element at the back of the list (or null if empty)
     */
    public E last() {              // returns (but does not remove) the last element
    	if (size==0){
            return null;
            }
            else{
            	return tail.getElement();
            }
    }

    // update methods
    /**
     * Rotate the first element to the back of the list.
     */
    public void rotate() {         // rotate the first element to the back of the list
    	if (tail != null) {
    		tail = tail.getNext( );
    	}
    }

    /**
     * Adds an element to the front of the list.
     * @param e  the new element to add
     */
    public void addFirst(E e) {               // adds element e to the front of the list
    	if (size == 0) {
    		tail = new Node<>(e, null);
    		tail.setNext(tail); // link to itself circularly
    		} 
    	else {
    		Node<E> newest = new Node<>(e, tail.getNext( ));
    		tail.setNext(newest);
    		}
    		size++;
    }

    /**
     * Adds an element to the end of the list.
     * @param e  the new element to add
     */
    public void addLast(E e) {                 // adds element e to the end of the list
    	addFirst(e); 
    	tail = tail.getNext( );
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {                   // removes and returns the first element
    	if(size==0) {
    		return null;
    	}
    	Node<E> head = tail.getNext( );
    	if (head == tail) {
    		tail = null;
    	} // must be the only node left
    	else {
    		tail.setNext(head.getNext( )); // removes ”head” from the list
    	}
    	size--;
    	return head.getElement( );
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
    	StringBuffer str = new StringBuffer();

        Node<E> walk = tail.getNext();
        System.out.print("[");
        str.append("[");
        while(walk.next!=tail.getNext()) {
        	System.out.print(walk.getElement()+", ");
        	str.append(walk.getElement()+ ", ");
        	walk=walk.getNext();
        }
        System.out.print(walk.getElement()+"]");
        str.append(walk.getElement()+ "]");
        
    	
       return str.toString();
    }


    public static void main(String [] args) {
    	CircularlyLinkedList <Integer> ll =new CircularlyLinkedList <Integer>();
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
