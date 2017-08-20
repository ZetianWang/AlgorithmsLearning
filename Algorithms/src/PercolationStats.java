import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result;
    private int t;
    private double m;
    private double s;

    public PercolationStats(int n, int trials) { 
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException("bad input");
        }
        result = new double[trials];
        t = trials;
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            result[i] = (double) p.numberOfOpenSites() / (double) (n * n);
        }
        m = StdStats.mean(result);
        s = StdStats.stddev(result);
    }
    
    public double mean() {
        return m;
    }
    
    public double stddev() {
        return s;
    }
    
    public double confidenceLo() {
        return m - (1.96 * s / Math.sqrt(t));
    }
    
    public double confidenceHi() {
        return m + (1.96 * s / Math.sqrt(t));
    }
    
    public static void main(String[] args) {
        

    }

}
