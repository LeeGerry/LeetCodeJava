package company.LinkedIn.medium;

import java.util.*;

/**
 * LeetCode 127
    Given two words (beginWord and endWord), and a dictionary's word list,
    find the length of shortest transformation sequence from beginWord to endWord, such that:

     Only one letter can be changed at a time.
     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     For example,

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]
     As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     return its length 5.

     Note:
     Return 0 if there is no such transformation sequence.
     All words have the same length.
     All words contain only lowercase alphabetic characters.
     You may assume no duplicates in the word list.
     You may assume beginWord and endWord are non-empty and are not the same
 */
public class WordLadder {
    // time: O(n) space: O(n)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (set.contains(beginWord))    set.remove(beginWord);
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);
        queue.offer(beginWord);
        while (!queue.isEmpty()){
            String word = queue.poll();
            int currentLevel = map.get(word);
            for (int i = 0; i < word.length(); i++) {
                char[] wordCharArray = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    wordCharArray[i] = c;
                    String temp = new String(wordCharArray);
                    if (set.contains(temp)){
                        if (temp.equals(endWord))   return currentLevel+1;
                        map.put(temp, currentLevel+1);
                        queue.offer(temp);
                        set.remove(temp);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        List<String> list = Arrays.asList(wordList);
        WordLadder wl = new WordLadder();
        System.out.println(wl.ladderLength(beginWord, endWord, list));
    }
}
