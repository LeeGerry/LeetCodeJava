package company.LinkedIn.easy;

import java.util.HashMap;
import java.util.Iterator;

/**
 * LC 170
 * Design and implement a TwoSum class.
 * It should support the following operations:add and find.

 add - Add the number to an internal data structure.
 find - Find if there exists any pair of numbers which sum is equal to the value.

 For example,

 add(1); add(3); add(5);
 find(4) -> true
 find(7) -> false

 [分析]
map O(1)存, O(n) 取
 */
public class TwoSum3 {
    HashMap<Integer, Integer> map = new HashMap<>();
    public void save(int input){
        int originalCount = 0;
        if(map.containsKey(input)){
            // 如果输入的数值已经存在，则读取value，即个数
            originalCount = map.get(input);
        }
        map.put(input, originalCount+1);
    }
    public boolean test(int target){
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()){
            int key = it.next();
            if(map.containsKey(target - key)){
                // 要判断一下是不是有特殊情况：key*2 = target
                boolean isDouble = (target == key*2);
                if(isDouble){//是特殊情况，则map中该key的数量大于1就好
                    if (map.get(key) > 1)   return true;
                    else return false;
                }else{//非特殊情况，则为true
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSum3 t3 = new TwoSum3();
        t3.save(1);
        t3.save(2);
        t3.save(5);
        t3.save(7);
        System.out.println(t3.test(10));
    }
}
