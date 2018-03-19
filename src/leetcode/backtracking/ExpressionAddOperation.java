package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 282
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or * between the digits
   so they evaluate to the target value.

 Examples:
 "123", 6 -> ["1+2+3", "1*2*3"]
 "232", 8 -> ["2*3+2", "2+3*2"]
 "105", 5 -> ["1*0+5","10-5"]
 "00", 0 -> ["0+0", "0-0", "0*0"]
 "3456237490", 9191 -> []
 */
public class ExpressionAddOperation {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0)   return res;
        helper(res, "", num, target, 0, 0, 0);
        return res;
    }

    private void helper(List<String> res, String path, String num, int target, int pos, long val, long pre) {
        if (pos == num.length()) {
            if (target == val) {
                res.add(path);
                return;
            }
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0){
                helper(res, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(res, path + "+" + cur, num, target, i + 1, val + cur, cur);
                helper(res, path + "-" + cur, num, target, i + 1, val - cur, -cur);
                helper(res, path + "*" + cur, num, target, i + 1, val - pre + pre * cur, pre * cur);
            }
        }
    }

    public static void main(String[] args) {

        ExpressionAddOperation eao = new ExpressionAddOperation();
        System.out.println(eao.addOperators("3456237490", 9191));
    }
}
