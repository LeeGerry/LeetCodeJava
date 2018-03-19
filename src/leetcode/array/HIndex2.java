package leetcode.array;

/**
 * Leetcode 275
 * Follow up for H-Index:
 * What if the citations array is sorted in ascending order?
 * Could you optimize your algorithm?
 */
public class HIndex2 {
    // 已经排序的情况下，用二分查找，时间复杂度降为O(logN)
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0)  return 0;
        int n = citations.length;
        int right = n - 1;
        int left = 0;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(citations[mid] < n - mid)    left = mid + 1;
            else    right = mid - 1;
        }
        return n - left;
    }
}
