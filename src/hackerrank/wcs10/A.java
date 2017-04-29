package hackerrank.wcs10;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

public class A implements Runnable{

    // solution
    void solve() throws IOException {
        Integer a, b, c;
        while (true) {
            a = readInt();
            b = readInt();
            c = readInt();

            if (a == null || b == null || c == null) {
                break;
            }

            if (a * 10 > 100)
                a = 100;
            else
                a = a * 10;
            if (b * 10 > 100)
                b = 100;
            else
                b = b * 10;
            if (c * 10 > 100)
                c = 100;
            else
                c = c * 10;

            println((a+b+c));
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