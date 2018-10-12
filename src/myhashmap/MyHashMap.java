package myhashmap;

public class MyHashMap<K, V> {
    private Bucket<K, V>[] buckets;
    private int loadFactor;
    private int cap;
    private static final int DEFAULT_CAP = 256;

    public MyHashMap(int cap) {
        this.cap = cap;
        loadFactor = 0;
        buckets = new Bucket[cap];
    }

    public MyHashMap() {
        this(DEFAULT_CAP);
    }

    // TODO: get(key), put(key, value), remove(key)
    private int getHashIndex(K key) {
        return key == null ? 0 : key.hashCode() % cap;
    }

    public V get(K key) {
        int idx = getHashIndex(key);
        Bucket<K, V> bucket = buckets[idx];
        if (bucket == null) {
            buckets[idx] = new Bucket<>();
        }
        return buckets[idx].get(key);
    }

    public boolean put(K key, V value) {
        int idx = getHashIndex(key);
        Bucket<K, V> bucket = buckets[idx];
        if (bucket == null) {
            buckets[idx] = new Bucket<>();
        }
        boolean res = buckets[idx].put(key, value);

        if (res) { // count loadFactor
            if (++loadFactor >= cap / 2) { // rehash when load >= 0.5
                rehash();
            }
        }
        return res;
    }

    public boolean remove(K key) {
        int idx = getHashIndex(key);
        Bucket<K, V> bucket = buckets[idx];
        if (bucket == null) {
            buckets[idx] = new Bucket<>();
            return false;
        }
        boolean res = bucket.remove(key);
        if (res) loadFactor--;
        return res;
    }

    private void rehash() {
        cap *= 2;
        Bucket<K, V>[] newBuckets = new Bucket[cap];
        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null) {
                for (Cell<K, V> cell : bucket) {
                    //Cell<K, V> cell = (Cell<K, V>) object;
                    K key = cell.getKey();
                    int idx = getHashIndex(key);
                    Bucket<K, V> newBucket = newBuckets[idx];
                    if (newBucket == null) {
                        newBuckets[idx] = new Bucket<>();
                    }
                    newBuckets[idx].add(cell);
                }
            }
        }
    }

    private void print() {
        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null) {
                for (Cell<K, V> cell : bucket) {
                    System.out.print(cell.getKey().hashCode() + ": " + cell.getValue() + ",");
                }
                System.out.println();
            }
        }
    }
}
