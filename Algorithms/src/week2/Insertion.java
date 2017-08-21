package week2;
/**
 * Propssition. To sort a randomly-ordered array with distinct keys,
 * insertion sort use ~ 1/4N2 compares and exchanges on average
 * Best case, array already sorted, N - 1 compares and 0 exchanges
 * Worst case, slower than selection sort, same compares much more exchanges
 * @author wangzetian
 *
 */
public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j-1);
                }
                else break;
            }
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
/**
 * Def. An inversion is a pair of keys that are out of order
 * Def. An array is partially sorted if the number of inversions is <= cN
 * Proposition. For partially-sorted array, 
 * insertion sort runs in linear time 
 */
