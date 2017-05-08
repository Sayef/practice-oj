package codeforces.c_412_2;

import java.io.*;
import java.util.*;

public class A implements Runnable{

    // solution
    void solve() throws IOException {
        while (true) {
            Integer n = readInt();
            if(n == null){
                break;
            }

            class Rating{
                int prev;
                int curr;
                int order;

                public Rating(int prev, int curr, int order) {
                    this.prev = prev;
                    this.curr = curr;
                    this.order = order;
                }
            }
            List<Rating> rating = new ArrayList<Rating>();
            boolean rated = false;
            for (int i = 0; i < n; i++){
                int a = readInt();
                int b = readInt();
                rating.add(new Rating(a,b,i));
                if(a!=b){
                    rated = true;
                }
            }

            if(rated == false){
                int last = rating.get(0).curr;
                int i;
                for (i = 1; i < n; i++){
                    if(rating.get(i).curr > last){
                        break;
                    }else{
                        last = rating.get(i).curr;
                    }
                }
                if(i == n){
                    println("maybe");
                }else{
                    println("unrated");
                }
            }else{
                println("rated");
            }

        }
    }

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Thread(null, new A(), "", 256 * (1L << 20)).start();
    }

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
            if (System.getProperty("ONLINE_JUDGE") != null) {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
            } else {
                in = new BufferedReader(new FileReader("src/codeforces/input.txt"));
                out = new PrintWriter("src/codeforces/output.txt");
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