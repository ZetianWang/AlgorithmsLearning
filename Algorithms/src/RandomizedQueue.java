import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int n;
    
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;       
    }
    
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int id = StdRandom.uniform(n);
        Item item = a[id];
        a[id] = a[--n];
        a[n] = null;
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }
    
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int id = StdRandom.uniform(n);
        return a[id];
    }
    
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current;
        private int[] randomId;
        
        public RandomizedQueueIterator() {
            current = n - 1;
            randomId = new int[n];
            for (int i = 0; i < n; i++) {
                randomId[i] = i;
            }
            StdRandom.shuffle(randomId);
        }
        public boolean hasNext() {
            return current >= 0;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return a[randomId[current--]];
        }
        
    }
       
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
