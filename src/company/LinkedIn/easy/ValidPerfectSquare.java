package company.LinkedIn.easy;
/*
LeetCode 367
Given a positive integer num,
write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:
Input: 16
Returns: True

Example 2:
Input: 14
Returns: False
 */
public class ValidPerfectSquare {
	public boolean isPerfectSquare2(int num) {
		if (num < 1)	return false;
		if (num == 1)	return true;
		int left = 1, right = num;
		while (left + 1 < right) {
			long mid = left + (right - left) / 2;
			long sq = mid * mid;
			if (sq == num) 		return true;
			else if (sq < num)	left = (int)mid;
			else 				right = (int)mid;
		}
		if (left * left == num || right * right == num)	return true;
		return false;
	}

	public boolean isPerfectSquare3(int num) {
		int low = 1, high = num;
		while (low <= high) {
			long mid = (low + high) >>> 1;
			if (mid * mid == num) {
				return true;
			} else if (mid * mid < num) {
				low = (int) mid + 1;
			} else {
				high = (int) mid - 1;
			}
		}
		return false;
	}
	public boolean isPerfectSquare(int num){
		if(num < 1) 	return false;
		if(num == 1) 	return true;
		long left = 1;
		long right = (num >> 1) + 1;
		while(left <= right){
			long mid = left + ((right - left) >> 1);
			long temp = mid * mid;
			if(temp == num) 		return true;
			else if(temp > num) 	right = mid -1;
			else					left = mid + 1;
		}
		return false;
	}
	public static void main(String[] args) {
		System.out.println(new ValidPerfectSquare().isPerfectSquare(9));
		System.out.println(new ValidPerfectSquare().isPerfectSquare2(808201));
		int i = 808201;
		System.out.println(i * i);
		System.out.println((long)((long)i * (long)i));
	}
}
