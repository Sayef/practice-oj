package codeforces.c_412_2;

import java.io.*;
import java.util.*;

/**
 * Created by sayef on 5/7/17.
 */


public class B implements Runnable{

    // solution
    void solve() throws IOException {
        while (true) {
            Integer p = readInt();
            if(p == null){
                break;
            }

            int x = readInt();
            int y = readInt();

            if(tshirtPositions(x,p)){
                println("0");
            }else {

                int start = x;
                int cnt = 0;
                boolean found = false;
                while (true) {
                    cnt++;
                    if (start - 50 < y) {
                        break;
                    }
                    if (tshirtPositions(start - 50, p)) {
                        found = true;
                        break;
                    }
                    start -= 50;
                }

                if (found == true) {
                    //System.out.println(start-50 + " " + y);
                    println("0");
                } else {

                    start = x;
                    cnt = 0;
                    found = false;
                    while (true) {
                        cnt++;
                        if (tshirtPositions(start + 50, p)) {
                            found = true;
                            break;
                        }
                        start += 50;
                    }
                    if (found == true) {
                        if (cnt % 2 == 0) {
                            println(cnt / 2);
                        } else {
                            println((cnt + 1) / 2);
                        }
                    }

                }
            }
        }
    }

    boolean tshirtPositions(int s, int pos){
        s = ((int)(s / 50)) % 475;
        for(int i = 0; i < 25; i++){
            s = ((int)(s * 96 + 42)) % 475;
            if(26+s == pos)
                return true;
        }
        return false;
    }

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Thread(null, new B(), "", 256 * (1L << 20)).start();
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