package company.LinkedIn.phoneinterview;

import company.LinkedIn.medium.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * LC 98
 */
public class ValidateBST {
    class Instruction{
        int code; // 0.visit; 1.print
        TreeNode node;
        public Instruction(int c, TreeNode node){
            this.code = c; this.node = node;
        }
    }

    public boolean valid(TreeNode root) {
        Long preValue = Long.MIN_VALUE;
        if (root == null)   return true;
        Deque<Instruction> q = new ArrayDeque<>();
        q.offerFirst(new Instruction(0, root));
        while (!q.isEmpty()) {
            Instruction current = q.pollFirst();
            if (current == null)    continue;
            if (current.code == 1) {
                if (current.node.val <= preValue)    return false;
                else preValue = (long)current.node.val;
            } else {
                if (current.node.right != null)
                    q.offerFirst(new Instruction(0, current.node.right));
                q.offerFirst(new Instruction(1, current.node));
                if (current.node.left != null)
                    q.offerFirst(new Instruction(0, current.node.left));
            }
        }
        return true;
    }

}
