import java.util.ArrayList;
import java.util.HashMap;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    /**
     * Creates a hash table with capacity 11 and prime factor 109345121.
     */
    public ChainHashMap() {
    	super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ChainHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    public static void main(String[] args) {
        //HashMap<Integer, String> m = new HashMap<Integer, String>();
        ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();

        m.put(1, "One");
        m.put(10, "Ten");
        m.put(11, "Eleven");
        m.put(20, "Twenty");

        System.out.println("m: " + m);

        m.remove(11);
        
        System.out.println("m: " + m);

//		ChainHashMap<String, Integer> counter = new ()//;
//		// Scanner from file
//		for(String word : scanner) {
//			Integer old_count = counter.get(word);
//			counter.put(old_count + 1);
//		}
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    
	

	@Override
    protected void createTable() {
        this.table=(UnsortedTableMap<K,V>[]) new UnsortedTableMap[this.capacity];
    }

    /**
     * Returns value associated with key k in bucket with hash value h. If no such
     * entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    @Override
    protected V bucketGet(int h, K k) {
    	UnsortedTableMap<K,V> bucket = this.table[h];
    	if(bucket==null) {
    		return null;
    	}
    	return bucket.get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h, returning the
     * previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketPut(final int n, final K k, final V v) {
        UnsortedTableMap<K, V> unsortedTableMap = this.table[n];
        if (unsortedTableMap == null) {
            final UnsortedTableMap<K, V>[] table = this.table;
            final UnsortedTableMap<K, V> unsortedTableMap2 = new UnsortedTableMap<K, V>();
            table[n] = unsortedTableMap2;
            unsortedTableMap = unsortedTableMap2;
        }
        final int size = unsortedTableMap.size();
        final V put = unsortedTableMap.put(k, v);
        this.n += unsortedTableMap.size() - size;
        return put;
    }

    /**
     * Removes entry having key k from bucket with hash value h, returning the
     * previously associated value, if found. 
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketRemove(int h, K k) {
    	UnsortedTableMap<K, V> unsortedTableMap = table[h];
        if (unsortedTableMap == null) {
            return null;
        }
        final int size = unsortedTableMap.size();
        final V remove = unsortedTableMap.remove(k);
        this.n -= size - unsortedTableMap.size();
        return remove;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	ArrayList<Entry<K,V>> buffer = new ArrayList<>( );
    	for (int h=0; h < capacity; h++)
    	if (table[h] != null)
    	for (Entry<K,V> entry : table[h].entrySet( ))
    	buffer.add(entry);
    	return buffer;
    }

    public String toString() {
        return entrySet().toString();
    }
}
