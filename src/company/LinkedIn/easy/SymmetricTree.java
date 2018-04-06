package company.LinkedIn.easy;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, check whether it is a mirror of itself
 * (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

      1
     / \
    2   2
   / \ / \
  3  4 4  3
 But the following [1,2,2,null,3,null,3] is not:
     1
    / \
   2   2
   \    \
    3    3
 Note:
 Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		//return isSym(root.left, root.right);
		return isSymmetric1(root);
	}
	// 方法1: recursive
	private boolean isSym(TreeNode left, TreeNode right){
		if(left == null && right == null)		return true;
		else if(right == null || left == null)	return false;
		if(left.val != right.val)				return false;
		if(!isSym(left.left, right.right))		return false;
		if(!isSym(left.right, right.left))		return false;
		return true;
	}

	//方法2: iterative
	public boolean isSymmetric1(TreeNode root) {
		if(root == null)	return true;
		Queue<TreeNode> left = new LinkedList<>();
		Queue<TreeNode> right = new LinkedList<>();
		left.offer(root);
		right.offer(root);
		while(!left.isEmpty() && !right.isEmpty()){
			TreeNode l = left.poll();
			TreeNode r = right.poll();
			// 左右都不为空，继续向下判断
			if(l == null && r == null)			continue;
			// 左右一个为空，一个不为空，不对称
			else if(l == null || r == null)		return false;
			// 左右都不为空，判断值是否相等，值不等，不对称
			if(l.val != r.val)					return false;
			// 值相等
			else{
				left.add(l.left);  // 左左入队
				left.add(l.right); // 左右入队
				right.add(r.right);// 右左入队
				right.add(r.left); // 右右入队
			}
		}
		return true;
	}
}
