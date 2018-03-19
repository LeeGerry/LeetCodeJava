package leetcode.backtracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 93
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * For example:
 * Given "25525511135",
 *
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(res, s, 0, "", 0);
        return res;
    }

    private void helper(List<String> res, String s, int index, String ret, int count) {
        if (count > 4)  return;
        if (count == 4 && index == s.length()) {
            res.add(ret);
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (index + i > s.length())     break;
            String temp = s.substring(index, index + i);
            if ((temp.startsWith("0") && temp.length() > 1) || (i == 3 && Integer.parseInt(temp) >= 256)) continue;
            helper(res, s, index + i, ret + temp + (count == 3 ? "" : "."), count + 1);
        }
    }

    public List<String> restoreIpAddress2(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        dfs(result, s, 0, list);
        ArrayList<String> finalResult = new ArrayList<>();
        for (ArrayList<String> l: result) {
            StringBuilder sb = new StringBuilder();
            for (String str: l) {
                sb.append(str).append(".");
            }
            sb.deleteCharAt(sb.length() - 1);
            finalResult.add(sb.toString());
        }
        return finalResult;
    }

    public void dfs(ArrayList<ArrayList<String>> result, String s, int start, ArrayList<String> t){
        // if already get 4 numbers, but s is not consumed, return
        if (t.size() >= 4 && start != s.length())   return;
        // make sure t's size + remaining string's length >= 4
        if ((t.size()) + s.length() - start + 1 < 4)    return;
        // t's size is 4 and no remaining part that is not consumed
        if (t.size() == 4 && start == s.length()){
            ArrayList<String> temp = new ArrayList<>(t);
            result.add(temp);
            return;
        }
        for (int i = 1; i <= 3; i++) {
            // make sure the index is within the boundary
            if (start + i > s.length())             break;
            String sub = s.substring(start, start + i);
            // handle case like 001. i.e., if length > 1 and first char is 0, ignore the case.
            if (i > 1 && s.charAt(start) == '0')    break;
            // make sure each number <= 255
            if (Integer.valueOf(sub) > 255)         break;

            t.add(sub);
            dfs(result, s, start + i, t);
            t.remove(t.size() - 1);
        }
    }

    public static void main(String[] args) {
        String ip = "25525511135";
        RestoreIPAddress rip = new RestoreIPAddress();
        System.out.println(rip.restoreIpAddresses(ip));
        System.out.println(rip.restoreIpAddress2(ip));
    }
}
