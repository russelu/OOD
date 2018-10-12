package myhashmap;

import java.util.Iterator;

public class BucketIterator<K, V> implements Iterator<Cell> {
    private Cell<K, V> curr;
    public BucketIterator(Cell head) {
        curr = head.next();
    }
    @Override
    public boolean hasNext() {
        return curr != null;
    }

    @Override
    public Cell<K, V> next() {
        Cell<K, V> next = curr;
        curr = curr.next();
        return next;
    }
}
