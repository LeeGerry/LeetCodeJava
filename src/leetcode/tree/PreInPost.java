package leetcode.tree;

import company.LinkedIn.medium.TreeNode;

import java.util.*;

/**
 * 先根中根后根遍历
 *
 */
public class PreInPost {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<Instruction> queue = new ArrayDeque<>();
        queue.offerFirst(new Instruction(0, root));
        while (!queue.isEmpty()) {
            Instruction current = queue.pollFirst();
            if (current == null) continue;
            if (current.code == 1)  result.add(current.node.val);
            else {
                if (current.node.right != null)
                    queue.offerFirst(new Instruction(0, current.node.right));
                if (current.node.left != null)
                    queue.offerFirst(new Instruction(0, current.node.left));
                queue.offerFirst(new Instruction(1, current.node));
            }
        }
        return result;
    }
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)   return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val);
            if (current.right != null)  stack.push(current.right);
            if (current.left != null)   stack.push(current.left);
        }
        return result;
    }
    public List<Integer> postTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.peek();
            if (current.left == null && current.right == null) result.add(stack.pop().val);
            else {
                if (current.right != null) {
                    stack.push(current.right);
                    current.right = null;
                }
                if (current.left != null) {
                    stack.push(current.left);
                    current.left = null;
                }
            }
        }
        return result;
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)   return result;
        Deque<Instruction> path = new ArrayDeque<>();
        path.offerFirst(new Instruction(0, root));
        while (!path.isEmpty()) {
            Instruction current = path.pollFirst();
            if (current.node == null) continue;
            if (current.code == 1) {
                result.add(current.node.val);
            } else {
                path.offerFirst(new Instruction(1, current.node));
                path.offerFirst(new Instruction(0, current.node.right));
                path.offerFirst(new Instruction(0, current.node.left));
            }
        }
        return result;
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)   return result;
        Deque<Instruction> path = new ArrayDeque<>();
        path.offerFirst(new Instruction(0, root));
        while (!path.isEmpty()) {
            Instruction current = path.pollFirst();
            if (current.node == null) continue;
            if (current.code == 1) {
                result.add(current.node.val);
            }else {
                path.offerFirst(new Instruction(0, current.node.right));
                path.offerFirst(new Instruction(1, current.node));
                path.offerFirst(new Instruction(0, current.node.left));
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    class Instruction {
        int code; // 0.visit; 1.print
        TreeNode node;
        public Instruction(int c, TreeNode n){
            this.code = c;
            this.node = n;
        }
    }
}
