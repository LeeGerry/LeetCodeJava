package company.LinkedIn.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 366
 * Given a binary tree, find all leaves and then remove those leaves.
 * Then repeat the previous steps until the tree is empty.

 Example:
 Given binary tree
     1
    / \
   2   3
  / \
 4   5
 Returns [4, 5, 3], [2], [1].

 Explanation:
 1. Remove the leaves [4, 5, 3] from the tree

    1
   /
  2

 2. Remove the leaf [2] from the tree
 1

 3. Remove the leaf [1] from the tree
 []
 Returns [4, 5, 3], [2], [1].
 */
public class FindLeavesOfBinaryTree {
    /*给了我们一个二叉树，让我们返回其每层的叶节点，就像剥洋葱一样，将这个二叉树一层一层剥掉，最后一个剥掉根节点。
    提示说要用DFS:每一个节点从左子节点和右子节点分开走可以得到两个深度，由于成为叶节点的条件是左右子节点都为空，
    所以我们取左右子节点中较大值加1为当前节点的深度值，知道了深度值就可以将节点值加入到结果res中的正确位置了，
    求深度的方法我们可以参见Maximum Depth of Binary Tree中求最大深度的方法
    */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, root);
        return res;
    }

    private int helper(List<List<Integer>> res, TreeNode root) {
        if (root == null)   return -1;
        int left = helper(res, root.left);
        int right = helper(res, root.right);
        int curr = Math.max(left, right) + 1;
        // the first time this code is reached is when curr==0,
        // since the tree is bottom-up processed.
        if (res.size() <= curr) res.add(new ArrayList<>());
        res.get(curr).add(root.val);
        return curr;
    }
}
