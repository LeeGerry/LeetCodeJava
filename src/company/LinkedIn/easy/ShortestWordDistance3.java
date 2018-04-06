package company.LinkedIn.easy;

/**
 * LC 245
 * Created by Gary on 3/19/18.
 * This is a follow up of Shortest Word Distance.
 * The only difference is now word1 could be the same as word2.
 Given a list of words and two words word1 and word2,
    return the shortest distance between these two words in the list.
 word1 and word2 may be the same and they represent two individual words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “makes”, word2 = “coding”, return 1.
 Given word1 = "makes", word2 = "makes", return 3
 Note:
 You may assume word1 and word2 are both in the list.
 区别是，Word1和Word2可以相同，数组中有多个该单词的时候，找到最小距离。
 */
public class ShortestWordDistance3 {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int res = words.length;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < words.length; i++) {
            String curr = words[i];
            if (word1.equals(curr)) {// 先判断当前和word1是否相同
                index1 = i; // 相同就把index1指向该位置
            }
            if (word2.equals(curr)) { // 在判断当前和word2是否相同
                if (word1.equals(curr)) { //若相同，则需要再判断是否和word1也相同，
                    index1 = index2;
                }
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                res = Math.min(res, Math.abs(index1 - index2));
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        // String w1 = "coding", w2 = "practice";
        String w1 = "makes", w2 = "makes";
        ShortestWordDistance3 s3 = new ShortestWordDistance3();
        System.out.println(s3.shortestWordDistance(words, w1, w2));
    }
}
