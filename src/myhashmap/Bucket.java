package myhashmap;

import java.util.Iterator;

public class Bucket<K, V> implements Iterable<Cell> {
    private final Cell<K, V> head;
    public Bucket() {
        head = new Cell();
    }
    // TODO: get(key), scan(key), put(key, value), remove(key)
    public Cell<K, V> scan(K key) {
        // if key exists, return matched cell
        // otherwise, return null
        Cell<K, V> prev = head, curr = head.next();

        while (curr != null) {
            if (curr.keyEquals(key)) return prev;
            prev = curr;
            curr = curr.next();
        }

        return prev;
    }

    public V get(K key) {
        // if key exists, return cell's value
        // otherwise, return null
        Cell<K, V> matchCell = scan(key).next();

        return matchCell == null ? null : matchCell.getValue();
    }

    public boolean put(K key, V value) {
        // true if not overwriting
        Cell<K, V> prev = scan(key);
        Cell<K, V> curr = prev.next();
        if (curr == null) {
            curr = new Cell(key, value);
            prev.setNext(curr);
            return true;
        }
        curr.setValue(value);
        return false;
    }

    public boolean remove(K key) {
        // true if remove succeed
        Cell<K, V> prev = scan(key);
        Cell<K, V> curr = prev.next();
        if (curr != null) { // key exists
            prev.setNext(curr.next());
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Cell> iterator() {
        // return new BucketIterator<K, V>(head);
        return new Iterator<Cell>() {
            private Cell curr = head.next();
            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Cell next() {
                Cell res = curr;
                curr = curr.next();
                return res;
            }
        };
    }

    public void add(Cell<K, V> cell) {
        cell.setNext(head.next());
        head.setNext(cell);
    }
}
