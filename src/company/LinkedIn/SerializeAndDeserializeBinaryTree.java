package company.LinkedIn;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * LC 297
 Serialization is the process of converting a data structure or object into a sequence of bits
 so that it can be stored in a file or memory buffer,
 or transmitted across a network connection link
 to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree.
 There is no restriction on how your serialization/deserialization algorithm should work.
 You just need to ensure that a binary tree can be serialized to a string
 and this string can be de-serialized to the original tree structure.

 For example, you may serialize the following tree

     1
    / \
   2   3
      / \
     4   5
 as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
 You do not necessarily need to follow this format,
 so please be creative and come up with different approaches yourself.

 Note: Do not use class member/global/static variables to store states.
 Your serialize and deserialize algorithms should be stateless.
 序列化与反序列化
 */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
public class SerializeAndDeserializeBinaryTree {
    private final String delimiter = ",";
    private final String emptyNode = "#";
    // Encodes a tree to a single string.序列化，给根节点，返回字符串
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        helper(root, res);
        return res.toString();
    }
    // 序列化的递归函数
    private void helper(TreeNode root, StringBuilder res) {
        if (root == null){
            res.append(emptyNode).append(delimiter);
        }
        else{
            res.append(root.val).append(delimiter);
            helper(root.left, res);
            helper(root.right, res);
        }
    }

    // Decodes your encoded data to tree.反序列化，给字符串，构造树，返回根节点
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(delimiter)));
        return helper2(nodes);
    }
    //反序列化的递归
    private TreeNode helper2(LinkedList<String> nodes) {
        String nodeValue = nodes.pollFirst();
        if (nodeValue.equals(emptyNode))    return null;
        else {
            TreeNode node = new TreeNode(Integer.parseInt(nodeValue));
            node.left = helper2(nodes);
            node.right = helper2(nodes);
            return node;
        }
    }
}

