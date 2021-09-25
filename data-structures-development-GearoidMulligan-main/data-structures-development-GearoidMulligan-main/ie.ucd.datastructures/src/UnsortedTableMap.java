import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a map using an unsorted table.
 */

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
	/** Underlying storage for the map of entries. */
	private ArrayList<MapEntry<K, V>> table;// = new ArrayList<>();

	/** Constructs an initially empty map. */
	public UnsortedTableMap() {
		this.table = new ArrayList<MapEntry<K, V>>();
	}

	// private utility
	/** Returns the index of an entry with equal key, or -1 if none found. */
	private int findIndex(K key) {
		int size=this.table.size();
		for(int j=0;j<size;j++) {
			if(this.table.get(j).getKey().equals(key)) {
				return j;
			}
		}
	return -1;
		}

	// public methods
	/**
	 * Returns the number of entries in the map.
	 * 
	 * @return number of entries in the map
	 */
	@Override
	public int size() {
		return this.table.size();
	}

	/**
	 * Returns the value associated with the specified key, or null if no such entry
	 * exists.
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the associated value, or null if no such entry exists
	 */
	@Override
	public V get(K key) {
		int find=findIndex(key);
		if(find==-1) {
			return null;
		}
		else {
			return table.get(find).getValue();
		}
	}

	/**
	 * Associates the given value with the given key. If an entry with the key was
	 * already in the map, this replaced the previous value with the new one and
	 * returns the old value. Otherwise, a new entry is added and null is returned.
	 * 
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with the key (or null, if no such
	 *         entry)
	 */
	@Override
	public V put(K key, V value) {
		int index=findIndex(key);
		if(index==-1) {
			table.add(new MapEntry<>(key,value));
			return null;
		}
		else {
			return table.get(index).setValue(value);
		}
	}

	/**
	 * Removes the entry with the specified key, if present, and returns its value.
	 * Otherwise does nothing and returns null.
	 * 
	 * @param key the key whose entry is to be removed from the map
	 * @return the previous value associated with the removed key, or null if no
	 *         such entry exists
	 */
	@Override
	public V remove(K key) {
		int sz=size();
		int index=findIndex(key);
		if(index==-1) {
			return null;
		}
		V ans = table.get(index).getValue();
		if(index !=sz-1) {
			table.set(index, table.get(sz-1));
		}
		table.remove(sz-1);
		return ans;
		
	}

	// ---------------- nested EntryIterator class ----------------
	private class EntryIterator implements Iterator<Entry<K, V>> {
		private int j = 0;

		public boolean hasNext() {
			if(j<table.size()) {
				return true;
			}
			else {
				return false;
			}
		}

		public Entry<K, V> next() {
			if(j==table.size()) {
				throw new NoSuchElementException( );
			}
			else {
				return table.get(j++);
			}
		}

		public void remove() {
			throw new UnsupportedOperationException("remove not supported");
		}
	} // ----------- end of nested EntryIterator class -----------

	// ---------------- nested EntryIterable class ----------------
	private class EntryIterable implements Iterable<Entry<K, V>> {
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	} // ----------- end of nested EntryIterable class -----------

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}
	
	public String toString() {
		return table.toString();
	}
}
