import java.util.*;

// ABC 80-C
// https://beta.atcoder.jp/contests/abc080/tasks/abc080_c

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		
		int[][] stores = new int[N][11];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				stores[i][j] = in.nextInt();
			}
		}
		
		int[][] points = new int[N][11];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 11; j++) {
				points[i][j] = in.nextInt();
			}
		}
		
		long answer = Long.MIN_VALUE;
		
		// Try all patterns
		for (int i = 1; i < (1 << 10); i++) {
			long n = 0;
			// Go through stores
			for (int j = 0; j < N; j++) {
				int numOverlappings = 0;
				// Check if there is overlapping
				for (int k = 0; k <= 9; k++) {
					if ((i & (1 << k)) > 0 && stores[j][k] == 1) {
						numOverlappings++;
					}
				}
				n += points[j][numOverlappings];
			}
			answer = Math.max(answer, n);
		}
		System.out.println(answer);
	}
}