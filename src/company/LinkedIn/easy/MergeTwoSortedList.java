package company.LinkedIn.easy;


/**
 * LeetCode 21
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 Example:
 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4
 */
class ListNode {
    int val;
    ListNode next;
    public ListNode(int x) {
        this.val = x;
    }
}
public class MergeTwoSortedList {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dump = new ListNode(0);
		ListNode last = dump;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				last.next = l1;
				l1 = l1.next;
			} else {
				last.next = l2;
				l2 = l2.next;
			}
			last = last.next;
		}
		if(l1 != null)  last.next = l1;
		else            last.next = l2;
		return dump.next;
	}
}
