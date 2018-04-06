package company.LinkedIn.hard;

import java.util.HashMap;

/**
 * LC 76
 Given a string S and a string T,
 find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".

 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".

 If there are multiple such windows,
 you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        char S[] = s.toCharArray();
        char T[] = t.toCharArray();
        String result = "";
        //字符计数，只记录T里面的，并且做一个初始的技术
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length; i++) {
            if (map.containsKey(T[i]) == false) {
                map.put(T[i], 0);
            }
            map.put(T[i], map.get(T[i]) + 1);
        }
        int count = T.length, p = 0, q = 0;  //注意count是必须要匹配的个数
        //移动q
        while (q < S.length) {
            /**
             * 如果q对应的位置的S的字符，在T中出现，那么计数-1
             * 注意有效的count--是需要剪完后仍然大于0的，也就是还满足T的要求的
             * */
            if (map.containsKey(S[q])) {
                int tmp = map.get(S[q]);
                if (tmp > 0)
                    count--;
                map.put(S[q], tmp - 1);
            }
            q++;
            //count=0的时候证明，已经有了能匹配T的字串了，此时移动p，到刚好能满足这个位置q的最小位置就可以
            if (count == 0) {
                while (p < q && count == 0) {
                    if (map.containsKey(S[p]) == true) {
                        int tmp = map.get(S[p]);
                        if (tmp >= 0) {
                            if (result.equals("") || result.length() >= q - p) {
                                result = s.substring(p, q);
                            }
                            count++;
                        }
                        map.put(S[p], tmp + 1);
                    }
                    p++;
                }
            }
        }
        return result;
    }


    /**
     * 两个指针，哈希表保存当前经过的数组。。。
     * 经过的字符，如果包含在T中，那么对应的计数-1，使用count表示有效计数（也就是T的长度），当计数减去后大于等于0的（注意可以小于0），就证明T中还有有待匹配的，那么count也跟着减，当count=0的时候p-q之间肯定有答案。
     * 如果p-q之间，正好满足了T的要求，那么就按照长度进行更新就可以。。。注意p的位置要记得调整到合适的，此时将p调整到第一个count不为0之前，如何确定count不为0，之前不是有计数的么，那么p在前进过程中，如果遇到T中没有的额就直接前进，如果遇到的是T包含的，但是计数小于0，那么p也可以继续前进，只需将对应的字符计数++就可以。
     *
     */
    public String minWindow2(String s, String t) {
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        String result="";
        //用map来保存t中的字符和字符个数
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0; i<charsT.length; i++)
            map.put(charsT[i], map.getOrDefault(charsT[i], 0) + 1);

        int count = charsT.length, start = 0, end = 0;  //注意count是必须要匹配的个数
        //移动q
        while(end <charsS.length){
            //如果q对应的位置的S的字符，在T中出现，那么计数-1;注意有效的count--是需要剪完后仍然大于0的，也就是还满足T的要求的
            if(map.containsKey(charsS[end])){
                int value = map.get(charsS[end]);
                if(value > 0) count--;
                map.put(charsS[end],value - 1);
            }
            end++;
            //count=0的时候证明，已经有了能匹配T的字串了，此时移动p，到刚好能满足这个位置q的最小位置就可以
            if(count==0){
                while(start < end && count == 0){
                    if(map.containsKey(charsS[start])){
                        int v = map.get(charsS[start]);
                        if(v >= 0){
                            if(result.equals("") || result.length() >= end - start)   result=s.substring(start, end);
                            count++;
                        }
                        map.put(charsS[start],v+1);
                    }
                    start++;
                }
            }
        }
        return result;
    }

    public String minWindow3(String s, String t) {
        int[] map = new int[128];
        for (char c: t.toCharArray()) map[c]++;
        int begin = 0, start = 0, end = 0, min = Integer.MAX_VALUE, total = t.length();

        while (end < s.length()) {
            if (map[s.charAt(end++)]-- > 0)     total--;
            while (total == 0) {
                if (end - start < min) {
                    min = end - start;
                    begin = start;
                }
                if (map[s.charAt(start++)]++ == 0)  total++;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(begin, begin + min);
    }
    public static void main(String[] args) {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        System.out.println(new MinimumWindowSubstring().minWindow3(S,T));
    }
}
