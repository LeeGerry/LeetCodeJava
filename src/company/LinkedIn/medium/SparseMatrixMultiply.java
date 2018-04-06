package company.LinkedIn.medium;
/**
 * LeetCode 311
 * 稀疏矩阵进行相乘
 * | 1 0 0 |   *  | 7 0 0 |			| 7 0 0 |
 * |-1 0 3 |	  | 0 0 0 |    =    |-7 0 3 |
 *  			  | 0 0 1 |
 * 时间O(m*n*nB) 空间O(m*nB)
 */
public class SparseMatrixMultiply {
	public int[][] mutiply(int[][]A, int[][]B){
		// 构造
		int m = A.length, n = A[0].length; // A矩阵m*n
		int nB = B[0].length;			   // B矩阵n*nB
		int[][] result = new int[m][nB];   // 结果为m*nB
		
		// 进行遍历
		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				if(A[i][k] != 0){ // 不为0再进行计算
					for(int j = 0; j < nB; j++){
						// 同样，不为0，再进行计算
						if(B[k][j] != 0)	result[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
		
		return result;
	}

















}
