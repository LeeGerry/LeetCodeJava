package leetcode.matrix;

import java.util.ArrayList;
import java.util.List;
/**
 * https://leetcode.com/problems/spiral-matrix/description/
 * Given the following matrix:
		[
		 [ 1, 2, 3 ],
		 [ 4, 5, 6 ],
		 [ 7, 8, 9 ]
		]
You should return [1,2,3,6,9,8,7,4,5].
螺旋输出二维数组，顺时针方向。
1.肯定要便利一遍
2.不能用简单的for循环
3.设置标记位，用while循环，从左到右，从上到下，从右到左，从下到上，四个方向遍历，然后修改标记位
 */
public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix){
		List<Integer> result = new ArrayList<>();
		if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)	return result;
		
		int rowBegin = 0;					// 标记：开始行
		int rowEnd = matrix.length - 1;		// 标记：结束行
		int colBegin = 0;					// 标记：开始列
		int colEnd = matrix[0].length -1;	// 标记：结束列
		while(rowBegin <= rowEnd && colBegin <= colEnd){ // while控制终止条件
			// 行从左向右：行号不变，列增加
			for(int j=colBegin; j<=colEnd; j++)
				result.add(matrix[rowBegin][j]);
			rowBegin++; // 遍历结束后 开始行++
			
			// 列从上向下：列号不变，行增加
			for(int j = rowBegin; j<=rowEnd; j++)
				result.add(matrix[j][colEnd]);
			colEnd--; // 遍历结束后 结束列--
			
			// 行从右向左：行号不变，列自减
			if(rowBegin<=rowEnd) //rowBegin在上面已经更新过，所以在此要重新判断一下
				for(int j = colEnd; j>=colBegin; j--)//从右向左遍历
					result.add(matrix[rowEnd][j]);
			rowEnd--;// 结束行--
			
			// 列从下向上：列号不变，行自减
			if(colBegin <= colEnd) // colEnd上面更新过，在此要重新判断一下
				for(int j = rowEnd; j>=rowBegin; j--)
					result.add(matrix[j][colBegin]);
			colBegin++; // 开始列++
		}
		return result;
	}
}
