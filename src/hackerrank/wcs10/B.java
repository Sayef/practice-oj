package hackerrank.wcs10;

import java.io.*;
import java.util.*;

public class B implements Runnable{

    // solution
    void solve() throws IOException {
        while (true) {
            Integer n = readInt();
            if(n == null)
                break;
            Integer[] list = new Integer[n];

            for(int i = 0; i < n; i++){
                list[i] = readInt();
            }
            if(n < 3){
                println("0");
            }else {
                int cnt = Integer.min(giveCount(list, 2), giveCount(list, 3));
                println(cnt);
            }
        }
    }

    int giveCount(Integer[] list, int x){
        int up = 0;
        int dwn = 0;
        int last = list[0];
        int cnt = 0;
        for (int i = 1; i < list.length; i++) {
            if (list[i] > last) {
                up++;
                dwn = 0;
            } else if (list[i] < last) {
                dwn++;
                up = 0;
            } else {
                up = dwn = 0;
            }

            last = list[i];

            if (up == 2) {
                cnt++;
                up = 1;
                if(x==3)
                    last = list[i - 1];
            } else if (dwn == 2) {
                cnt++;
                dwn = 1;
                if(x==3)
                    last = list[i - 1];
            }
        }

        return cnt;

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