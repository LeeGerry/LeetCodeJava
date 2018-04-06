package company.LinkedIn.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC 671
 Given a non-empty special binary tree consisting of nodes with the non-negative value,
 where each node in this tree has exactly two or zero sub-node.
 If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

 Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

 If no such second minimum value exists, output -1 instead.

 Example 1:
 Input:
   2
  / \
 2   5
    / \
   5   7

 Output: 5
 Explanation: The smallest value is 2, the second smallest value is 5.

 Example 2:
 Input:
   2
  / \
 2   2

 Output: -1
 Explanation: The smallest value is 2, but there isn't any second smallest value.

 */
public class SecondMinimumNodeInBinaryTree {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null)   return -1;
        return bfs(root);
        //return dfs(root, root.val);
    }
    // time: O(n); space: O(n)
    private int bfs(TreeNode root) {
        if (root == null)   return -1;
        int s1 = root.val;
        int s2 = Integer.MAX_VALUE;
        boolean found = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.val > s1 && curr.val < s2) {
                s2 = curr.val;
                found = true;
                break;
            }
            if (curr.left == null)  continue;
            q.offer(curr.left);
            q.offer(curr.right);
        }
        return found ? s2: -1;
    }

    private int dfs(TreeNode root, int s1){
        if (root == null)   return -1;
        if (root.val > s1)  return root.val;
        int sl = dfs(root.left, s1);
        int sr = dfs(root.right, s1);
        if (sl == -1)   return sr;
        if (sr == -1)   return sl;
        return Math.min(sl, sr);
    }
}
