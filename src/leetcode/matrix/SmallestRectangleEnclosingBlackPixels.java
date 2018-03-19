package leetcode.matrix;
/**
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
 * The black pixels are connected, i.e., there is only one black region. 
 * Pixels are connected horizontally and vertically. 
 * Given the location (x, y) of one of the black pixels, 
 * 	return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

	For example, given the following image:
	
	[
	  "0010",
	  "0110",
	  "0100"
	]
	and x = 0, y = 2,

 二维数组代表一张图，0是白色像素点，1是黑色像素点，一张图中只有一片黑色区域，黑色像素点是相连的。（上下左右相连）
 给定一个黑色像素点，返回一个数值，该数值为能够覆盖黑色区域的最小矩形面积。
 如上图，[0,1],[0,2],[2,1],[2,2]作为四个顶点的矩形，恰好覆盖了所有黑色像素点。面积为6.

Return 6.
 * time: O(m * log n + n * log m)
 * space: O(1)
 */
public class SmallestRectangleEnclosingBlackPixels {
	/**
	 * 得出矩形的最小面积
	 * @param image 图片像素矩阵
	 * @param x 其中一个黑色像素的x坐标
	 * @param y 其中一个黑色像素的y坐标
	 * @return
	 */
	public int minArea(char[][] image, int x, int y) {
		int row = image.length;
		int col = image[0].length;
		// 最左边的位置
		int left = binarySearchLeft(image, 0, y, true);
		// 最右边的位置
		int right = binarySearchRight(image, y, col - 1, true);
		// 最上边的位置
		int top = binarySearchLeft(image, 0, x, false);
		// 最下边的位置
		int bottom = binarySearchRight(image, x, row - 1, false);
		// 矩形面积
		return (right - left + 1) * (bottom - top + 1);
	}

	/**
	 * 找最右边
	 * @param image
	 * @param left
	 * @param right
	 * @param isHo true横着找，false竖着找
	 * @return
	 */
	private int binarySearchRight(char[][] image, int left, int right, boolean isHo) {
		while(left + 1 < right){
			int mid = (right - left) / 2 + left;
			if(hasBlack(image, mid, isHo))	left = mid;
			else							right = mid;
		}
		if(hasBlack(image, right, isHo))	return right;
		return left;
	}
	/**
	 * 找最左边
	 * @param image
	 * @param left
	 * @param right
	 * @param isHo true横着找，false竖着找
	 * @return
	 */
	private int binarySearchLeft(char[][] image, int left, int right, boolean isHo) {
		while(left + 1 < right){
			int mid = (right - left) / 2 + left;
			if(hasBlack(image, mid, isHo))	right = mid;
			else 							left = mid;
		}
		if(hasBlack(image, left, isHo))		return left;
		return right;
	}
	/**
	 * 是否有黑色像素
	 * @param image
	 * @param x
	 * @param isHo true横着找，false竖着找
	 * @return
	 */
	private boolean hasBlack(char[][] image, int x, boolean isHo) {
		if(isHo){
			for(int i = 0; i < image.length; i++){
				if(image[i][x] == '1')	return true;
			}
		}else{
			for(int i = 0; i<image[0].length; i++){
				if(image[x][i] == '1')	return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		char[][] image = {
				{'0','0','1','0','0','0','0','0'},
				{'0','0','1','1','0','1','0','0'},
				{'0','0','0','1','1','1','0','0'},
				{'0','0','0','0','0','1','0','0'},
				{'0','0','0','0','0','1','0','0'}
		};
		SmallestRectangleEnclosingBlackPixels s = new SmallestRectangleEnclosingBlackPixels();
		System.out.println(s.minArea(image, 1, 2));
	}
}
