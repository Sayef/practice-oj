package algorithm.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by sayef on 5/6/17.
 */
public class Main {
    public static void main(String[] args){
       testConvexHull();
    }

    static void testConvexHull(){
        ConvexHull convexHull = new ConvexHull();
        int[] x = {0, 1, 2, 4, 0, 1, 3, 3};
        int[] y = {3, 1, 2, 4, 0, 2, 1, 3};
        List<ConvexHull.Point>points = new ArrayList<>();

        for(int i = 0 ; i < x.length; i++){
            points.add(convexHull.new Point(Integer.toUnsignedLong(x[i]), Integer.toUnsignedLong(y[i])));
        }
        points = convexHull.grahamScan(points);

        System.out.println(points);
    }
}
