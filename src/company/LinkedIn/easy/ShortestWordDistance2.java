package company.LinkedIn.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 244
 * This is a follow up of Shortest Word Distance.
 * The only difference is now you are given the list of words
 * and your method will be called repeatedly many times with different parameters.
 * How would you optimize it?

 Design a class which receives a list of words in the constructor,
 and implements a method that takes two words word1 and word2
 and return the shortest distance between these two words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.
 Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance2 {
    Map<String, ArrayList<Integer>> map;
    public ShortestWordDistance2(String[] words){
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.get(words[i]).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        ArrayList<Integer> word1IndexList = map.get(word1);
        ArrayList<Integer> word2IndexList = map.get(word2);
        int res = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < word1IndexList.size() && j < word2IndexList.size()) {
            res = Math.min(res, Math.abs(word1IndexList.get(i) - word2IndexList.get(j)));
            if (word1IndexList.get(i) < word2IndexList.get(j)) // word1的index小的话，移动i，距离变小
                i++;
            else // Word2的index小的话，移动j，距离变小
                j++;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        // String w1 = "coding", w2 = "practice";
        String w1 = "makes", w2 = "coding";
        ShortestWordDistance2 swd2 = new ShortestWordDistance2(words);
        System.out.println(swd2.shortest(w1, w2));
    }
}
