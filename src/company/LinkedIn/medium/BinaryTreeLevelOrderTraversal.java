package company.LinkedIn.medium;

import java.lang.reflect.Array;
import java.util.*;

/**
 * LC 102
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
   3
  / \
 9  20
   /  \
 15   7
 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]
 */
public class BinaryTreeLevelOrderTraversal {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    // bfs
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)   return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> curLevel = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++){
                TreeNode head = q.poll();
                curLevel.add(head.val);
                if (head.left != null)      q.offer(head.left);
                if (head.right != null)     q.offer(head.right);
            }
            res.add(curLevel);
        }
        return res;
    }

    // dfs
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, root, 0);
        return res;

    }

    public void helper(List<List<Integer>> res, TreeNode node, int depth) {
        if (node == null) return;
        if (res.size() == depth) {
            List<Integer> list = new ArrayList<>();
            res.add(list);
        }
        res.get(depth).add(node.val);
        helper(res, node.left, depth + 1);
        helper(res, node.right, depth + 1);
    }
}
