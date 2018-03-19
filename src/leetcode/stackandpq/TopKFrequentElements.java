package leetcode.stackandpq;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * LeetCode 347
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 * Given a non-empty array of integers, return the k most frequent elements.
	For example,
	Given [1,1,1,2,2,3] and k = 2, return [1,2].
	
	Note: 
	You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
	Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 */
public class TopKFrequentElements {
	//PriorityQueue- time: O(klogn) space: O(n)
	public List<Integer> topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int num: nums)	map.put(num, map.getOrDefault(num, 0) + 1);
		PriorityQueue<Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		for(Entry<Integer, Integer> entry: map.entrySet())	maxHeap.add(entry);
		List<Integer> result = new ArrayList<>();
		while(result.size() < k){
			Entry<Integer, Integer> entry = maxHeap.poll();
			result.add(entry.getKey());
		}
        return result;
    }

	// Bucket Sort -time: O(n) space: O(n)
	public List<Integer> topKFrequent3(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int num: nums)	map.put(num, map.getOrDefault(num, 0) + 1);
		List<Integer>[] bucket = new List[nums.length + 1];
		for(int num: map.keySet()){
			int freq = map.get(num);
			if(bucket[freq] == null)
				bucket[freq] = new LinkedList<>();
			bucket[freq].add(num);
		}
		List<Integer> result = new ArrayList<>();
		for(int i = bucket.length - 1; i>=0 && result.size()<k; i--){
			if(bucket[i]!=null)	result.addAll(bucket[i]);
		}
		return result;
	}

	// TreeMap- time: O(klogn) space: O(n) 
	public List<Integer> topKFrequent2(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int num: nums)	map.put(num, map.getOrDefault(num, 0) + 1);
		TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
		for(int num: map.keySet()){
			int freq = map.get(num);
			if(freqMap.containsKey(freq)){
				freqMap.get(freq).add(num);
			} else{
				freqMap.put(freq, new LinkedList<>());
				freqMap.get(freq).add(num);
			}
		}
		List<Integer> result = new ArrayList<>();
		while(result.size() < k){
			Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
			result.addAll(entry.getValue());
		}
		return result;
	}
	

	public static void main(String[] args) {
		int[] arr = {1,1,1,2,2,3};
		int k = 2;
		TopKFrequentElements t = new TopKFrequentElements();
		System.out.println(t.topKFrequent(arr, k));
		System.out.println(t.topKFrequent2(arr, k));
		System.out.println(t.topKFrequent3(arr, k));
	}
}
