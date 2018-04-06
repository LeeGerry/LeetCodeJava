package company.LinkedIn.phoneinterview;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * 实现Integer.parseInt(String)
 * 正负号问题： +3434343 -344343
 * 边界问题：   MAXVALUE MINVALUE
 *
 *
 */
public class IntegerParse {
    public static int solution(String str) {
        if (str == null || str.length() == 0)
            throw new NumberFormatException("string could not be null or empty.");
        if (str.equals("-2147483648"))  return Integer.MIN_VALUE;
        char firstChar = str.charAt(0);
        boolean positive = true;
        long result = 0;
        if (!Character.isDigit(firstChar)) {
            if (firstChar == '-') positive = false;
            else if (firstChar != '+')
                throw new NumberFormatException("string format is invalid.");
        } else {
            result += firstChar - '0';
        }

        for (int i = 1; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (!Character.isDigit(curr)) {
                throw new NumberFormatException("string format is invalid.");
            } else {
                result *= 10;
                result += curr - '0';
            }
            if (Integer.MIN_VALUE > result || result > Integer.MAX_VALUE)
                throw new NumberFormatException("number overflow.");
        }
        if (positive) return (int)result;
        return -(int)result;
    }

    public static void main(String[] args) {
//        System.out.println(-Integer.MAX_VALUE / 10);
//        System.out.println(Integer.MIN_VALUE / 10);
        System.out.println(solution("2147483647"));
    }
    public static int parseInt(String s) throws NumberFormatException {
        if (s == null) throw new NumberFormatException("null");
        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE; // -2147483647
        int multmin = limit/10; // -214748364
        int digit;
        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+')
                    throw new NumberFormatException("符号非法");

                if (len == 1) // Cannot have lone "+" or "-"
                    throw new NumberFormatException("不能只有符号");
                i++;
            }

            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++), 10);
                if (digit < 0) {
                    throw new NumberFormatException("");
                }
                if (result < multmin) {
                    throw new NumberFormatException("");
                }
                result *= 10;
                if (result < limit + digit) {
                    throw new NumberFormatException("");
                }
                result -= digit;
            }
        } else {
            throw new NumberFormatException("");
        }
        return negative ? result : -result;
    }
}
