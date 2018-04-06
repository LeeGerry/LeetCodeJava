package company.LinkedIn.hard;

import java.util.PriorityQueue;

/**
 * Lc23
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 */
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
public class MergeKSortedList {
	private ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		return helper(lists, 0, lists.length - 1);
	}

	private ListNode helper(ListNode[] lists, int l, int r) {
		if (l == r) 	return lists[l];
		int m = l + (r - l) / 2;
		ListNode left = helper(lists, l, m);
		ListNode right = helper(lists, m+1, r);
		return merge(left, right);
	}

	private ListNode merge(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				tail.next = l1;
				tail = l1;
				l1 = l1.next;
			} else {
				tail.next = l2;
				tail = l2;
				l2 = l2.next;
			}
		}
		if (l1 != null) 	tail.next = l1;
		else 				tail.next = l2;
		return dummy.next;
	}

	/**
	 * time: n * log k
	 * @param lists
	 * @return
	 */
	private ListNode mergeKLists2(ListNode[] lists) {
		if (lists == null || lists.length == 0)	return null;
		PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		for (ListNode node: lists) {
			if (node != null)	queue.offer(node);
		}
		while (!queue.isEmpty()) {
			current.next = queue.poll();
			current = current.next;
			if (current.next != null)	queue.offer(current.next);
		}
		return dummy.next;
	}
}
