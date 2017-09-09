import java.util.*;

// ABC 73-D
// http://abc073.contest.atcoder.jp/

public class Main {

	static int N;
	static int M;
	static int R;
	
	static int answer = Integer.MAX_VALUE;
	static int[][] map;
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println(Integer.MAX_VALUE / 2 + Integer.MAX_VALUE / 2);
		
		N = in.nextInt();
		M = in.nextInt();
		R = in.nextInt();
		
		int[] cities = new int[R];
		for (int i = 0; i < R; i++) {
			cities[i] = in.nextInt() - 1;
		}
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 100000 * (N - 1) + 1);
		}
		
		for (int i = 0; i < M; i++) {
			int s = in.nextInt() - 1;
			int g = in.nextInt() - 1;
			int d = in.nextInt();
			
			map[s][g] = d;
			map[g][s] = d;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.printf("%d ", map[i][j]);
//			}
//			System.out.println();
//		}
		
		solve(0, cities, new int[R], new int[R]);
		System.out.println(answer);
	}
	
	public static void solve(int index, int[] cities, int[] used, int[] path) {
		if (index == R) {
//			for (int i = 0; i < R; i++) {
//				System.out.printf("%d ", path[i]);
//			}
//			System.out.println();
			answer = Math.min(answer, distance(path));
		} else {
			for (int i = 0; i < R; i++) {
				if (used[i] == 0) {
					path[index] = cities[i];
					used[i] = 1;
					solve(index + 1, cities, used, path);
					used[i] = 0;
				}
			}
		}
	}
	
	public static int distance(int[] path) {
		int d = 0;
		for (int i = 0; i < R - 1; i++) {
			d += map[path[i]][path[i + 1]];
		}
		return d;
	}
}


//Arrays.fill(map[i], 100000 * (N - 1) + 1);