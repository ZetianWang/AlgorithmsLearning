package week2;

/**
 * pros: 1.Every operation takes constant time in the worst case.
 * 2. A stack with N items uses ~ 40N bytes. (16 bytes object overhead, 8 bytes inner class overhead, 8 bytes String reference, 8 Bytes Node reference)
 * @author wangzetian
 *
 */

public class LinkedStackOfStrings {
    private Node first = null;
    
    private class Node {
        String item;
        Node next;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    
    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

}
