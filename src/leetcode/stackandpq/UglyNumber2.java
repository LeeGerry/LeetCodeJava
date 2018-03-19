package leetcode.stackandpq;
/**
 * LeetCode 264
 * https://leetcode.com/problems/ugly-number-ii/description/
 * 
 * Write a program to find the n-th ugly number.
	Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
	For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
	
	Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 */
public class UglyNumber2 {
	public int nthUglyNumber(int n){
		int[] numbers = new int[n];
		int index2 = 0, index3 = 0, index5 = 0;
		numbers[0] = 1;
		for(int i = 1; i < numbers.length; i++){
			numbers[i] = Math.min(numbers[index2]*2, Math.min(numbers[index3] * 3, numbers[index5] * 5));
			if(numbers[i] == numbers[index2] * 2) 	index2++;
			if(numbers[i] == numbers[index3] * 3) 	index3++;
			if(numbers[i] == numbers[index5] * 2) 	index5++;
		}
		return numbers[n-1];
	}
	public static void main(String[] args) {
		UglyNumber2 u2 = new UglyNumber2();
		System.out.println(u2.nthUglyNumber(10));
	}
}
