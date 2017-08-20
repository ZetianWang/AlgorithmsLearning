import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;
    
    private class Node {
        private Item item;
        private Node next;
        private Node pre;
    }
    
    public Deque() { 
        n = 0;
        first = null;
        last = null;
    }
    
    public boolean isEmpty() {
        return first == null && last == null;
    }
    
    public int size() {
        return n;
    }
    
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("add null");
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.pre = null;
        n++;
        if (n == 1) {
            last = first;
        }
        else {
            oldfirst.pre = first;
        }
    }
    
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("add null");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.pre = oldlast;
        n++;
        if (n == 1) {
            first = last;
        }
        else {
            oldlast.next = last;
        }
    }
    
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque underflow");
        }
        Item item = first.item;
        if (first == last) {
            first = null;
            last = first;
        }
        else {
            first = first.next;
            first.pre = null;
        }
        n--;
        return item;
    }
    
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque underflow");
        }
        Item item = last.item;
        if (first == last) {
            last = null;
            first = last;
        }
        else {
            last = last.pre;
            last.next = null;
        }
        n--;
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
     

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Deque<String> d = new Deque<String>();
        String[] a = new String[]{"A", "B", "C"};
        for (int i = 0; i < a.length; i++) {
            d.addLast(a[i]);
        }
        System.out.print(d.removeFirst());
        System.out.print(d.removeFirst());

    }

}
