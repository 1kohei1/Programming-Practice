import java.util.Arrays;
import java.util.Scanner;

public class wallst {

	static int n;
	static int[][] blocks;
	static long[][] memo;
	static int[][][] dimension;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	
		int nt = scanner.nextInt();
		while(nt-- > 0) {
			n = scanner.nextInt();
			
			blocks = new int[n][2];
			memo = new long[n][n];
			dimension = new int[n][n][2];
						
			for (int i = 0; i < n; i++) {
				blocks[i] = new int[]{scanner.nextInt(), scanner.nextInt()};
			}
			
			// Initialize global variables
			for (int i = 0; i < n; i++) {
				Arrays.fill(memo[i], -1);
			}
			setDimension();
//			printDimension();

			System.out.println(solve(0, n - 1));
		}
	}
/*
2
3 20 30 60 40 30 50
2 10 90 30 40	
 */
	public static long solve(int start, int end) {
		if (start == end) {
			return 0;
		}
		if (memo[start][end] != -1) {
			return memo[start][end];
		}
		
		long answer = Long.MAX_VALUE;
		for (int i = start; i < end; i++) {
			long a = solve(start, i) + solve(i + 1, end) + 100 * Math.min(dimension[start][i][0], dimension[start][i][1]) * Math.min(dimension[i + 1][end][0], dimension[i + 1][end][1]);
			answer = Math.min(answer, a);
		}
		memo[start][end] = answer;
		return answer;
	}
	
	public static void setDimension() {
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == j) {
					dimension[i][j] = blocks[i];
				} else {
					int x = dimension[i][j - 1][0] + blocks[j][0];
					int y = Math.max(dimension[i][j - 1][1], blocks[j][1]);
					dimension[i][j] = new int[]{x, y};
				}
			}
		}
	}
	
	public static void printDimension() {
		for (int i = 0; i < dimension.length; i++) {
			for (int j = 0; j < dimension[i].length; j++) {
				int row = 0;
				int col = 0;
				if (i <= j) {
					row = dimension[i][j][0];
					col = dimension[i][j][1];
				}
				System.out.printf("(%3d, %3d) ", row, col);
			}
			System.out.println();
		}
	}
}
