import java.util.Arrays;
import java.util.Scanner;

public class MatrixChainMultiplication {

	static int n;
	static int[][] memo;
	static int[][] matrix;
	static int[][][] matrixDimension;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			n = scanner.nextInt();
			
			if (n == 0) {
				break;
			}
			
			matrix = new int[n][2];
			memo = new int[n][n];
			matrixDimension = new int[n][n][2];
			for (int i = 0; i < n; i++) {
				Arrays.fill(memo[i], -1);
			}
			
			for (int i = 0; i < n; i++) {
				matrix[i] = new int[]{scanner.nextInt(), scanner.nextInt()};
			}

			setMatrixDimension();
			printMatrixDimension();
			
			System.out.println(solve(0, n - 1));
		}
	}
/*
4
10 100
100 5
5 50
50 1
=> 1750
 */
	public static int solve(int start, int end) {
		if (start == end) {
			return 0;
		}
		
		if (memo[start][end] != -1) {
			return memo[start][end];
		}
		
		int answer = Integer.MAX_VALUE;
		for (int i = start; i < end; i++) {
			int a = solve(start, i) + solve(i + 1, end) + matrixDimension[start][i][0] * matrixDimension[start][i][1] * matrixDimension[i + 1][end][1];
			answer = Math.min(answer, a);
		}
		memo[start][end] = answer;
		return answer;
	}
	
	public static void setMatrixDimension() {
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == j) {
					matrixDimension[i][j] = matrix[i];
				} else {
					matrixDimension[i][j] = new int[]{matrixDimension[i][j - 1][0], matrix[j][1]};
				}
			}
		}
	}
	
	public static void printMatrixDimension() {
		for (int i = 0; i < matrixDimension.length; i++) {
			for (int j = 0; j < matrixDimension[i].length; j++) {
				int row = 0;
				int col = 0;
				if (i <= j) {
					row = matrixDimension[i][j][0];
					col = matrixDimension[i][j][1];
				}
				System.out.printf("(%3d, %3d) ", row, col);
			}
			System.out.println();
		}
	}
}
