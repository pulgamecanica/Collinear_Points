import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class FastCollinearPoints {
	private ArrayList<LineSegment> allFourSegments = new ArrayList<LineSegment>();
 	private final Point[] pointsCopy;
    public FastCollinearPoints(Point[] points)  {
    	if (points == null)
    		throw new NullPointerException("Constructor doesn't work with null params");
        for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("There are Some Points Duplicated!!!!!!!");

        pointsCopy = Arrays.copyOf(points, points.length);
        List<Point> slopesAt = new ArrayList<>();
        for (Point startPoint : points) {
        	Arrays.sort(pointsCopy, startPoint.slopeOrder());
            double loSlope = 0.0;
            double prevSlope = Double.POSITIVE_INFINITY;
            int i;
	        for(i = 1; i < points.length; i++){
	        	loSlope = startPoint.slopeTo(pointsCopy[i]);
	        	if(loSlope == prevSlope)
	        		slopesAt.add(pointsCopy[i]);
	        	else{
	        		if (slopesAt.size() >=3) {
	        			//slopesAt.add(startPoint);
	        			//Need to check if is repeated
	        			if(checkSegment(slopesAt, startPoint))
	        				allFourSegments.add(new LineSegment(startPoint, pointsCopy[i]));
	        		}
	        		slopesAt.clear();
	        		slopesAt.add(pointsCopy[i]); //needed??????????
	        	}
		        if(slopesAt.size() >=3){
		        	slopesAt.add(startPoint);
		        	if(checkSegment(slopesAt, startPoint))
		        		allFourSegments.add(new LineSegment(startPoint, pointsCopy[i-1]));
		        	slopesAt.clear();
		        }
		        prevSlope = loSlope;
	    	}
	    }
    }   
    private boolean checkSegment(List<Point> slopesAt, Point startPoint){
    	Point maxPoint = null;
    	Point minPoint = null;
    	for(Point x : slopesAt){
    		if(maxPoint == null)
    			maxPoint = x;
    		if(minPoint == null)
    			minPoint = x;
    		if(minPoint != null && x.compareTo(minPoint) > 0)
    			minPoint = x;
    		if(maxPoint != null && x.compareTo(maxPoint) < 0)
    			maxPoint = x;
    	}
    	if(startPoint.compareTo(maxPoint) < 0 || startPoint.compareTo(minPoint) > 0)
    		return false;
    	return true;
    }
    public int numberOfSegments() {
    	return allFourSegments.size();
    }       
    public LineSegment[] segments() {
    	return allFourSegments.toArray(new LineSegment[allFourSegments.size()]);
    }       

}