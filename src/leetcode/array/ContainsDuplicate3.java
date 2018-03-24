package leetcode.array;

import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Leetcode 220
 * Given an array of integers,
 * find out whether there are two distinct indices i and j in the array
 * such that the absolute difference between nums[i] and nums[j] is at most t
 * and the absolute difference between i and j is at most k.
 */
public class ContainsDuplicate3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length <=1 || k < 1 || t < 1)  return false;

        TreeSet<Long> set = new TreeSet<>();
        for (int i=0; i<nums.length; i++) {
            Long cur = (long)nums[i];
            Long floor = set.floor(cur + t);
            Long ceil = set.ceiling(cur - t);

            if (floor != null && floor >= cur
                    || ceil != null && ceil <= cur)
                return true;

            set.add(cur);
            if (i >= k) set.remove((long) (nums[i-k]));
        }
        return false;
    }
    //静态方法，便于作为工具类
    public static String getMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static void main(String[] args) {
        int[] nums = {1, 7, 12, 16};
        System.out.println(new ContainsDuplicate3().containsNearbyAlmostDuplicate(nums,3,2));
    }
}

