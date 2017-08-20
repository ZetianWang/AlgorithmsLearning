
public class Chapter2 {
     /**
     * merge two sorted subarray.
     * @param arr 
     * @param p left 
     * @param q mid
     * @param r right
     */
     private void merge(int[]arrayA, int p, int q, int r) {
         int n1 = q - p +1;
         int n2 = r - q;
         int[] arrayL = new int[n1 + 1];
         int[] arrayR = new int[n2 + 1];
         for (int i = 0; i < n1; i++) {
             arrayL[i] = arrayA[p+i];
         }
         arrayL[n1] = Integer.MAX_VALUE;
         for (int j = 0; j < n2; j++) {
             arrayR[j] = arrayA[q+j+1];
         }
         arrayR[n2] = Integer.MAX_VALUE;
         int m = 0;
         int n = 0;
         for (int k = p; k <= r; k++) {
             if (arrayL[m] <= arrayR[n]) {
                 arrayA[k] = arrayL[m];
                 m++;
             }
             else {
                 arrayA[k] = arrayR[n];
                 n++;
             }
         }
     }
     /**
     * merge sort, recursive, theta(nlgn)
     * @param arrayA
     * @param p
     * @param q
     */

     public void mergeSort(int[]arrayA, int p, int q) {
         if (p < q) {
             int r = (p + q)/2;
             mergeSort(arrayA, p, r);
             mergeSort(arrayA, r+1, q);
             merge(arrayA, p, r, q);
         }
     }
     /**
     *binary search, theta(lgn)  
     * @param A, sorted array
     * @param p, start index
     * @param q, end index
     * @param v, target value
     * @return if exist, return wanted index, else return -1
     */

     public int binarySearch(int[] arrayA, int p, int q, int v) {
         if (p > q) {
             return -1;
         }
         else {
             int m = (p + q)/2;
             if (arrayA[m] == v) {
                 return m;
             }
             else if (arrayA[m] > v) {
                 return binarySearch(arrayA, p, m-1, v);
             }
             else {
                 return binarySearch(arrayA, m+1, q, v);
             }
         }
     }

    /**
     * determine whether or not there exist two elements in array whose sum equals target ,
     * theta(nlgn) solution
     * 1.merge sort array, theta(nlgn)
     * 2.look for two elements, theta(n)
     * @param A, an array of integers
     * @param target
     * @return
     */

     public boolean twoSum(int[] arrayA, int target) {
         this.mergeSort(arrayA, 0, arrayA.length-1);
         int i = 0;
         int j = arrayA.length - 1;
         while (i < j) {
             if ((arrayA[i] + arrayA[j]) == target) {
                 return true;
             }
             else if ((arrayA[i] + arrayA[j]) < target) {
                 i++;
             }
             else if ((arrayA[i] + arrayA[j] > target)) {
                 j--;
             }
         }
         return false;
     }

    public void bubbleSort(int[] arrayA) {
        for (int i = 0; i < arrayA.length - 1; i++) {
            for (int j = arrayA.length - 1; j > i; j--) {
                if (arrayA[j] < arrayA[j-1]) {
                    int tmp = arrayA[j];
                    arrayA[j] = arrayA[j-1];
                    arrayA[j-1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] arrayA = new int[]{32, 21, 11, 8, 14, 67, 2, 4, 5};
        Chapter2 c2 = new Chapter2();
        System.out.print(c2.twoSum(arrayA, 98));
    }
}
