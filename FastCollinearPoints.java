import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;
public class FastCollinearPoints {
	private List<LineSegment> allFourSegments = new ArrayList<LineSegment>();
	private HashMap<Double, List<Point>> foundSegments = new HashMap<>();
    public FastCollinearPoints(Point[] points)  {
    	if (points == null)
    		throw new NullPointerException("Constructor doesn't work with null params");
       	for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("There are Some Points Duplicated!!!!!!!");
      	Point[] pointsCopy = Arrays.copyOf(points, points.length);
      	for (Point startPoint : points) {
      		List<Point> slopePoints = new ArrayList<>();
            Arrays.sort(pointsCopy, startPoint.slopeOrder());
            double slopeBefore = Double.NEGATIVE_INFINITY;
            double slope = 0;
            for (int i = 1; i < pointsCopy.length; i++) {
                slope = startPoint.slopeTo(pointsCopy[i]);
                if (slope == slopeBefore)
                    slopePoints.add(pointsCopy[i]);
                else if (slopePoints.size() >= 3) {
                        slopePoints.add(startPoint);
                        checkSegment(slopePoints, slopeBefore);
                        slopePoints.clear();
                    	slopePoints.add(pointsCopy[i]);
                    }
                else{
                   	slopePoints.clear();
                    slopePoints.add(pointsCopy[i]);
                }
                slopeBefore = slope;
            }

            if (slopePoints.size() >= 3) {
                slopePoints.add(startPoint);
                checkSegment(slopePoints, slope);
            }
        }
    }   
      private void checkSegment(List<Point> slopePoints, double slope) {
        List<Point> endPoints = foundSegments.get(slope);
        Collections.sort(slopePoints);
        Point startPoint = slopePoints.get(0);
        Point endPoint = slopePoints.get(slopePoints.size() - 1);
        if (endPoints == null) {
            endPoints = new ArrayList<>();
            endPoints.add(endPoint);
            foundSegments.put(slope, endPoints);
            allFourSegments.add(new LineSegment(startPoint, endPoint));
        } else {
            for (Point currentEndPoint : endPoints) {
                if (currentEndPoint.compareTo(endPoint) == 0) {
                    return;
                }
            }
            endPoints.add(endPoint);
            allFourSegments.add(new LineSegment(startPoint, endPoint));
        }
    }

    public int numberOfSegments() {
    	return allFourSegments.size();
    }       
    public LineSegment[] segments() {
    	return allFourSegments.toArray(new LineSegment[allFourSegments.size()]);
    }       


}