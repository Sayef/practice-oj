package codeforces.c_412_2;
import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class C implements Runnable{

    // solution
    void solve() throws IOException {
        while (true) {
            Integer n = readInt();
            if(n == null){
                break;
            }
            while (n -- > 0){
                Long a = readLong();
                Long b = readLong();
                Long p = readLong();
                Long q = readLong();
                if(p==0L){
                    if(a>0L)
                        println("-1");
                    else println("0");
                }
                else {

                    Long i = (long) Math.ceil((double) (b) / (double) (q));
                    Long r =  Long.MAX_VALUE / q + 1L;

                    Long l = i;

                    while ( l < r) {
                        Long mid = (l + r)/2L;

                        Long x = q * mid - b;
                        Long y = mid * p;

                        if (a <= y && y <= a + x) {
                            r = mid;
                        }else{
                            l = mid + 1;
                        }
                    }

                    Long mid = l;
                    Long x = q * mid - b;
                    Long y = mid * p;
                    if (a <= y && y <= a + x) {
                        println(q * l - b);
                    }else{
                        println("-1");
                    }
                }

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
                in = new BufferedReader(new FileReader("src/input.txt"));
                out = new PrintWriter("src/output.txt");
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