import java.util.Arrays;
import java.util.Scanner;

public class prob2_02_20 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		while (n != 0) {
			n++;
			long[][] grid = new long[n][n];
			
			for (int i = 1; i < n; i++) {
				grid[0][i] = 1;
			}
			
			for (int i = 1; i < n; i++) {
				for (int j = i; j < n; j++) {
					grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
				}
			}
			System.out.println(grid[n - 1][n - 1]);
			n = scanner.nextInt();
		}
	}

}
