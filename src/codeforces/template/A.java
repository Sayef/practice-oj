package codeforces.template;

import java.io.*;
import java.util.*;

public class A implements Runnable{

    // solution
    void solve() throws IOException {
        int n = readInt();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(readInt());
        }

        for(int x: list){
            print(x + " ");
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
        System.out.println(line);
    }

    void print(Object line){
        System.out.print(line);
    }

}