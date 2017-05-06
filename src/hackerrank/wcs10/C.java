package hackerrank.wcs10;

import java.io.*;
import java.util.*;

public class C implements Runnable{

    class Point{
        Long x;
        Long y;
        int index;

        Point(Long x, Long y, int i){
            this.index = i;
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return this.index + " = (" + this.x + "," + this.y + ")";
        }
    }

    class Pair{
        int x;
        int y;
        Pair(int x, int y){ this.x = x; this.y = y;}

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    // solution
    void solve() throws IOException {
        while (true) {
            Integer n = readInt();
            if(n == null)
                break;


            points = new ArrayList<>();
            G = new List[1005];
            for(int i = 0; i < 1005; i++){
                G[i] = new ArrayList<>();
            }

            for(int i = 0; i < n-1; i++){
                Integer a = readInt();
                Integer b = readInt();
                G[a].add(b);
                G[b].add(a);
            }

            for(int i = 0; i < n; i++){
                Long a = readLong();
                Long b = readLong();
                points.add(new Point(a,b,i+1));
            }
            pairList=new ArrayList<>();

            visitedNodes = new boolean[1005];
            visitedPoints = new boolean[1005];
            for(int j = 0; j < visitedNodes.length; j++) {
                visitedNodes[j] = false;
                visitedPoints[j] = false;
            }

            convexHull = new ConvexHull();
            List<Point> convexPoints = convexHull.grahamScan(points);


            dfs(1, convexPoints);


            pairList.sort(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return (o1.x<o2.x)? -1:1;
                }
            });

            //System.out.println(pairList);


            print(pairList.get(0).y);
            for(int i = 1; i < pairList.size(); i++){
                print(" " + pairList.get(i).y);
            }

            println("");
        }
    }

    ConvexHull convexHull;
    boolean[] visitedNodes;// = new boolean[1005];
    boolean[] visitedPoints;// = new boolean[1005];
    List<Point> points;// = new ArrayList<>();
    List< Integer >[] G;// = new List[1005];
    List<Point> tempList = new ArrayList<>();
    List<Pair>pairList=new ArrayList<>();


    void dfs(int u, List<Point>convexPoints){

        Point now = convexPoints.get(0);

        pairList.add(new Pair(u, now.index));
        visitedNodes[u] = true;
        visitedPoints[now.index] = true;


        for(int i = 0; i< G[u].size(); i++){
            int v = G[u].get(i);
            if(visitedNodes[v] == false){
                convexPoints.clear();
                convexPoints.add(now);
                for (Point p: points){
                    if(visitedPoints[p.index] == false){
                        convexPoints.add(p);
                    }
                }

                convexPoints = convexHull.grahamScan(convexPoints);
                while (convexPoints.get(0).index != now.index){
                    convexPoints.add(convexPoints.get(0));
                    convexPoints.remove(0);
                }
                dfs(v, convexPoints);
            }
        }
    }


    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Thread(null, new C(), "", 256 * (1L << 20)).start();
    }

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
            //Properties props = System.getProperties();
            //props.list(System.out);
            if (System.getProperty("ONLINE_JUDGE") != null || System.getProperty("user.dir").contains("/run")) {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
            } else {
                in = new BufferedReader(new FileReader("src/hackerrank/input.txt"));
                out = new PrintWriter("src/hackerrank/output.txt");
            }
            Locale.setDefault(Locale.US);
            solve();
            in.close();
            out.close();
            long t2 = System.currentTimeMillis();
            System.err.println("Time = " + (t2 - t1));
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    String readString() throws IOException {
        try {
            while (!tok.hasMoreTokens()) {
                tok = new StringTokenizer(in.readLine());
            }
            return tok.nextToken();
        }catch (NullPointerException npe){
            return null;
        }
    }

    Integer readInt(){
        try {
            return Integer.parseInt(readString());
        }catch (Exception e){
            return null;
        }
    }

    Long readLong(){
        try {
            return Long.parseLong(readString());
        }catch (Exception e){
            return null;
        }
    }

    Double readDouble(){
        try {
            return Double.parseDouble(readString());
        }catch (Exception e){
            return null;
        }
    }
    void println(Object line){
        out.println(line);
    }

    void print(Object line){
        out.print(line);
    }

    class ConvexHull {
        /*public class Point {
            Long x, y;
            Point(Long x, Long y){
                this.x = x;
                this.y = y;
            }

            @Override
            public String toString() {
                return x+","+y;
            }
        }*/

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

}