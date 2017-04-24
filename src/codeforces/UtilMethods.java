package codeforces;

/**
 * Created by sayef on 4/25/17.
 */
public class UtilMethods {
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
    public static String reverse(String input){
        char[] in = input.toCharArray();
        int begin=0;
        int end=in.length-1;
        char temp;
        while(end>begin){
            temp = in[begin];
            in[begin]=in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }
}
