package company.LinkedIn.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 68
   Given an array of words and a length L,
   format the text such that each line has exactly L characters
    and is fully (left and right) justified.

 You should pack your words in a greedy approach;
 that is, pack as many words as you can in each line.
 Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible.
 If the number of spaces on a line do not divide evenly between words,
 the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text,
 it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0;
        List<String> result = new ArrayList<>();
        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }
        return result;
    }

    private String justify(int left, int right, String[] words, int maxWidth) {
        if (right - left == 0)  return padResult(words[left], maxWidth);

        boolean isLast = right == words.length - 1;
        int numSpaces = right - left;
        int totalSpace = maxWidth - wordsLength(left, right, words);
        String space = isLast ? " ": blank(totalSpace / numSpaces);
        int remainder = isLast ? 0 : totalSpace % numSpaces;

        StringBuilder res = new StringBuilder();
        for (int i = left; i <= right; i++) {
            res.append(words[i]).append(space).append(remainder-- > 0 ? " " : "");
        }
        return padResult(res.toString().trim(), maxWidth);
    }

    /** 产生长度len的空格字符串*/
    private String blank(int len){
        return new String(new char[len]).replace('\0',' ');
    }
    /** 对字符串result进行空格补齐使之长度为maxWidth,
     * 如"abc", maxWidth=10, 返回"abc       "*/
    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }
    /** 返回index从left到right的单词数组中的单词长度之和*/
    private int wordsLength(int left, int right, String[] words) {
        int total = 0;
        for (int i = left; i <= right; i++) total += words[i].length();
        return total;
    }

    // 从当前left 开始往右寻找 需要构成maxWidth长度的最右边的位置
    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right].length();
        right++;
        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
            sum += 1 + words[right].length();
            right++;
        }
        return right - 1;
    }




    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        TextJustification tf = new TextJustification();
//        System.out.println(tf.blank(3));
//        System.out.println(tf.padResult("ab",13));
//        System.out.println(tf.wordsLength(0,words.length-1, words));
        System.out.println(tf.fullJustify(words, 16));
        StringBuilder sb = new StringBuilder();

    }
}
