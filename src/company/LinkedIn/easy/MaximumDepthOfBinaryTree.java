package company.LinkedIn.easy;


import java.util.LinkedList;
import java.util.Queue;

/**
 * LC 104
 Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node
 down to the farthest leaf node.

 For example:
 Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
 return its depth = 3.
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null)   return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int dep = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                TreeNode n = q.poll();
                if (n.left != null)     q.offer(n.left);
                if (n.right != null)    q.offer(n.right);
                size--;
            }
            dep++;
        }
        return dep;
    }
}
