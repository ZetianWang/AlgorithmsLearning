package week2;

/**
 * If array is full, create a new array of twice of the size, and copy items.
 * If array is 1/4 full, resize it to half
 * Amortized analysis. Average running time per operation over a worst case sequence of operation
 * pros: 1. Starting from an empty stack, any sequence of M push and pop operations takes time proportional to M
 *2. Uses between ~ 8N (when full) bytes and ~ 32N (when one quarter full) bytes to represent a stack with N items 
 * @author wangzetian
 *
 */
public class ResizingArrayStackOfStrings {
    private String[] s;
    private int N = 0;
    
    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }
    
    public void push(String item) {
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }
    
    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
    
    public String pop() {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }
}
