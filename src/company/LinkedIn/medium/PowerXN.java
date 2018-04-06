package company.LinkedIn.medium;

/**
 * LC 50
 * Implement pow(x, n).
     Example 1:

     Input: 2.00000, 10
     Output: 1024.00000
     Example 2:

     Input: 2.10000, 3
     Output: 9.26100
 */
public class PowerXN {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        int t = n / 2;
        if (n < 0) {
            t = -t;
            x = 1 / x;
        }
        double res = myPow(x, t);
        if (n % 2 == 0) return res * res;
        return res * res * x;
    }
    /**
    HashMap<Integer, Double> map = new HashMap();
    public double solution(double x, int n) {
        int absN = Math.abs(n);
        map.put(0, new Double(1));
        map.put(1, x);
        if (n >= 0)
            return help(x, absN);
        else
            return 1 / help(x, absN);
    }
    public double help(double x, int n) {
        Double value = map.get(n);
        if (value != null)  return value;
        int t = n / 2;
        double res = help(x, t);
        double temp;
        if (n % 2 == 0)
            temp = res * res;
        else
            temp = res * res * x;
        map.put(t, temp);
        return temp;
    }
    */

    public static void main(String[] args) {
        PowerXN p = new PowerXN();
        System.out.println(p.myPow(-3,3));
    }
}
