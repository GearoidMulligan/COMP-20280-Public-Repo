import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor


    public static void main(String[] args) {
		/*LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
		bt.addRoot(1);

		  // Direct construction of Tree Position<Integer> root = bt.addRoot(12);
		  Position<Integer> p1 = bt.addLeft(root, 25); Position<Integer> p2 =
		  bt.addRight(root, 31);

		  Position<Integer> p3 = bt.addLeft(p1, 58); bt.addRight(p1, 36);

		  Position<Integer> p5 = bt.addLeft(p2, 42); bt.addRight(p2, 90);

		  Position<Integer> p4 = bt.addLeft(p3, 62); bt.addRight(p3, 75);


		  // Can you write a level order constructor?
		// Level order construction
		// Integer[] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
		// bt.createLevelOrder(arr);

		System.out.println("bt inorder: " + bt.size() + " " + bt.inorder());
		System.out.println("bt preorder: " + bt.size() + " " + bt.preorder());
		System.out.println("bt preorder: " + bt.size() + " " + bt.postorder());

		System.out.println("bt height: " + bt.height(bt.root()));
		System.out.println("bt depth: " + bt.depth(bt.root()));

		System.out.println(bt.toString());*/
    	LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

		Integer [] arr = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		bt.createLevelOrder(arr);
		
		bt.inorder();
		System.out.print(bt.toString());
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
    	Node<E> n=validate(p);
    	return n.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
    	Node<E> n=validate(p);
    	return n.getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
    	Node<E> n=validate(p);
    	return n.getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if(size>0) {
        	throw new IllegalStateException("Tree is not null");
        }
        root=createNode(e,null,null,null);
        size=1;
        return root;
    }


    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
    	Node<E> parent=validate(p);
    	if(parent.getLeft()!=null) {
    		throw new IllegalArgumentException("p has a left child already");
    	}
    	Node<E>child =createNode(e,parent,null,null);
    	parent.setLeft(child);
    	size++;
    	return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
    	Node<E> parent=validate(p);
    	if(parent.getRight()!=null) {
    		throw new IllegalArgumentException("p has a left child already");
    	}
    	Node<E>child =createNode(e,parent,null,null);
    	parent.setRight(child);
    	size++;
    	return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> n=validate(p);
		E tmp=n.getElement();
		n.setElement(e);
		return tmp;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
    	Node<E> node = validate(p);
    	if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
    	size += t1.size( ) + t2.size( );
    	if (!t1.isEmpty( )) { // attach t1 as left subtree of node
    	t1.root.setParent(node);
    	node.setLeft(t1.root);
    	t1.root = null;
    	t1.size = 0;
    	}
    	if (!t2.isEmpty( )) { // attach t2 as right subtree of node
    	t2.root.setParent(node);
    	node.setRight(t2.root);
    	t2.root = null;
    	t2.size = 0;
    	}
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
 
    		Node<E> node = validate(p);
    		if (numChildren(p) == 2)
    		throw new IllegalArgumentException("p has two children");
    		Node<E> child = (node.getLeft( ) != null ? node.getLeft( ) : node.getRight( ) );
    	    if (child != null)
    		child.setParent(node.getParent( )); // child�s grandparent becomes its parent
    		if (node == root)
    		root = child; // child becomes root
    		else {
    		Node<E> parent = node.getParent( );
    		if (node == parent.getLeft( ))
    		parent.setLeft(child);
    		else
    		parent.setRight(child);
    		}
    		size--;
    		E temp = node.getElement( );
    		node.setElement(null); // help garbage collection
    		node.setLeft(null);
    		node.setRight(null);
    		node.setParent(node); // our convention for defunct node
    		return temp;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Position<E> p : positions()) {
        	if(p.getElement()==null) {
        		continue;
        	}
            sb.append(p.getElement());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
    	root = createLevelOrderHelper(l,root,0);
    }

    private Node<E> createLevelOrderHelper(ArrayList<E> l, Node<E> p, int i) {
    	LinkedList<Position<E>> tree=new LinkedList<Position<E>>();
		Position<E> root= addRoot(l.get(0));
		tree.add(root);
		tree.add(root);
		for(i=1;i<l.size();i++) {
			Position<E> parent=tree.poll();
			Position<E> current;
			if(i%2==0) {
				current=addRight(parent,l.get(i));
			}
			else {
				current=addLeft(parent,l.get(i));
			}
			tree.add(current);
			tree.add(current);
		}
		
		return (LinkedBinaryTree.Node<E>) root;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
		LinkedList<Position<E>> tree=new LinkedList<Position<E>>();
		Position<E> root= addRoot(arr[0]);
		tree.add(root);
		tree.add(root);
		for(i=1;i<arr.length;i++) {
			Position<E> parent=tree.poll();
			Position<E> current;
			if(i%2==0) {
				current=addRight(parent,arr[i]);
			}
			else {
				current=addLeft(parent,arr[i]);
			}
			tree.add(current);
			tree.add(current);
		}
		
		return (LinkedBinaryTree.Node<E>) root;
    }


    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;


        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
        	element=e;
        	parent=p;
        	left=l;
        	right=r;
        }

        // accessor
		public E getElement(){
			return element;
		}
		public Node<E> getParent(){
			return parent;
		}
		public Node<E> getLeft(){
			return left;
		}
		public Node<E> getRight(){
			return right;
		}
		
		//update
		public void setElement(E e) {
			element=e;
		}
		public void setParent(Node<E>pNode) {
			parent=pNode;
		}
		public void setRight(Node<E> rChild) {
			right=rChild;
		}
		public void setLeft(Node<E>lChild) {
			left=lChild;
		}
    }
    

    
}