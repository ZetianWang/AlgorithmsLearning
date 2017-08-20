package Week1;

/**
 * 
 * @author wangzetian
 *cost model: Number of array access (for read or write).
 *quick-find -- initialize: N; union: N; find: 1 
 *takes N2 array access to process sequence of N union commands on N objects.
 *quadratic time algorithms are not accepted for large scale problem. they don't scale with technology 
 */
public class QuickFindUF {
	private int[] id;
	
	public QuickFindUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public boolean connetced(int p, int q) {
		return id[p] == id[q];
	}

	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pid) id[i] = qid;
		}
		
	}
}
