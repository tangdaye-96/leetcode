package psn.tangdaye.structure.cache;

/**
 * @date       : 2023/3/28 13:40
 * @author     : shayan
 */
public interface Cache<K, V> {
    int capacity = 0;

    /**
     * O(1)
     * @param key key-value
     * @return null if cache miss
     */
    V get(K key);

    /**
     * O(1)
     * will delete key with some strategy
     * @param key k-value
     * @param value v-value
     */
    void put(K key, V value);
}
