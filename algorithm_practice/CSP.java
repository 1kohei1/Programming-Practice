import java.util.ArrayList;
import java.util.Scanner;

public class CSP {

	public static void main(String[] args) {

	}

	// Solves map color problem by using arc consistency
	// Input format is:
	// # of area (n) if n is 0, exit loop
	// # of connections
	// Connection index starts from 0 <= loc1 loc2 <n
	// Domain entry for each area. It will be # of colors of that, and # of chose
	public static void MapColorProblem() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while (n != 0) {
			int[][] map = new int[n][n];
			int[][] domains = new int[n][];
			
			// # of connections
			int numConnections = in.nextInt();
			for (int i = 0; i < numConnections; i++) {
				int start = in.nextInt();
				int end = in.nextInt();
				map[start][end] = 1;
			}
			
			// # domain for each area
			for (int i = 0; i < n; i++) {
				int numDomain = in.nextInt();
				domains[i] = new int[numDomain];
				for (int j = 0; j < numDomain; j++) {
					domains[i][j] = in.nextInt();
				}
			}
			
			// # of regions
			n = in.nextInt();
		}
	}
}
