package week3;
/**
 * Proposition. Merge sort uses at most NlgN compares and 6NlgN array accesses to sort any size of array
 * If D(N) satisfies D(N) = 2D(N / 2) + N for N > 1, with D(1) = 0,
 * then D(N) = NlgN
 * Merge sort uses extra space proportional to N
 * Def. A sorting algorithm is in-place if it uses <= clogN extra memory
 * 1.Use insertion sort for small subarrays,
 * Mergesort has too much overhead for tiny subarrays, set cutoff 
 * 2. Stop if already sorted
 * Is biggest item in first half <= smallest item in second half
 * 3. Eliminate the copy to the auxiliary array.
 * Save time (but not space) by switching the role of the input and the auxiliary array in each recursive call
 * @author wangzetian
 *
 */
public class Merge {
    private static Comparable[] aux;
    
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);
        
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = a[j++];
            else a[k] = a[i++];
        }
        
        assert isSorted(a, lo, hi);
    }
    
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, lo, mid, hi);
    }
    
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    
    public static void sortBU(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz -1, N - 1));
            }
        }
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Assertion. Statement to test assumptions about your program.
     * help detect logic bugs; documents code
     * throw exception unless boolean condition is true 
     * can enable or disable at runtime, no cost in production code, java -ea/da 
     */
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }
}
