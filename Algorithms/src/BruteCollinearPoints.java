import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private LineSegment[] ls;
    private int count;
    
    /**
     * finds all line segments contains 4 points
     * @param points array of all points
     */
    public BruteCollinearPoints(Point[] points) {
        ls = new LineSegment[1];
        count = 0;
        int n = points.length;
        Arrays.sort(points, 0, n);
        Point pre = null;
        for (int p1 = 0; p1 < n - 3; p1++) {
            Point start = points[p1];
            if (start == null) throw new IllegalArgumentException();
            if (start.equals(pre)) throw new IllegalArgumentException();
            pre = start;
            for (int p2 = p1 + 1; p2 < n - 2; p2++) {
                for (int p3 = p2 + 1; p3 < n - 1; p3++) {
                    if (start.slopeTo(points[p2]) == start.slopeTo(points[p3])) {
                        for (int p4 = p3 + 1; p4 < n; p4++) {
                            if (start.slopeTo(points[p2]) == start.slopeTo(points[p4])) {
                                if (count == ls.length) {
                                    resize(2 * ls.length);
                                }
                                ls[count++] = new LineSegment(start, points[p4]);
                            }
                        }
                    }
                    else continue;
                }
            }
        }
        resize(count);
    }
    
    /**
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return count;
    }
    
    /**
     * 
     * @return an array of all satisfied segments 
     */
    public LineSegment[] segments() { 
        return ls;
    }
    
    private void resize(int len) {
        LineSegment[] copy = new LineSegment[len];
        for (int i = 0; i < count; i++) {
            copy[i] = ls[i];
        }
        ls = copy;
    }

    public static void main(String[] args) {
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
