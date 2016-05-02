import java.util.Arrays;
import java.util.Scanner;

public class bones {

	static int n;
	static long[][] map;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int nt = in.nextInt();
		for (int c = 0; c < nt; c++) {
			n = in.nextInt();
			int k = in.nextInt();
			int m = in.nextInt();
			
			long bigNumber = (long) Math.pow(10, 12);
			
			map = new long[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(map[i], bigNumber);
			}
			
			for (int i = 0; i < m; i++) {
				int s = in.nextInt();
				int e = in.nextInt();
				int d = in.nextInt();
				
				map[s][e] = d;
				map[e][s] = d;
			}
			
			// Get shortest path
			for (int kk = 0; kk < n; kk++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (i != j) {
							map[i][j] = Math.min(map[i][j], map[i][kk] + map[kk][j]);
						}
					}
				}
			}
			
//			printMap();
			
			long low = 1;
			long high = bigNumber;
			
			while(low <= high) {
								
				long mid = (low + high) / 2;
//				System.out.printf("low: %d, high: %d, mid: %d\n", low, high, mid);

				int[][] map2 = new int[n][n];
				for (int i = 0; i < n; i++) {
					Arrays.fill(map2[i], 200);
					map2[i][i] = 0;
				}
				
				// If i -> j is reachable with full battery charged, put 1
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (map[i][j] <= mid) {
							map2[i][j] = 1;
						}
					}
				}
				
				// Connect reachable charge
				for (int kk = 0; kk < n; kk++) {
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							map2[i][j] = Math.min(map2[i][j], map2[i][kk] + map2[kk][j]);
						}
					}
				}
				
//				printMap2(map2);
				
				int biggest = 0;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (map2[i][j] > biggest) {
							biggest = map2[i][j];
						}
					}
				}
				
				if (biggest <= k) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			
			System.out.println(low);
		}
	}
	
	public static void printMap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void printMap2(int[][] map2) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%d ", map2[i][j]);
			}
			System.out.println();
		}
	}
	
/*
2
4 2 4
0 1 100
1 2 200
2 3 300
3 0 400
10 2 15
0 1 113
1 2 314
2 3 271
3 4 141
4 0 173
5 7 235
7 9 979
9 6 402
6 8 431
8 5 462
0 5 411
1 6 855
2 7 921
3 8 355
4 9 113

1
10 2 15
0 1 113
1 2 314
2 3 271
3 4 141
4 0 173
5 7 235
7 9 979
9 6 402
6 8 431
8 5 462
0 5 411
1 6 855
2 7 921
3 8 355
4 9 113
 */
}
