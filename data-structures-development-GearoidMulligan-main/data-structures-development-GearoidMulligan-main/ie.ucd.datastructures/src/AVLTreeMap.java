import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import java.util.Iterator;
import java.util.Comparator;

public class AVLTreeMap<K, V> extends TreeMap<K, V>
{
    public AVLTreeMap() {
    }
    
    public AVLTreeMap(final Comparator<K> comparator) {
        super(comparator);
    }
    
    protected int height(final Position<Entry<K, V>> position) {
        return tree.getAux(position);
    }
    
    protected void recomputeHeight(final Position<Entry<K, V>> position) {
        tree.setAux(position, 1 + Math.max(height(left(position)), height(right(position))));
    }
    
    protected boolean isBalanced(final Position<Entry<K, V>> position) {
        return Math.abs(height(left(position)) - height(right(position))) <= 1;
    }
    
    protected Position<Entry<K, V>> tallerChild(final Position<Entry<K, V>> position) {
        if (height(left(position)) > height(right(position))) {
            return left(position);
        }
        if (height(left(position)) < height(right(position))) {
            return right(position);
        }
        if (isRoot(position)) {
            return left(position);
        }
        if (position == left(parent(position))) {
            return left(position);
        }
        return right(position);
    }
    
    protected void rebalance(Position<Entry<K, V>> position) {
        int height;
        int height2;
        do {
            height = height(position);
            if (!isBalanced(position)) {
                position = restructure(tallerChild(tallerChild(position)));
                recomputeHeight(left(position));
                recomputeHeight(right(position));
            }
            recomputeHeight(position);
            height2 = height(position);
            position = parent(position);
        } while (height != height2 && position != null);
    }
    
    @Override
    protected void rebalanceInsert(final Position<Entry<K, V>> position) {
        rebalance(position);
    }
    
    @Override
    protected void rebalanceDelete(final Position<Entry<K, V>> position) {
        if (!isRoot(position)) {
            rebalance(parent(position));
        }
    }
    
    private boolean sanityCheck() {
        for (final Position<Entry<K, V>> position : tree.positions()) {
            if (isInternal((Position<Entry<K, V>>)position)) {
                if (position.getElement() == null) {
                    System.out.println("VIOLATION: Internal node has null entry");
                }
                else {
                    if (height((Position<Entry<K, V>>)position) != 1 + Math.max(height(left((Position<Entry<K, V>>)position)), height(right((Position<Entry<K, V>>)position)))) {
                        System.out.println("VIOLATION: AVL unbalanced node with key " + position.getElement().getKey());
                        dump();
                        return false;
                    }
                    continue;
                }
            }
        }
        return true;
    }
}
