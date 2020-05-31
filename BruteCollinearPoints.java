import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> allFourSegments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException("Constructor doesn't work with null params");
        for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("There are Some Points Duplicated!!!!!!!");
      
        Point[] auxCopy = new Point[points.length];
        for(int k = 0; k < auxCopy.length; k++)
            auxCopy[k] = points[k];
        Arrays.sort(auxCopy);
       
        int four = 4;
        for (int p = 0; p < auxCopy.length - (four - 1); p++) 
            for (int q = p + 1; q < auxCopy.length - (four - 2); q++) 
                for (int r = q + 1; r < auxCopy.length - (four - 3); r++) 
                    for (int s = r + 1; s < auxCopy.length; s++) 
                        if (auxCopy[p].slopeTo(auxCopy[r]) == auxCopy[p].slopeTo(auxCopy[q]) &&
                                auxCopy[p].slopeTo(auxCopy[q]) == auxCopy[p].slopeTo(auxCopy[s])) 
                            allFourSegments.add(new LineSegment(auxCopy[p], auxCopy[s]));
    }
    public int numberOfSegments() {
        return allFourSegments.size();
    }
    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[allFourSegments.size()];
        return allFourSegments.toArray(segments);
    }
}