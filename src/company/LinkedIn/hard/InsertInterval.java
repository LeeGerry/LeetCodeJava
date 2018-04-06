package company.LinkedIn.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC 57
 Given a set of non-overlapping intervals,
    insert a new interval into the intervals (merge if necessary).
 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

 */
class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}
public class InsertInterval {
    // time O(n); space O(n)
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int i = 0;
        //分成三步：第一步找左边没连成一片的
        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start){
            result.add(intervals.get(i++));
        }
        // 第二步：merge中间连城一片的
        // merge all overlapping intervals to one considering newInterval
        while (i<intervals.size() && intervals.get(i).start <= newInterval.end){
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }
        result.add(newInterval); //add the union of intervals
        // 第三步：把后面剩余的加到结果中
        // add all the rest
        while (i < intervals.size())
            result.add(intervals.get(i++));
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Interval> list = new ArrayList<>();
        list.add(new Interval(1,2));
        list.add(new Interval(3,5));
        list.add(new Interval(6,7));
        list.add(new Interval(8,10));
        list.add(new Interval(12,16));
        Interval in = new Interval(4,9);
        List<Interval> res = new InsertInterval().insert(list, in);
        System.out.println(Arrays.toString(res.toArray()));

    }
}
