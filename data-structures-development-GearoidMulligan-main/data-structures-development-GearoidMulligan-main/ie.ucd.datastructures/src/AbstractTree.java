import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An abstract base class providing some functionality of the Tree interface.
 * <p>
 * The following three methods remain abstract, and must be
 * implemented by a concrete subclass: root, parent, children. Other
 * methods implemented in this class may be overridden to provide a
 * more direct and efficient implementation.
 */
public abstract class AbstractTree<E> implements Tree<E> {

    /**
     * Returns true if Position p has one or more children.
     *
     * @param p A valid Position within the tree
     * @return true if p has at least one child, false otherwise
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p)>0;
    }

    /**
     * Returns true if Position p does not have any children.
     *
     * @param p A valid Position within the tree
     * @return true if p has zero children, false otherwise
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public boolean isExternal(Position<E> p) {
        return numChildren(p)==0;
    }

    /**
     * Returns true if Position p represents the root of the tree.
     *
     * @param p A valid Position within the tree
     * @return true if p is the root of the tree, false otherwise
     */
    @Override
    public boolean isRoot(Position<E> p) {
      return p==root();
    }

    /**
     * Returns the number of children of Position p.
     *
     * @param p A valid Position within the tree
     * @return number of children of Position p
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public int numChildren(Position<E> p) {
      int count =0;
      for(Position<E> p1 : positions()) {
    	  count++;
      }
      return count;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
    	int n = 0;
        for (Position<E> p : positions()) {
            ++n;
        }
        return n;
    }

    /**
     * Tests whether the tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    //---------- support for computing depth of nodes and height of (sub)trees ----------

    /**
     * Returns the number of levels separating Position p from the root.
     *
     * @param p A valid Position within the tree
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public int depth(Position<E> p) throws IllegalArgumentException {
    	if (isRoot(p)) {
            return 0;
        }
        return 1 + depth(this.parent(p));
    }

    /**
     * Returns the height of the tree.
     * <p>
     * Note: This implementation works, but runs in O(n^2) worst-case time.
     */
    private int heightBad() {             // works, but quadratic worst-case time
        int h = 0;
        for (Position<E> p : positions())
            if (isExternal(p))                // only consider leaf positions
                h = Math.max(h, depth(p));
        return h;
    }

    /**
     * Returns the height of the subtree rooted at Position p.
     *
     * @param p A valid Position within the tree
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public int height(Position<E> p) throws IllegalArgumentException {
    	int h = 0; // base case if p is external
    	for (Position<E> c : children(p))
    	h = Math.max(h, 1 + height(c));
    	return h;
    }

    //---------- support for various iterations of a tree ----------

    /**
     * Returns an iterator of the elements stored in the tree.
     *
     * @return iterator of the tree's elements
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /**
     * Returns an iterable collection of the positions of the tree.
     *
     * @return iterable collection of the tree's positions
     */
    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
    }

    /**
     * Adds positions of the subtree rooted at Position p to the given
     * snapshot using a preorder traversal
     *
     * @param p        Position serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    	snapshot.add(p);
        final Iterator<Position<E>> iterator = children(p).iterator();
        while (iterator.hasNext()) {
            preorderSubtree(iterator.next(), snapshot);
        }
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in preorder.
     *
     * @return iterable collection of the tree's positions in preorder
     */
    public Iterable<Position<E>> preorder() {
    	ArrayList<Position<E>> lst = new ArrayList<Position<E>>();
        if (!isEmpty()) {
            preorderSubtree(root(), lst);
        }
        return lst;
    }

    /**
     * Adds positions of the subtree rooted at Position p to the given
     * snapshot using a postorder traversal
     *
     * @param p        Position serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    	Iterator<Position<E>> iterator = children(p).iterator();
        while (iterator.hasNext()) {
            this.postorderSubtree(iterator.next(), snapshot);
        }
        snapshot.add(p);
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in postorder.
     *
     * @return iterable collection of the tree's positions in postorder
     */
    public Iterable<Position<E>> postorder() {
    	ArrayList<Position<E>> list = new ArrayList<Position<E>>();
        if (!isEmpty()) {
            postorderSubtree(root(), list);
        }
        return list;
    }

    /**
     * Returns an iterable collection of positions of the tree in breadth-first order.
     *
     * @return iterable collection of the tree's positions in breadth-first order
     */
    public Iterable<Position<E>> breadthfirst() {
    	ArrayList<Position<E>> list = new ArrayList<Position<E>>();
        if (!this.isEmpty()) {
            LinkedQueue<Position<E>> linkedQueue = (LinkedQueue<Position<E>>)new LinkedQueue<Position<E>>();
            linkedQueue.enqueue(root());
            while (!linkedQueue.isEmpty()) {
                Position<E> position = linkedQueue.dequeue();
                list.add(position);
                Iterator<Position<E>> iterator = children(position).iterator();
                while (iterator.hasNext()) {
                    linkedQueue.enqueue(iterator.next());
                }
            }
        }
        return list;
    }
    

    //---------------- nested ElementIterator class ----------------
    /* This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        } // return element!

        public void remove() {
            posIterator.remove();
        }
    }
}