package leetcode.stackandpq;

import java.util.Stack;

/**
 * LeetCode 84
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 * Given n non-negative integers representing the histogram's bar height
 * 	where the width of each bar is 1, find the area of largest rectangle in the histogram.
 Given heights = [2,1,5,6,2,3],
 return 10.
 *
 */
public class LargestRectangleInHistogram {
	public static int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0)	return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i = 0; i<=heights.length; i++){
        	int h = (i==heights.length ? 0:heights[i]);
        	while(!stack.isEmpty() && h < heights[stack.peek()]){
        		int height = heights[stack.pop()];
        		int start = stack.isEmpty() ? -1 : stack.peek();
        		int area = height * (i - start - 1);
        		res = Math.max(res, area);
        	}
        	stack.push(i);
        }
        return res;
    }
	public static void main(String[] args) {
		int[] heights = {2,1,5,6,2,3};
		System.out.println(largestRectangleArea(heights));
	}
}
