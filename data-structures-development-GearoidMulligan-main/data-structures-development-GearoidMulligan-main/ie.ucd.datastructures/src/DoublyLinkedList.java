
import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    //---------------- nested Node class ----------------
    /**
     * Node of a doubly linked list, which stores a reference to its
     * element and to both the previous and next node in the list.
     */
    private static class Node<E> {
        private E element;
        private Node<E>prev;
        private Node<E>next;
        public Node(E e,Node<E> p,Node<E> n) {
        	element=e;
        	prev=p;
        	next=n;
        }
        public E getElement() {
        	return element;
        }
        public Node<E> getPrev(){
        	return prev;
        }
        public Node<E>getNext(){
        	return next;
        }
        public void setPrev(Node<E>p) {
        	prev=p;
        }
        public void setNext(Node<E>n) {
        	next=n;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the DoublyLinkedList
    /** Sentinel node at the beginning of the list */
    private Node<E> header;                    // header sentinel

    /** Sentinel node at the end of the list */
    private Node<E> trailer;                   // trailer sentinel

    /** Number of elements in the list (not including sentinels) */
    private int size = 0;                      // number of elements in the list

    /** Constructs a new empty list. */
    public DoublyLinkedList() {
        header=new Node<>(null,null,null);
        trailer=new Node<>(null,header,null);
        header.setNext(trailer);
    }

    // public accessor methods
    /**
     * Returns the number of elements in the linked list.
     * @return number of elements in the linked list
     */
    public int size() { 
    	return size; 
    }

    /**
     * Tests whether the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() { 
    	if(size==0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    	}

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
    	Node <E> n=header;
        for(int j=0;j<=i;j++) {
        	n=n.getNext();
        }
        return n.getElement();
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
    	Node <E> n=header;
        for(int j=0;j<=i;j++) {
        	n=n.getNext();
        }
        addBetween(e,n.getPrev(),n);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
    	Node <E> n=header;
        for(int j=0;j<=i;j++) {
        	n=n.getNext();
        }
        remove(n);
        return n.getElement();
    }

    private class DoublyLinkedListIterator implements Iterator<E> {
    	Node<E> curr=header.getNext();
		@Override
		public boolean hasNext() {
			return curr!=trailer;
		}

		@Override
		public E next() {
			E res = (E) curr.getElement();
            curr=curr.getNext();
            return res;

		}
    }
    
    @Override
    public Iterator<E> iterator() {
    	return new DoublyLinkedListIterator();
    }

    /**
     * Returns (but does not remove) the first element of the list.
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        if(size==0) {
        	return null;
        }
        else {
        	return header.getNext().getElement();
        }
    }

    /**
     * Returns (but does not remove) the last element of the list.
     * @return element at the end of the list (or null if empty)
     */
    public E last() {
        if(size==0) {
        	return null;
        }
        return trailer.getPrev().getElement();
        
    }

    // public update methods
    /**
     * Adds an element to the front of the list.
     * @param e   the new element to add
     */
    public void addFirst(E e) {
        Node<E> first=new Node<>(e,header,header.getNext());
        header.setNext(first);
        header.getNext().getNext().setPrev(first);
        size++;
    }

    /**
     * Adds an element to the end of the list.
     * @param e   the new element to add
     */
    public void addLast(E e) {
        Node<E> last=new Node<>(e,trailer.getPrev(),trailer);
        trailer.getPrev().setNext(last);
        trailer.setPrev(last);
        size++;
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        if(size==0) {
        	return null;
        }
        else {
        	Node<E> removed=header.getNext();
        	header.setNext(header.getNext().getNext());
        	header.getNext().setPrev(header);
        	size--;
        	return removed.getElement();
        }
    }

    /**
     * Removes and returns the last element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeLast() {
    	if (isEmpty( )) return null; // nothing to remove
      return remove(trailer.getPrev( ));
    	
    }

    // private update methods
    /**
     * Adds an element to the linked list in between the given nodes.
     * The given predecessor and successor should be neighboring each
     * other prior to the call.
     *
     * @param predecessor   node just before the location where the new element is inserted
     * @param successor     node just after the location where the new element is inserted
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<> (e, predecessor, successor);
        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
    }

    /**
     * Removes the given node from the list and returns its element.
     * @param node    the node to be removed (must not be a sentinel)
     */
    private E remove(Node<E> node) {
        Node<E> before = node.getPrev();
        Node<E> after=node.getNext();
        before.setNext(after);
        after.setPrev(before);
        return node.getElement();
    }


    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
    	StringBuffer str = new StringBuffer();

        Node<E> walk = header.getNext();
        System.out.print("[");
        str.append("[");
        while(walk.getNext()!=trailer) {
        	System.out.print(walk.getElement()+", ");
        	str.append(walk.getElement()+ ", ");
        	walk=walk.getNext();
        }
        System.out.print(walk.getElement()+"]");
        str.append(walk.getElement()+ "]");
        
    	
       return str.toString();
    }

    public static void main(String [] args) {
        //ArrayList<String> all;
        //LinkedList<String> ll;
        //DoublyLinkedList<String> ll = new DoublyLinkedList<String>();

        //String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        //for (String s : alphabet) {
            //ll.addFirst(s);
            //ll.addLast(s);
        //}
        //System.out.println(ll.toString());

        //for (String s : ll) {
            //System.out.print(s + ", ");
        //}
    	
    	/*DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		ll.addFirst(-1);
		ll.addFirst(-2);
		ll.addFirst(-3);
		ll.addFirst(-5);
		
		ll.toString();*/
    	DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);
		ll.toString();
		ll.add(2, -1);
		ll.toString();
		//assertEquals("[0, 1, -1, 2, 3, 4]", ll.toString());
    	
    }
} //----------- end of DoublyLinkedList class -----------
