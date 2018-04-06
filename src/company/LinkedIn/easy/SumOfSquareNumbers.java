package company.LinkedIn.easy;

/**
 * LC 633
 Given a non-negative integer c, your task is to decide whether there're two integers a and b
 such that a^2 + b^2 = c.

 Example 1:
 Input: 5
 Output: True
 Explanation: 1 * 1 + 2 * 2 = 5

 Example 2:
 Input: 3
 Output: False

 */
public class SumOfSquareNumbers {
    public boolean judgeSquareSum(int c) {
        if (c < 0)  return false;
        if (c == 0 || c == 1)   return true;
        int left = 0, right = (int)Math.sqrt(c);
        while (left <= right) {
            int curr = left * left + right * right;
            if (curr == c)      return true;
            else if (curr < c)  left++;
            else                right--;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SumOfSquareNumbers().judgeSquareSum(3));
    }
}
