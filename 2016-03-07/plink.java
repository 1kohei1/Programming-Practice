import java.util.Scanner;
import java.util.Arrays;

public class plink {
	
	static int[][] dp;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		for (int counter = 1; counter <= n; counter++) {
			int col = scanner.nextInt();
			int row = scanner.nextInt();
			
			char[][] map = new char[row][col];
			dp = new int[row][col];
			for (int i = 0; i < row; i++) {
				map[i] = scanner.next().toCharArray();
				Arrays.fill(dp[i], -1);
			}
			
			int max = 0;
			int dropCol = 0;
			// Drop a ball at [0][i]
			for (int i = 1; i < col - 1; i++) {
				int score = getScore(row, col, 0, i, map);
				if (max < score) {
					dropCol = i;
				}
				max = Math.max(max, score);
			}
			if (max == 0) {
				System.out.printf("Board #%d: Don't even bother dropping a coin. You can't win!\n", counter);
			} else {
				System.out.printf("Board #%d: Drop at column %d for a score of %d points.\n", counter, dropCol, max);
			}
			System.out.println();
		}
	}
/*
2
20 6
|..................|
|\____.\__.__/\./\.|
|.____/..|.|...\__.|
|\__|....|.|....._/|
||.......|\_.......|
|987654321123456789|
7 5
|.....|
|\__._|
|..._.|
|.....|
|12345|
 */
	// Return the result if ball comes to map[r][c] and map[r][c] == '.'
	public static int getScore(int row, int col, int r, int c, char[][] map) {
		if (dp[r][c] >= 0) {
			return dp[r][c];
		}
		while (r != row - 1 && map[r][c] == '.') {
			r++;
		}
		if (map[r][c] == '|' || map[r][c] == '_') {
			dp[r][c] = 0;
			return 0;
		}
		if (r == row - 1) {
			dp[r][c] = map[r][c] - '0';
			return map[r][c] - '0';
		}
		if (map[r][c] == '\\') {
			c++;
			while (c != col - 1 && map[r][c] == '_') {
				c++;
			}
			if (map[r][c] == '.') {
				dp[r][c] = getScore(row, col, r, c, map);
				return dp[r][c];
			} else {
				dp[r][c] = 0;
				return 0;
			}
		}
		if (map[r][c] == '/') {
			c--;
			while (c != 0 && map[r][c] == '_') {
				c--;
			}
			if (map[r][c] == '.') {
				dp[r][c] = getScore(row, col, r, c, map);
				return dp[r][c];
			} else {
				dp[r][c] = 0;
				return 0;
			}
		}
		return 0;
	}
}
