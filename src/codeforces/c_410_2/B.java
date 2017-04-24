package codeforces.c_410_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class B implements Runnable{

    // solution
    void solve() throws IOException {
        while (true){
            Integer n = readInt();
            if(n == null)
                break;
            List<String>list = new ArrayList<>();
            List<String>revList = new ArrayList<>();
            for(int i = 0; i < n; i ++){
                String str = readString();
                list.add(str);
                revList.add(str+str);
            }

            boolean flag = true;
            int ans = Integer.MAX_VALUE;
            for(int i = 0; i < list.size(); i++){
                String str = list.get(i);
                int temp = 0;
                for(int j = 0; j < revList.size(); j++){
                    String revStr = revList.get(j);
                    if(i != j) {
                        if (!revStr.contains(str)) {
                            flag = false;
                            break;
                        } else {
                            //System.out.println(revStr.indexOf(str));
                            temp += revStr.indexOf(str);
                        }
                    }
                }
                if(!flag){
                    break;
                }else{
                    //System.out.println(temp);
                    ans = Integer.min(temp, ans);
                }
            }

            if(flag){
                println(ans);
            }else{
                println("-1");
            }
        }
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

    void println(Object line) {
        out.println(line);
    }

    void print(Object line){
        out.print(line);
    }

}