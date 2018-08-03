/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

public class MinHeap <T extends Comparable<T>> {
    // fields
    private Object[] array;
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    private final Comparator comparator;
    
    // methods
    public MinHeap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }
    
    public MinHeap(int capacity) {
        this(capacity, null);
    }
    
    public MinHeap(Comparator<? super T> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }
    
    public MinHeap(int capacity, Comparator<? super T> comparator) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must > 0");
        array = new Object[capacity];
        size = 0;
        this.comparator = comparator;
    }
    
    public MinHeap(T[] inputArray) {
        array = Arrays.copyOf(inputArray, inputArray.length * 2);
        size = inputArray.length;
        comparator = null;
        heapify();
    }
    
    public boolean add(T t) {
        if (size == array.length)
            expand();
        array[size] = t;
        percolateUp(size);
        size++;
        return true;
    }
    
    public T poll() {
        if (size <= 0) throw new IllegalStateException("heap empty");
        T top = (T) array[0];
        swap(0, size - 1);
        size--;
        percolateDown(0);
        return top;
    }
    
    public T peek() {
        if (size <= 0) throw new IllegalStateException("heap empty");
        return (T) array[0];
    }
    
    public boolean update(int idx, T t) {
        if (idx < 0 || idx > size)
            return false;
        if (idx == size) {
            return add(t);
        }
        array[idx] = t;
        percolateUp(idx);
        percolateDown(idx);
        return true;
    }
    
    private void percolateUp(int idx) {
        if (comparator == null) {
            percolateUpComparable(idx);
        } else {
            percolateUpComparator(idx);
        }
    }
    
    private void percolateUpComparator(int idx) {
        int parent = (idx - 1) / 2;
        while (idx > 0) {
            if (comparator.compare((T) array[idx], (T) array[parent]) >= 0)
                return;
            swap(idx, parent);
            idx = parent;
            parent = (idx - 1) / 2;
        }
    }
    
    private void percolateUpComparable(int idx) {
        int parent = (idx - 1) / 2;
        while (idx > 0) {
            if (((T)array[idx]).compareTo((T) array[parent]) >= 0)
                return;
            swap(idx, parent);
            idx = parent;
            parent = (idx - 1) / 2;
        }
    }
    
    private void percolateDown(int idx) {
        if (comparator == null) {
            percolateDownComparable(idx);
        } else {
            percolateDownComparator(idx);
        }
    }
    
    private void percolateDownComparable(int idx) {
        int leftChild = idx * 2 + 1;
        int rightChild = idx * 2 + 2;
        int swapChild = leftChild;
        while (leftChild < size) {
            if (rightChild < size 
                    && ((T) array[rightChild]).compareTo(
                            (T) array[leftChild]) < 0)
                swapChild = rightChild;
            if (((T) array[swapChild]).compareTo((T) array[idx]) < 0) {
                swap(idx, swapChild);
                idx = swapChild;
                leftChild = idx * 2 + 1;
                rightChild = idx * 2 + 2;
                swapChild = leftChild;
            } else return;
        }
    }
     
    private void percolateDownComparator(int idx) {
        int leftChild = idx * 2 + 1;
        int rightChild = idx * 2 + 2;
        int swapChild = leftChild;
        while (leftChild < size) {
            if (rightChild < size 
                    && comparator.compare(
                            (T)array[rightChild], (T)array[leftChild]) < 0)
                swapChild = rightChild;
            if (comparator.compare((T) array[swapChild],
                    (T) array[idx]) < 0) {
                swap(idx, swapChild);
                idx = swapChild;
                leftChild = idx * 2 + 1;
                rightChild = idx * 2 + 2;
                swapChild = leftChild;
            } else return;
        }
    }
    
    private void heapify() {
        for (int i = (size - 2) / 2; i >= 0; --i) {
            percolateDown(i);
        }
    }
    
    private void expand() {
        array = Arrays.copyOf(array, array.length * 2);
    }
    
    private void swap(int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; ++i) {
            sb.append(((T) array[i]).toString() + ", ");
        }
        sb.append(((T) array[size - 1]).toString() + "]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Card[] deck = new Card[9];
        int k = 0;
        deck[k++] = new Card(13, Suit.HEART);
        deck[k++] = new Card(11, Suit.HEART);
        deck[k++] = new Card(4, Suit.HEART);
        deck[k++] = new Card(10, Suit.HEART);
        deck[k++] = new Card(8, Suit.HEART);
        deck[k++] = new Card(7, Suit.HEART);
        deck[k++] = new Card(6, Suit.HEART);
        deck[k++] = new Card(2, Suit.HEART);
        deck[k++] = new Card(3, Suit.HEART);
        
        MinHeap<Card> heap = new MinHeap<>(deck);
        System.out.println(heap.toString());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        heap.add(new Card(3, Suit.CLUB));
        System.out.println(heap.peek());
        heap.update(7, new Card(2, Suit.SPADE));
        System.out.println(heap.peek());
    }
}
