import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class SampleClient {

    public static void main(String[] args) {
        Point[] points = getAllPoints(args[0]);

        // draw the points
        // StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        // BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment + " Segment Founded");
            segment.draw();
        }

    }

    public static Point[] getAllPoints(String fileName) {
        In in = new In (fileName);
        int numPoints = in.readInt();
        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }
}
