package algorithm.math;

/**
 * Created by sayef on 5/9/17.
 */
public class Main {
    public static void  main(String[] args){
        MathUtils math = new MathUtils();
        Long a = 8L, b = 3L, c = -56L;
        Long d = math.linearDiophantine(a,b,c);
        System.out.println("x = " + b + "*i + " + d);
    }
}
