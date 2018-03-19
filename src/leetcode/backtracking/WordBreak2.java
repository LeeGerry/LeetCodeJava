package leetcode.backtracking;

import java.util.*;

/**
 * Leetcode 140
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * You may assume the dictionary does not contain duplicate words.

 Return all such possible sentences.

 For example, given
 s = "catsanddog",
 dict = ["cat", "cats", "and", "sand", "dog"].

 A solution is ["cats and dog", "cat sand dog"].

 UPDATE (2017/1/4):
 The wordDict parameter had been changed to a list of strings (instead of a set of strings).
 Please reload the code definition to get the latest changes.


 */
public class WordBreak2 {
    Map<Integer, List<String>> map = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }

    private List<String> dfs(String s, List<String> wordDict, int start) {
        if (map.containsKey(start))     return map.get(start);
        List<String> res = new ArrayList<>();
        if (start == s.length())    res.add("");
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = dfs(s, wordDict, end);
                for (String temp: list)
                    res.add(s.substring(start, end) + (temp.equals("") ? "" : " ") + temp);
            }
        }
        map.put(start, res);
        return res;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        String[] dict = {"cat", "cats", "and", "sand", "dog"};
        List<String> list = Arrays.asList(dict);

        WordBreak2 wordBreak2 = new WordBreak2();
        System.out.println(wordBreak2.wordBreak(s, list));
    }
}
