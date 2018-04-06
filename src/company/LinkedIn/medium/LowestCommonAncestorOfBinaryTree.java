package company.LinkedIn.medium;

/**
 * LC 236
 Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

 According to the definition of LCA on Wikipedia:
 “The lowest common ancestor is defined between two nodes v and w
    as the lowest node in T that has both v and w as descendants
    (where we allow a node to be a descendant of itself).”

            3
         /     \
        5       1
       / \     / \
      6   2   0   8
         / \
        7   4
 For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3.

 Another example is LCA of nodes 5 and 4 is 5,
 since a node can be a descendant of itself according to the LCA definition.
 找两个节点的公共最低祖先，例如上面的树，5和1的公共最低祖先是3；2和8的公共最低祖先是3；6和4的公共最低祖先是5
 自己可以是自己的祖先， 例如5和4的最低公共祖先是5。
 */
public class LowestCommonAncestorOfBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
}
