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
	        			allFourSegments.add(new LineSegment(startPoint, pointsCopy[i]));
	        		}
	        		slopesAt.clear();
	        		slopesAt.add(pointsCopy[i]); //needed??????????
	        	}
		        if(slopesAt.size() >=3){
		        	slopesAt.add(startPoint);
		        	allFourSegments.add(new LineSegment(startPoint, pointsCopy[i-1]));
		        	slopesAt.clear();
		        }
		        prevSlope = loSlope;
	    	}
	    }
    }   
    public int numberOfSegments() {
    	return allFourSegments.size();
    }       
    public LineSegment[] segments() {
    	return allFourSegments.toArray(new LineSegment[allFourSegments.size()]);
    }       

}