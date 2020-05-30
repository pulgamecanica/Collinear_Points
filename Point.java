/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/
import java.util.*;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if(that.x == this.x){
            if(that.y == this.y)
                return Double.NEGATIVE_INFINITY;
            return Double.POSITIVE_INFINITY;
        }
        else if(that.y == this.y)
            return 0.0;
        else
            return (double) (that.y - this.y) / (that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if( that.x == x && that.y == y) return 0;
        if(that.y > y || that.y == y && that.x > x) return -1;
        return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new TheSlope();
    }

    private class TheSlope implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double firstSlope = slopeTo(p1);
            double secondSlope = slopeTo(p2);
            if (firstSlope < secondSlope) return -1;
            if (firstSlope == secondSlope) return 0;
            return 1;
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        Point a = new Point(10000,20221);
        Point a1 = new Point(10000,20222);
        Point a2 = new Point(10000,20223);
        Point a3 = new Point(10000,20224);
        Point a4 = new Point(10000,20225);
        Point a5 = new Point(10000,20226);
        Point a6 = new Point(10000,20227);
        Point a7 = new Point(10000,20228);
        Point a8 = new Point(10000,20229);
        Point a9 = new Point(1000,202210);
        Point a10 = new Point(10000,202211);
        Point b = new Point(3,1);
        Point c = new Point(-1,-2);
        Point d = new Point(-1,3);
        System.out.println(c.slopeTo(a) + " Inclination :)");
        System.out.println(b.compareTo(d) + " This should be -1");
        System.out.println(b.equals(d) + " This should be -1");
        System.out.println("***************************************************************************************************");
        a.draw();
        a1.draw();
        a2.draw();
        a3.draw();
        a4.draw();
        a5.draw();
        a6.draw();
        a7.draw();
        a8.draw();
        a9.draw();
        a10.draw();
        b.draw();
        c.draw();
        d.draw();
        a.drawTo(b);
        StdDraw.show();
    }
}
