import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private int count;
    private LineSegment[] ls;
    
    
    public FastCollinearPoints(Point[] points) {
        ls = new LineSegment[1];
        count = 0;
        int n = points.length;
        Arrays.sort(points, 0, n);
        for (int i = 0; i < n - 1; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            Point start = points[i];           
            Arrays.sort(points, i + 1, n, start.slopeOrder());
            int np = 0;
            double lastSlope = Double.NEGATIVE_INFINITY;
            for (int j = i + 1; j < n; j++) {
                if (start.equals(points[j])) throw new IllegalArgumentException();
                if (start.slopeTo(points[j]) == lastSlope) {
                    np++;
                    if (j == n - 1 && np >= 3) {
                        if (count == ls.length) resize(2 * ls.length);
                        ls[count++] = new LineSegment(start, points[j]);                        
                    }
                }
                else {
                    if (np >= 3) {
                        if (count == ls.length) resize(2 * ls.length);
                        ls[count++] = new LineSegment(start, points[j - 1]);
                    }
                    lastSlope = start.slopeTo(points[j]);
                    np = 1;
                }
            }
            Arrays.sort(points, 0, n);
        }
        resize(count);
    }
    
    public int numberOfSegments() {
        return count;
    }
    
    public LineSegment[] segments() {
        return ls;
    }
    // if pass ls, this method does not work.
    private void resize(int len) {
        LineSegment[] copy = new LineSegment[len];
        for (int i = 0; i < count; i++) {
            copy[i] = ls[i];
        }
        ls = copy;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // read the n points from a file
        In in = new In("CollinearInput/input9.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }

}
