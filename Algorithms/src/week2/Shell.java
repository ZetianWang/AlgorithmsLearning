package week2;
/**
 * Idea. Move entries more than on position at a time by h-sorting the array
 * big increments => small subarray
 * small increments => nearly in order
 * do passes of big increments sort to make array generally sorted
 * increment sequence.
 * 3x + 1, easy to compute, 1, 4, 13, ...
 * worst case O(N(3/2))
 * @author wangzetian
 *
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - 1]); j -=h) {
                    exch(a, j, j-h);
                }
            }
            h = h / 3;
        }
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
