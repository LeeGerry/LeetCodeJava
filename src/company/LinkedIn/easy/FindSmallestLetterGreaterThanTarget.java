package company.LinkedIn.easy;

/**
 LC 744
 Given a list of sorted characters letters containing only lowercase letters,
 and given a target letter target,
 find the smallest element in the list that is larger than the given target.

 Letters also wrap around.
 For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

 Examples:
 Input:
 letters = ["c", "f", "j"]
 target = "a"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "c"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "d"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "g"
 Output: "j"

 Input:
 letters = ["c", "f", "j"]
 target = "j"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "k"
 Output: "c"
 Note:
 letters has a length in range [2, 10000].
 letters consists of lowercase letters, and contains at least 2 unique letters.
 target is a lowercase letter.
 */
public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;
        // 如果目标比数组最右边的还大，那么返回数组第0个元素
        if (target >= letters[len - 1]) return letters[0];
        // 如果目标比数组最左边还小， 那么返回第0个元素
        if (target < letters[0])    return letters[0];
        // 标准二分法查找
        int left = 0;
        int right = len - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target < letters[mid])  right = mid; // 如果目标在左边，移动右指针
            else                        left = mid;  // 如果目标大于等于mid元素，都要移动左指针，因为要找比目标大的最小值
        }
        // while循环结束时letters[left] 肯定是小于或者等于 target，返回letter[right]
        return letters[right];
    }

    public static void main(String[] args) {
        char[] chars = {'c', 'f', 'k'};
        FindSmallestLetterGreaterThanTarget f = new FindSmallestLetterGreaterThanTarget();
        System.out.println(f.nextGreatestLetter(chars, 'a'));

    }
}
