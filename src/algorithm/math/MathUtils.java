package algorithm.math;

/**
 * Created by sayef on 5/9/17.
 */
public class MathUtils {

    int gcd(int p, int q) {
        if (q == 0) return p;
        if (p == 0) return q;
        // p and q even
        if ((p & 1) == 0 && (q & 1) == 0) return gcd(p >> 1, q >> 1) << 1;
            // p is even, q is odd
        else if ((p & 1) == 0) return gcd(p >> 1, q);
            // p is odd, q is even
        else if ((q & 1) == 0) return gcd(p, q >> 1);
            // p and q odd, p >= q
        else if (p >= q) return gcd((p-q) >> 1, q);
            // p and q odd, p < q
        else return gcd(p, (q-p) >> 1);
    }

    int lcm(int a, int b){
        int gcd = gcd(a, b);
        return (a * b) / gcd;
    }

    Long linearDiophantine(Long a, Long b, Long c) throws ArithmeticException{
        a %= b;
        if(c < b){
            Long diff = b - c;
            Long i = diff / b;
            c += b * (i + 1);
        }

        c %= b;

        while (a > 1) {
            int gcd = gcd(a.intValue(), c.intValue());
            a /= gcd;
            c /= gcd;

            if(a == 1)
                break;

            a %= b;
            if(c < b){
                Long diff = b - c;
                Long i = diff / b;
                c += b * (i + 1);
            }

            c %= b;


            Long i = a  - c % a;
            c += b * i;
        }
        return c;
    }

}
