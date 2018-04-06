package company.LinkedIn.phoneinterview;

import company.LinkedIn.medium.TreeNode;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * LC 109
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.


 Example:

 Given the sorted linked list: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

        0
       / \
     -3   9
     /   /
   -10  5

 */
class ListNode{
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
public class ConvertSortedListToBST {
    public TreeNode sortedListToBST(ListNode head){
        if (head == null)   return null;
        return toBST(head, null);
    }

    private TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail)   return null;
        ListNode fast = head, slow = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode node = new TreeNode(slow.val);
        node.left = toBST(head, slow);
        node.right = toBST(slow.next, tail);
        return node;
    }
}
