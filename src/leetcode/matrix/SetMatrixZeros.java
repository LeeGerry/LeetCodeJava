package leetcode.matrix;
/**
 * https://leetcode.com/problems/set-matrix-zeroes/description/
 * 给定一个二维数组，进行Update：
 * 规则：若第i行第j列的元素是0，则把i行和i列所有元素都更新为0
 * 例如 	1011			0000
 * 	   	1100			0000
 * 		1111   -->		1000
 * 		1111			1000
 */
public class SetMatrixZeros {
	/**
	 * 方法一：设置两个一维数组，记录应变成0的行号和列号
	 * 	时间 O(m*n), 空间O(m+n)
	 */
	public void setZeros(int[][] matrix){
		boolean[] row = new boolean[matrix.length];
		boolean[] col = new boolean[matrix[0].length];
		
		// 第一遍遍历，扫描并记录值为0的元素所在行号和列号
		for(int i = 0; i<matrix.length; i++){
			for(int j = 0; j<matrix[0].length;j++){
				if(matrix[i][j] == 0){// 若m[i][j]为0，则说明第i行和第j列元素都应该为0
					row[i] = true;    // 设置标记
					col[j] = true;
				}
			}
		}
		
		// 第二遍扫描，根据被标记的行号和列号，更新数组
		for(int i = 0; i<matrix.length; i++){
			for(int j = 0; j<matrix[0].length;j++){
				// 若第i行或者第j列被标记过，说明该元素应该被设置为0
				if(row[i] || col[j])	matrix[i][j] = 0; 
			}
		}
	}
	// 要更新数组，遍历的时间复杂度肯定是m*n不会提高了，那么是否可以进行空间上的优化呢？
	/**
	 * 方法二：空间优化为O(1),可以用两个布尔变量值，借用第0行和第0列的数组元素本身来进行记录
	 * @param matrix
	 */
	public void setZeros2(int[][] matrix){
		// 设置boolean类型的两个变量来记录第0行或者第0列是否应该设置为0
		boolean booRow = false, booCol = false;
		
		// 第一遍遍历 来进行状态记录
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				if(matrix[i][j] == 0){ // 若当前元素[i,j]为0，则进行记录
					if(i==0)	booRow = true; // 若第0行元素有0出现，则第0行最终应该全部更新为0，行标记置为true
					if(j==0)	booCol = true; // 若第0列元素有0出现，则第0列最终应该全部更新为0，列标记置为true
					matrix[0][j] = 0; 	// 标记对应第0行第j列的元素为0
					matrix[i][0] = 0;   // 标记对应第i行第0列的元素为0
				}
			}
		}
		
		// 第二遍遍历 来进行元素更新，从第1行第1列开始，事实上，在这里第0行和第0列充当了标记位
		for(int i = 1; i<matrix.length; i++){
			for(int j=1; j<matrix[0].length; j++){
				// 查询对应的行标记是否是0，查询对应的列标记是否是0，若两者有一个是0，则说明该元素应该更新为0
				if(matrix[i][0] == 0 || matrix[0][j] == 0) 
					matrix[i][j] = 0;
			}
		}
		
		// 第二遍更新完了以后，要对第0行和第0列进行更新，因为上面的更新是从第1行和第1列开始
		if(booRow) // 如果行标记=true，把第0行的元素都置为0
			for(int i=0;i<matrix[0].length; i++)	matrix[0][i] = 0;
		if(booCol) // 如果列标记=true，把第0列的元素都置为0
			for(int i=0; i<matrix.length; i++)	matrix[i][0] = 0;
	}
}
