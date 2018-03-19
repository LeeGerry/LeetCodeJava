package leetcode.stackandpq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;

/**
 * LeetCode 332
 * https://leetcode.com/problems/reconstruct-itinerary/description/
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
	
	Note:
	If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
	All airports are represented by three capital letters (IATA code).
	
	You may assume all tickets form at least one valid itinerary.
	
	Example 1:
	tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
	Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
	Example 2:
	tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
	Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
	Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
	
 */
public class ReconstructItinerary {
	HashMap<String, PriorityQueue<String>> map;
	List<String> result;
	public List<String> findItinerary(String[][] tickets){
		map = new HashMap<>();
		result = new LinkedList<>();
		for(String[] ticket: tickets){
			map.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
		}
		helper("JFK");
		return result;
	}

	private void helper(String airPort) {
		while(map.containsKey(airPort) && !map.get(airPort).isEmpty())
			helper(map.get(airPort).poll());
		result.add(0,airPort);
	}
	
	public static void main(String[] args) {
		String [][] tickets = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		ReconstructItinerary r = new ReconstructItinerary();
		System.out.println(r.findItinerary(tickets));

	}
}
