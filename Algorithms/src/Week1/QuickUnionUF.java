package Week1;

/**
 * 
 * @author wangzetian
 *quick-find defect: union too expensive; trees are flat, but too expensive to keep them flat
 *quick-union defect: tress can get tall; find too expensive (could be N array accesses)
 */
public class QuickUnionUF {

	private int[] id;
	
	/**
	 * N array accesses
	 */
	public QuickUnionUF (int N) {
		id = new int [N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	/**
	 * depth of i array accesses
	 */
	public int root(int i) {
		while (i != id[i]) {
			i = id[i];
		}
		return i;
	}
	
	/**
	 * depth of p and q array accesses, N in worst case 
	 */
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	/**
	 * depth of p and q array accesses, N in worst case 
	 */
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}
