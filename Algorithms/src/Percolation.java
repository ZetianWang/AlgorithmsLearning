import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private WeightedQuickUnionUF grid1;
    private WeightedQuickUnionUF grid2;
    private boolean[] gridstate;
    private int count;

    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = n;
        int num1 = n * n + 2;
        int num2 = n * n + 1;
        grid1 = new WeightedQuickUnionUF(num1);
        grid2 = new WeightedQuickUnionUF(num2);
        for (int i = 1; i <= n; i++) {
            grid1.union(0, i);
            grid1.union(num1 - 1, n * (n - 1) + i);
            grid2.union(0,  i);
        }
        gridstate = new boolean[num1];
        for (int j = 1; j < num1 - 1; j++) {
            gridstate[j] = false;
        }
        gridstate[0] = true;
        gridstate[num1 - 1] = true;
        count = 0;
    }

    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new java.lang.IllegalArgumentException("site out of grid in open");
        }  
        if (!isOpen(row, col)) {
            gridstate[getId(row, col)] = true;
            count++;
            if ((row - 1) >= 1 && isOpen(row - 1, col)) {
                grid1.union(getId(row - 1, col), getId(row, col));
                grid2.union(getId(row - 1, col), getId(row, col));
            }
            if ((row + 1) <= size && isOpen(row + 1, col)) {
                grid1.union(getId(row + 1, col), getId(row, col));
                grid2.union(getId(row + 1, col), getId(row, col));
            }
            if ((col - 1) >= 1 && isOpen(row, col - 1)) {
                grid1.union(getId(row, col - 1), getId(row, col));
                grid2.union(getId(row, col - 1), getId(row, col));
            }
            if ((col + 1) <= size && isOpen(row, col + 1)) {
                grid1.union(getId(row, col + 1), getId(row, col));
                grid2.union(getId(row, col + 1), getId(row, col));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new java.lang.IllegalArgumentException("site out of grid in isOpen");
        }  
        return gridstate[getId(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new java.lang.IllegalArgumentException("site out of grid in isFull");
        }  
        return isOpen(row, col) && grid2.connected(getId(row, col), 0);
    }
    
    public int numberOfOpenSites() {
//        int count = 0;
//        for (int i = 1; i <= size * size; i++) {
//            if (gridstate[i] == 1) {
//                count++;
//            }
//        }
        return count;

    }

    public boolean percolates() {
        if (size == 1) {
            return isOpen(1, 1);
        }
        else {
            return grid1.connected(0, size * size + 1);
        }       
    }

    private int getId(int row, int col) {
        return (row - 1) * size + col;
    }

    public static void main(String[] args) {

    }

}
