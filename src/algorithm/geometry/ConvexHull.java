package algorithm.geometry;

import java.util.*;

/**
 * Created by sayef on 5/6/17.
 */

class ConvexHull {
    public class Point {
        Long x, y;
        Point(Long x, Long y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x+","+y;
        }
    }

    // A globle point needed for  sorting points with reference
    // to  the first point Used in compare function of sort()
    Point referencePoint;

    // A utility function to find next to top in a stack
    Point nextToTop(Stack<Point>S) {
        Point p = S.peek(); S.pop();
        Point res = S.peek(); S.push(p);
        return res;
    }

    // A utility function to return square of distance
    // between p1 and p2
    Long distSquare(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
    enum ORIENTATION {
        ANTICLOCKWISE(-1),
        COLLINEAR(0),
        CLOCKWISE(1);
        private final int id;
        ORIENTATION(int id) {this.id = id;}
        public int getValue() {return id;}

        public static ORIENTATION fromValue(int id) {
            for (ORIENTATION orientation: values()) {
                if (orientation.getValue() == id) {
                    return orientation;
                }
            }
            return null;
        }
    }
    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are collinear
    // +1 --> Clockwise
    // -1 --> Counterclockwise
    int orientation(Point p, Point q, Point r) {
        Long val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return ORIENTATION.COLLINEAR.id;  // collinear
        return (val > 0) ? ORIENTATION.CLOCKWISE.id : ORIENTATION.ANTICLOCKWISE.id; // clock or counter clock wise
    }


    // Prints convex hull of a set of n points.
    List<Point> grahamScan(List<Point> points) {
        // Find the bottommost point
        Long yMin = points.get(0).y;
        int min = 0;
        for (int i = 1; i < points.size(); i++) {
            Long y = points.get(i).y;

            // Pick the bottom-most or chose the left
            // most point in case of tie
            if ((y < yMin) || (yMin == y && points.get(i).x < points.get(min).x)){
                yMin = points.get(i).y;
                min = i;
            }
        }

        // Place the bottom-most point at first position
        Point temp = points.get(0); points.set(0, points.get(min)); points.set(min, temp);


        // Sort n-1 points with respect to the first point.
        // A point p1 comes before p2 in sorted ouput if p2
        // has larger polar angle (in counterclockwise
        // direction) than p1
        referencePoint = points.get(0);

        points.remove(0);
        points.sort(new Comparator<Point>() {
            // A function used by library function sort() to sort an array of
            // points with respect to the first point
            @Override
            public int compare(Point p1, Point p2) {
                // Find orientation
                int o = orientation(referencePoint, p1, p2);
                if (o == 0)
                    return distSquare(referencePoint, p2) >= distSquare(referencePoint, p1) ? -1 : 1;
                return o;
            }
        });
        List<Point>convexHullPoints = new ArrayList<>();
        // If two or more points make same angle with referencePoint,
        // Remove all but the one that is farthest from referencePoint
        // Remember that, in above sorting, our criteria was
        // to keep the farthest point at the end when more than
        // one points have same angle.
        convexHullPoints.add(referencePoint);
        for (int i = 0; i < points.size(); i++) {
            // Keep removing i while angle of i and i+1 is same
            // with respect to referencePoint
            while (i < points.size() - 1 && orientation(referencePoint, points.get(i), points.get(i + 1)) == ORIENTATION.COLLINEAR.id) {
                i++;
            }
            convexHullPoints.add(points.get(i));
        }

        // If modified array of points has less than 3 points,
        // convex hull is not possible
        if (convexHullPoints.size() < 3) return convexHullPoints;

        // Create an empty stack and push first three points
        // to it.
        Stack<Point>S = new Stack<>();
        S.push(convexHullPoints.get(0));
        S.push(convexHullPoints.get(1));
        S.push(convexHullPoints.get(2));

        // Process remaining n-3 points
        for (int i = 3; i < convexHullPoints.size(); i++) {
            // Keep removing top while the angle formed by
            // points next-to-top, top, and points[i] makes
            // a non-left turn
            while (orientation(nextToTop(S), S.peek(), convexHullPoints.get(i)) != ORIENTATION.ANTICLOCKWISE.id)
                S.pop();
            S.push(convexHullPoints.get(i));
        }

        convexHullPoints.clear();
        // Now stack has the output points, print contents of stack
        while (!S.empty()) {
            Point p = S.peek();
            convexHullPoints.add(p);
            S.pop();
        }

        Collections.reverse(convexHullPoints);

        return convexHullPoints;
    }
}