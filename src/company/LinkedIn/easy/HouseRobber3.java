package company.LinkedIn.easy;


public class HouseRobber3 {
	public int rob(TreeNode root) {
		if (root == null)
			return 0;
		int[] result = helper(root);
		return Math.max(result[0], result[1]);
	}

	public int[] helper(TreeNode root) {
		if (root == null)
			return new int[] { 0, 0 };
		int[] result = new int[2];
		int[] left = helper(root.left);
		int[] right = helper(root.right);

		result[0] = root.val + left[1] + right[1];
		result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		return result;
	}
}
