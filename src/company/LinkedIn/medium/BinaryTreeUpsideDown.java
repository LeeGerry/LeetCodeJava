package company.LinkedIn.medium;

/**
 * LC 156
 * https://blog.csdn.net/whuwangyi/article/details/43186045
 * https://www.tangjikai.com/algorithms/leetcode-156-binary-tree-upside-down
 Problem:
 Given a binary tree where all the right nodes are either leaf nodes with a sibling
 (a left node that shares the same parent node) or empty,
 flip it upside down and turn it into a tree
 where the original right nodes turned into left leaf nodes.
 Return the new root.

 For example:
 Given a binary tree {1,2,3,4,5},

     1
    / \
   2   3
  / \
 4   5


 return the root of the binary tree [4,5,2,#,#,3,1].

     4
    / \
   5   2
      / \
     3   1

     1                 1
    / \               /
   2   3      -->    2 - 3
  / \               /
 4   5             4 - 5
 */
public class BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode node = root, parent = null, right = null;
        while (node != null) {
            TreeNode left = node.left;
            node.left = right;
            right = node.right;
            node.right = parent;
            parent = node;
            node = left;
        }
        return parent;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return null;
        TreeNode newRoot = upsideDownBinaryTree2(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}