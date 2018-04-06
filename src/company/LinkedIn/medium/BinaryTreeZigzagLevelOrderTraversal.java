package company.LinkedIn.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LC 103
 Given a binary tree, return the zigzag level order traversal of its nodes' values.
 (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
   3
  / \
 9   20
    /  \
   15   7
 return its zigzag level order traversal as:
 [
     [3],
     [20,9],
     [15,7]
 ]

 */
public class BinaryTreeZigzagLevelOrderTraversal {
    // time : O(n); space : O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null)   return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean flag = true;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (flag)   list.add(curr.val);
                else        list.add(0, curr.val);
                if (curr.left != null)  q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
            res.add(list);
            flag = !flag;
        }
        return res;
    }
}
