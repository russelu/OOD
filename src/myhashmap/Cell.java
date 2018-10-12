package myhashmap;

public class Cell<K, V> {
    private final K key;
    private V value;
    private Cell next;

    public Cell(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
    }

    public Cell() {
        this(null, null);
    }

    // TODO: hashCode, equals, keyEquals
    @Override
    public int hashCode() {
        return key == null ? 0 : key.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof Cell) {
            Cell<K, V> that = (Cell) other;
            if (key == null) return that.key == null;
            return keyEquals(that.key);
        }
        return false;
    }

    public boolean keyEquals(K otherKey) {
        return key.equals(otherKey);
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public Cell<K, V> next() {
        return next;
    }

    public void setNext(Cell<K, V> cell) {
        next = cell;
    }

    public void setValue(V newValue) {
        this.value = newValue;
    }
}
