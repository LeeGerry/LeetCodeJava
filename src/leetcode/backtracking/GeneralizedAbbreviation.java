package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 320
 * http://www.cnblogs.com/grandyang/p/5261569.html
 * Write a function to generate the generalized abbreviations of a word.

 Example:

 Given word = "word", return the following list (order does not matter):

 ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

 思路: DFS + 回溯. 每一次枚举数字化几位, 需要注意的是如果没有完全将后面的字符数字化的话, 每两段之间至少需要有一个字符的间隔.
 */
public class GeneralizedAbbreviation {
    public List<String> solution4(String word) {
        List<String> res = new ArrayList<>();
        helper(res, word, 0, "", 0);
        return res;
    }

    private void helper(List<String> res, String word, int pos, String current, int count) {
        if (pos == word.length()) {
            if (count > 0)  current += count;
            res.add(current);
        } else {
            helper(res, word, pos + 1, current, count + 1);
            helper(res, word, pos + 1, current + (count > 0 ? count : "") + word.charAt(pos), 0);
        }
    }

    List<String> solution1(String word){
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, word.length()); i++) {
            StringBuilder cur = new StringBuilder();
            int cnt = 0;
            for (int j = 0; j < word.length(); j++) {
                if (((i >> j) & 1) == 1)    cnt++;
                else {
                    if (cnt != 0) {
                        cur.append(cnt);
                        cnt = 0;
                    }
                    cur.append(word.charAt(j));
                }
            }
            if (cnt > 0)    cur.append(cnt);
            res.add(cur.toString());
            cur.delete(0, cur.length());
        }
        return res;
    }



    public static void main(String[] args) {
        GeneralizedAbbreviation g = new GeneralizedAbbreviation();
        System.out.println(g.solution4("word"));
        System.out.println(g.solution1("word"));
    }
}

