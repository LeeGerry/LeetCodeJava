package company.LinkedIn;

/**
 * LC 605
 Suppose you have a long flowerbed in which some of the plots are planted and some are not.
 However, flowers cannot be planted in adjacent plots
    - they would compete for water and both would die.

 Given a flowerbed
 (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty),
 and a number n,
 return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

 Example 1:
 Input: flowerbed = [1,0,0,0,1], n = 1
 Output: True
 Example 2:
 Input: flowerbed = [1,0,0,0,1], n = 2
 Output: False
 Note:
 The input array won't violate no-adjacent-flowers rule.
 The input array size is in the range of [1, 20000].
 n is a non-negative integer which won't exceed the input array size.

 贪心算法（Greedy Algorithm）
 从左向右遍历flowerbed，将满足要求的的种花位置0设为1。计数与n比较即可。
 */
public class CanPlaceFlower {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        //合法位置数目
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            // 当前位置已经种花，继续
            if (flowerbed[i] > 0)   continue;
            //当前位置不是最左边，当前位置左边已经种花，继续
            if (i > 0 && flowerbed[i - 1] > 0)  continue;
            //当前位置不是右边界-1，当前位置右边已经种花，继续
            if (i + 1 < flowerbed.length && flowerbed[i + 1] > 0)   continue;
            // 当前位置可种花，数目++
            count++;
            // 种上
            flowerbed[i] = 1;
        }
        return count >= n;
    }

    public static void main(String[] args) {
        int[] flowerbed = {1,0,0,0,1};
        System.out.println(new CanPlaceFlower().canPlaceFlowers(flowerbed, 1));
    }
}
