package company.LinkedIn.easy;

/**
 * LeetCode 243
 Given a list of words and two words word1 and word2,
 return the shortest distance between these two words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.
 */
public class ShortestWordDistance {
    /*
    给定单词数组，返回其中任意两单词的最短位置距离。
    记录二者最新位置和最小距离，遍历实现。
        因为数组中可能存在相同的单词，所以不能使用Hash来实现；
        通过两个指针指示两个单词的最新位置，遍历实现即可。
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int position1 = -1, position2 = -1;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            // 如果第一个单词匹配当前单词
            if (word1.equals(words[i])) {
                position1 = i; //指针1指向该位置
                // 判断第二个单词是否找到过，不为-1就是找到过，result更新为较小值
                if (position2 != -1)
                    result = Math.min(result, position1 - position2);
            } else if (word2.equals(words[i])) {
                position2 = i; //指针1指向该位置
                // 判断第一个单词是否找到过，不为-1就是找到过，result更新为较小值
                if (position1 != -1)
                    result = Math.min(result, position2 - position1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        // String w1 = "coding", w2 = "practice";
        String w1 = "makes", w2 = "coding";
        System.out.println(new ShortestWordDistance().shortestDistance(words, w1, w2));
    }
}
