import java.util.Scanner;
import java.util.Arrays;

// Works negative cycle
// Computational efficiency is (n - 1)E
public class BellmanFord {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			int n = scanner.nextInt();
			int e = scanner.nextInt();
			
			if (n == 0) break;
			
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(map[i], Integer.MAX_VALUE / 2 - 1);
			}

			for (int i = 0; i < e; i++) {
				map[scanner.nextInt()][scanner.nextInt()] = scanner.nextInt();
			}
			
			int start = scanner.nextInt();
			int goal = scanner.nextInt();
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[start][i] > map[start][j] + map[j][i]) {
						map[start][i] = map[start][j] + map[j][i];
					}
				}
			}
		}
	}

}
