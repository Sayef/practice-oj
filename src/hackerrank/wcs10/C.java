package hackerrank.wcs10;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.*;

public class C implements Runnable{
    class Point{
        int x;
        int y;
        int index;

        Point(int x, int y, int i){
            this.index = i;
            this.x = x;
            this.y = y;
        }
        Point(int x, int y){
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

            Point lowestPoint = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE,-1);
            for(int i = 0; i < n; i++){
                int a = readInt();
                int b = readInt();
                points.add(new Point(a,b,i+1));
                if(b < lowestPoint.y){
                    lowestPoint = new Point(a,b,i+1);
                }
            }

            //System.out.println(points);
            visitedNodes = new boolean[1005];
            visitedPoints = new boolean[1005];
            for(int j = 0; j < visitedNodes.length; j++) {
                visitedNodes[j] = false;
                visitedPoints[j] = false;
            }

            List<Pair>pairList=new ArrayList<>();
            Point now = lowestPoint;
            int u = n;
            dfs(u,new Point(0,0,-1),now,pairList);

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

    boolean[] visitedNodes;// = new boolean[1005];
    boolean[] visitedPoints;// = new boolean[1005];
    List<Point> points;// = new ArrayList<>();
    List< Integer >[] G;// = new List[1005];

    void dfs(int u, Point last, Point now, List<Pair>pairList){
        visitedNodes[u] = true;
        visitedPoints[now.index] = true;
        pairList.add(new Pair(u, now.index));

        for(int i = 0; i< G[u].size(); i++){
            int v = G[u].get(i);
            if(visitedNodes[v] == false){
                //System.out.println("Last: " + last);
                //System.out.println("Now: " + now);
                last = getNextAntiClockPoint(last, now);
                //System.out.println("Next: " + last);
                dfs(v, now, last, pairList);
            }
        }
    }

    public double angleABC(Point A, Point B, Point C){
        double a = Math.sqrt(  (B.x - C.x)*(B.x - C.x) + (B.y - C.y)*(B.y - C.y) );
        double b = Math.sqrt(  (A.x - C.x)*(A.x - C.x) + (A.y - C.y)*(A.y - C.y) );
        double c = Math.sqrt(  (A.x - B.x)*(A.x - B.x) + (A.y - B.y)*(A.y - B.y) );
        return Math.acos((a*a + c*c - b*b) / (2*a*c));
    }

    public int onAntiClockSide(Point A, Point B, Point C){
        int d = (C.x - A.x) * (B.y - A.y) - (C.y - A.y)*(B.x - A.x);
        return d;
    }

    public Point getNextAntiClockPoint(Point A, Point B){

        double minAngle = Double.MAX_VALUE;
        double maxAngle = Double.MIN_VALUE;

        Point nextAntiClockPoint = new Point(0,0,-1);
        for(int i = 0; i < points.size(); i++){
            if(visitedPoints[points.get(i).index] ==false && B.index != points.get(i).index && A.index != points.get(i).index){
                double angle = Math.toDegrees(angleABC(A, B, points.get(i)));
                if(angle <= minAngle   && onAntiClockSide(A, B, points.get(i)) >= 0){
                    minAngle = angle;
                    nextAntiClockPoint = points.get(i);
                }
                else if(angle >= maxAngle && onAntiClockSide(A, B, points.get(i)) <= 0){
                    maxAngle = angle;
                    nextAntiClockPoint = points.get(i);
                }
            }
        }

        return nextAntiClockPoint;
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

}