package week2;

public class FixedCapacityStackOfStrings {
    
    private String[] s;
    private int N = 0;
    
    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public void push(String item) {
        s[N++] = item;
    }
    
    //Loitering, Holding a reference to an object when it is no longer needed
    //public String pop() {
    //    return s[--N];
    //}
    public String pop() {
        String item = s[--N];
        s[N] = null;
        return item;
    }

}
