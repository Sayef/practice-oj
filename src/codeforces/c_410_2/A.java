package codeforces.c_410_2;

import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class A implements Runnable{

    // solution
    void solve() throws IOException {
        while (true) {
            String str = readString();
            if(str == null){
                break;
            }
            int cnt = 0;
            for (int i = 0; i <= str.length() / 2; i++) {
                String rep = replaceCharAt(str, i, str.charAt(str.length() - i - 1));
                if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                    if (isPalindrome(rep)){
                        cnt = 1;
                        break;
                    }
                }else if(str.length() % 2 != 0 && i == str.length() / 2){
                    if (isPalindrome(rep)){
                        cnt = 1;
                        break;
                    }
                }
            }

            if (cnt == 1) {
                println("YES");
            } else {
                println("NO");
            }
        }
    }

    boolean isPalindrome(String str){
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    String replaceCharAt(String str, int i, char c) {
        if(i<0 || i>=str.length())
            return null;
        char[] Char = str.toCharArray();
        Char[i] = c;
        return String.copyValueOf(Char);
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