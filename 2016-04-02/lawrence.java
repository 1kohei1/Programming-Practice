import java.util.Arrays;
import java.util.Scanner;

public class lawrence {

	static int n;
	static int[][] lookup;
	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	
		while(true) {
			n = scanner.nextInt();
			int numBombs = scanner.nextInt();
			if (n == 0 && numBombs == 0) {
				break;
			}
			
			int[] points = new int[n];
			for (int i = 0; i < n; i++) {
				points[i] = scanner.nextInt();
			}
			
			// Calculate look up
			lookup = new int[n][n];
			for (int i = 0; i < n; i++) {
				int pointsSum = points[i];
				int squareSum = points[i] * points[i];
				
				for (int j = 1; j < n; j++) {
					if (i + j >= n) {
						lookup[i][j] = lookup[i][j - 1];
					} else {
						pointsSum += points[i + j];
						squareSum += points[i + j] * points[i + j];
						
						int score = (pointsSum * pointsSum - squareSum) / 2;
						lookup[i][j] = score;
					}
				}
			}

			dp = new int[n][numBombs + 1];
			for (int i = 0; i < n; i++) {
				Arrays.fill(dp[i], -1);
			}
			
			System.out.println(solve(0, numBombs, 0));
		}
	}
/*
4 1
4 5 1 2
4 2
4 5 1 2
5 2
1 2 3 4 5
0 0
 */
	public static int solve(int index, int numBomb, int numEdge) {
		if (index == n) {
			if (numEdge == 0) {
				return 0;
			}
			return lookup[index - numEdge][numEdge - 1];
		}
		if (numBomb == 0) {
			return lookup[index - numEdge][n - 1];
		}
		if (dp[index - numEdge][numBomb] != -1) {
			return dp[index][numBomb];
		}
		int placingBomb = lookup[index - numEdge][numEdge] + solve(index + 1, numBomb - 1, 0);
		int notPlacing = solve(index + 1, numBomb, numEdge + 1);
		int answer = Math.min(placingBomb, notPlacing);
		dp[index - numEdge][numBomb] = answer;
		return answer;
	}

	public static void print(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.printf("%d ", array[i][j]);
			}
			System.out.println();
		}
	}
}
